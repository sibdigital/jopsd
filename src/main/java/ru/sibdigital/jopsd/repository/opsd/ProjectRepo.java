package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.NationalProject;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query(value = "SELECT *\n" +
                    "FROM projects\n" +
                    "WHERE lft IS NOT NULL\n" +
                    "ORDER BY lft DESC\n" +
                    "LIMIT 1;", nativeQuery = true)
    Project findProjectWithMaxLft();

//    @Query(value = "")
//    Double findRequiredDiskSpace(@Param("id") Long projectId);

    @Query(value = "select coalesce ((with rels as (\n" +
            "    select from_id, to_id\n" +
            "    from relations as r\n" +
            "    where  hierarchy > 0 and from_id <> to_id\n" +
            "),\n" +
            "               only_childs as(\n" +
            "                   select distinct to_id as child_id\n" +
            "                   from rels\n" +
            "               ),\n" +
            "               wp as(\n" +
            "                   select id, done_ratio\n" +
            "                   from work_packages as w\n" +
            "                   where project_id = :projectId\n" +
            "               ),\n" +
            "               rels_wp as( select distinct w.id as id, from_id\n" +
            "                           from wp as w\n" +
            "                                    inner join rels as r\n" +
            "                                               on w.id = from_id\n" +
            "               ),\n" +
            "               done_wp as( select distinct w.id as id, done_ratio, from_id, child_id\n" +
            "                           from wp as w\n" +
            "                                    left join rels_wp as r\n" +
            "                                              on w.id = from_id\n" +
            "                                    left join only_childs as o\n" +
            "                                              on w.id = o.child_id\n" +
            "               ),\n" +
            "               only_parents as (select *\n" +
            "                                from done_wp as r\n" +
            "                                where r.child_id is null or (not r.from_id is null and r.child_id is null)\n" +
            "               )\n" +
            "          select round(avg(done_ratio), 2) as done_ratio from only_parents), 0);", nativeQuery = true)
    Double findDoneRatio(@Param("projectId") Long projectId);

    List<Project> findByName(String name);
}