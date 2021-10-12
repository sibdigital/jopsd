WITH arb_objects_tbl AS (
    SELECT *
    FROM arbitary_objects
    WHERE type_id = :type_id and (:project_id is null or project_id = :project_id)
),
     work_packages_tbl AS (
         SELECT wp.id as work_package_id, wp.contract_id as contract_id, aot.id as arbitary_object_id
         FROM work_packages wp
                  INNER JOIN arb_objects_tbl aot
                             ON wp.arbitary_object_id = aot.id
         WHERE contract_id is not null
     ),
     spents_additional AS (
         SELECT
             CASE WHEN ce.overridden_costs IS NULL THEN
                      COALESCE(ce.costs, 0)
                  ELSE ce.overridden_costs
                 END AS spent_costs,
             ce.tyear as year,
             wpt2.work_package_id
         FROM work_packages_tbl wpt2
                  INNER JOIN cost_entries ce
                             ON wpt2.work_package_id = ce.work_package_id
     ),
     spents_tbl AS (
         SELECT spents_additional.work_package_id,
                spents_additional.year,
                sum(spents_additional.spent_costs) as spent
         FROM spents_additional
         GROUP BY work_package_id, year
     )
SELECT wpt.work_package_id as work_package_id,
       wpt.contract_id as contract_id,
       spents_tbl.year as year,
       coalesce(spents_tbl.spent, 0) as spent
FROM work_packages_tbl wpt
         LEFT JOIN spents_tbl
                   ON wpt.work_package_id = spents_tbl.work_package_id


