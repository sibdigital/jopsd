-- Выбираем по типу объекта arbitary_objects
WITH arb_objects_tbl AS (
    SELECT *
    FROM arbitary_objects
    WHERE type_id = :type_id
      and (:project_id is null or project_id = :project_id)
),
-- Находим work_packages с данными arbitary_objects и не нулевыми contracts
     work_packages_tbl AS (
         SELECT wp.id as work_package_id, wp.contract_id as contract_id, aot.id as arbitary_object_id
         FROM work_packages wp
                  INNER JOIN arb_objects_tbl aot
                             ON wp.arbitary_object_id = aot.id
         WHERE contract_id is not null
     ),
-- Расходы
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
     ),
     sum_spent_contract AS (
         SELECT wpt3.contract_id,
                SUM(spents_tbl.spent) as sum_spent
         FROM work_packages_tbl wpt3
                  LEFT JOIN spents_tbl
                            ON wpt3.work_package_id = spents_tbl.work_package_id
         GROUP BY wpt3.contract_id
     )
SELECT wpt.work_package_id as work_package_id,
       coalesce(work_packages.subject, '') as work_package_name,
       wpt.contract_id as contract_id,
       contracts.contract_subject as contract_subject,
       contracts.nmck as nmck,
       contracts.price as price,
       spents_tbl.year as year,
       coalesce(spents_tbl.spent, 0) as spent,
       0 as economy,
       coalesce(contracts.price, 0) - coalesce(ssc.sum_spent, 0) as payment_balance,
       contracts.schedule_date as schedule_date,
       contracts.schedule_date_plan as schedule_date_plan,
       contracts.notification_date as notification_date,
       contracts.notification_date_plan as notification_date_plan,
       contracts.auction_date as auction_date,
       contracts.auction_date_plan as auction_date_plan,
       contracts.contract_date as contract_date,
       contracts.contract_date_plan as contract_date_plan,
       contracts.date_end as date_end,
       contracts.date_end_plan as date_end_plan,
       contracts.note as note,
       contracts.conclusion_of_estimated_cost_date as conclusion_of_estimated_cost_date,
       contracts.conclusion_of_estimated_cost_number as conclusion_of_estimated_cost_number,
       contracts.conclusion_of_estimated_cost_details as conclusion_of_estimated_cost_details,
       contracts.conclusion_of_project_documentation_date as conclusion_of_project_documentation_date,
       contracts.conclusion_of_project_documentation_number as conclusion_of_project_documentation_number,
       contracts.conclusion_of_project_documentation_details as conclusion_of_project_documentation_details,
       contracts.conclusion_of_ecological_expertise_date as conclusion_of_ecological_expertise_date,
       contracts.conclusion_of_ecological_expertise_number as conclusion_of_ecological_expertise_number,
       contracts.conclusion_of_ecological_expertise_details as conclusion_of_ecological_expertise_details
FROM work_packages_tbl wpt
         LEFT JOIN spents_tbl
                   ON wpt.work_package_id = spents_tbl.work_package_id
         LEFT JOIN work_packages
                   ON wpt.work_package_id = work_packages.id
         LEFT JOIN contracts
                   ON wpt.contract_id = contracts.id
         LEFT JOIN sum_spent_contract ssc
                   ON contracts.id = ssc.contract_id




