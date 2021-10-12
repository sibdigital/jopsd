package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.util.List;

@RepositoryRestResource
public interface ProjectRepo extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query(value = "SELECT *\n" +
                    "FROM projects\n" +
                    "WHERE lft IS NOT NULL\n" +
                    "ORDER BY lft DESC\n" +
                    "LIMIT 1;", nativeQuery = true)
    Project findProjectWithMaxLft();

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

    Page<Project> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(Project project);

    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    Page findByNameStartsWith(@Param("name") String name, Pageable p);

    List<Project> findByIdentifier(String identifier);

    @Query(nativeQuery = true,
            value = "select * from projects\n" +
                    " where\n" +
                    "    ((cast(:identifier as character varying) is not null and identifier like concat('%', :identifier, '%')) or (cast(:identifier as character varying) is null))\n" +
                    "  AND ((cast(cast(:start_date_left as character varying) as timestamp) is null or cast(cast(:start_date_right as character varying) as timestamp) is null) or ((cast(cast(:start_date_left as character varying) as timestamp) is not null and cast(cast(:start_date_right as character varying) as timestamp) is not null and start_date between to_timestamp(:start_date_left, 'YYYY-MM-DD') and to_timestamp(:start_date_right, 'YYYY-MM-DD'))))\n" +
                    "  AND ((cast(cast(:due_date_left as character varying) as timestamp) is null or cast(cast(:due_date_right as character varying) as timestamp) is null) or ((cast(cast(:due_date_left as character varying) as timestamp) is not null and cast(cast(:due_date_right as character varying) as timestamp) is not null and due_date between to_timestamp(:due_date_left, 'YYYY-MM-DD') and to_timestamp(:due_date_right, 'YYYY-MM-DD'))))\n" +
                    "  AND ((cast(cast(:created_on_left as character varying) as timestamp) is null or cast(cast(:created_on_right as character varying) as timestamp) is null) or ((cast(cast(:created_on_left as character varying) as timestamp) is not null and cast(cast(:created_on_right as character varying) as timestamp) is not null and created_on between to_timestamp(:created_on_left, 'YYYY-MM-DD') and to_timestamp(:created_on_right, 'YYYY-MM-DD'))))\n" +
                    "  AND ((cast(cast(:national_project_id as character varying) as INTEGER) is null) or (cast(cast(:national_project_id as character varying) as INTEGER) is not null and national_project_id=cast(cast(:national_project_id as character varying) as INTEGER)))\n" +
                    "  AND ((cast(cast(:updated_on_left as character varying) as timestamp) is null or cast(cast(:updated_on_right as character varying) as timestamp) is null) or ((cast(cast(:updated_on_left as character varying) as timestamp) is not null and cast(cast(:updated_on_right as character varying) as timestamp) is not null and updated_on between to_timestamp(:updated_on_left, 'YYYY-MM-DD') and to_timestamp(:updated_on_right, 'YYYY-MM-DD'))))\n" +
                    "  AND ((cast(cast(:project_status_id as character varying) as INTEGER) is null) or (cast(cast(:project_status_id as character varying) as INTEGER) is not null and project_status_id=cast(cast(:project_status_id as character varying) as INTEGER)))\n" +
                    "  AND ((cast(cast(:federal_project_id as CHARACTER varying) as INTEGER) is null) or (cast(cast(:federal_project_id as CHARACTER varying) as INTEGER) is not null and federal_project_id=cast(cast(:federal_project_id as CHARACTER varying) as INTEGER)))\n" +
                    "  AND ((cast(cast(:project_approve_status_id as character varying) as INTEGER) is null) or (cast(cast(:project_approve_status_id as character varying) as INTEGER) is not null and project_approve_status_id=cast(cast(:project_approve_status_id as character varying) as INTEGER)))\n" +
                    "  AND ((cast(cast(:status as character varying) as INTEGER) is null) or (cast(cast(:status as character varying) as INTEGER) is not null and status=cast(cast(:status as character varying) as INTEGER)))\n" +
                    "  AND ((cast(:name as character varying) is not null and name like concat('%', :name, '%')) or (cast(:name as character varying) is null))",
            countQuery = "select count(*) from projects\n" +
                    " where\n" +
                    "    ((cast(:identifier as character varying) is not null and identifier like concat('%', :identifier, '%')) or (cast(:identifier as character varying) is null))\n" +
                    "  AND ((cast(cast(:start_date_left as character varying) as timestamp) is null or cast(cast(:start_date_right as character varying) as timestamp) is null) or ((cast(cast(:start_date_left as character varying) as timestamp) is not null and cast(cast(:start_date_right as character varying) as timestamp) is not null and start_date between to_timestamp(:start_date_left, 'YYYY-MM-DD') and to_timestamp(:start_date_right, 'YYYY-MM-DD'))))\n" +
                    "  AND ((cast(cast(:due_date_left as character varying) as timestamp) is null or cast(cast(:due_date_right as character varying) as timestamp) is null) or ((cast(cast(:due_date_left as character varying) as timestamp) is not null and cast(cast(:due_date_right as character varying) as timestamp) is not null and due_date between to_timestamp(:due_date_left, 'YYYY-MM-DD') and to_timestamp(:due_date_right, 'YYYY-MM-DD'))))\n" +
                    "  AND ((cast(cast(:created_on_left as character varying) as timestamp) is null or cast(cast(:created_on_right as character varying) as timestamp) is null) or ((cast(cast(:created_on_left as character varying) as timestamp) is not null and cast(cast(:created_on_right as character varying) as timestamp) is not null and created_on between to_timestamp(:created_on_left, 'YYYY-MM-DD') and to_timestamp(:created_on_right, 'YYYY-MM-DD'))))\n" +
                    "  AND ((cast(cast(:national_project_id as character varying) as INTEGER) is null) or (cast(cast(:national_project_id as character varying) as INTEGER) is not null and national_project_id=cast(cast(:national_project_id as character varying) as INTEGER)))\n" +
                    "  AND ((cast(cast(:updated_on_left as character varying) as timestamp) is null or cast(cast(:updated_on_right as character varying) as timestamp) is null) or ((cast(cast(:updated_on_left as character varying) as timestamp) is not null and cast(cast(:updated_on_right as character varying) as timestamp) is not null and updated_on between to_timestamp(:updated_on_left, 'YYYY-MM-DD') and to_timestamp(:updated_on_right, 'YYYY-MM-DD'))))\n" +
                    "  AND ((cast(cast(:project_status_id as character varying) as INTEGER) is null) or (cast(cast(:project_status_id as character varying) as INTEGER) is not null and project_status_id=cast(cast(:project_status_id as character varying) as INTEGER)))\n" +
                    "  AND ((cast(cast(:federal_project_id as CHARACTER varying) as INTEGER) is null) or (cast(cast(:federal_project_id as CHARACTER varying) as INTEGER) is not null and federal_project_id=cast(cast(:federal_project_id as CHARACTER varying) as INTEGER)))\n" +
                    "  AND ((cast(cast(:project_approve_status_id as character varying) as INTEGER) is null) or (cast(cast(:project_approve_status_id as character varying) as INTEGER) is not null and project_approve_status_id=cast(cast(:project_approve_status_id as character varying) as INTEGER)))\n" +
                    "  AND ((cast(cast(:status as character varying) as INTEGER) is null) or (cast(cast(:status as character varying) as INTEGER) is not null and status=cast(cast(:status as character varying) as INTEGER)))\n" +
                    "  AND ((cast(:name as character varying) is not null and name like concat('%', :name, '%')) or (cast(:name as character varying) is null))"
    )
    Page<Project> findByProjectRegisterFields(
            @Param("start_date_left") String start_date_left,
            @Param("start_date_right") String start_date_right,
            @Param("due_date_left") String due_date_left,
            @Param("due_date_right") String due_date_right,
            @Param("created_on_left") String created_on_left,
            @Param("created_on_right") String created_on_right,
            @Param("identifier") String identifier,
            @Param("national_project_id") Integer national_project_id,
            @Param("updated_on_left") String updated_on_left,
            @Param("updated_on_right") String updated_on_right,
            @Param("project_status_id") Integer project_status_id,
            @Param("federal_project_id") Integer federal_project_id,
            @Param("project_approve_status_id") Integer project_approve_status_id,
            @Param("status") Integer status,
            @Param("name") String name,
            Pageable pageable
    );

    @Query(value = "SELECT * FROM projects " +
            "WHERE status <> 4 and status <> 3 and " +
            "   ((SELECT type FROM roles WHERE id = " +
            "       (SELECT role_id FROM principal_roles WHERE principal_id =:id)) = 'GlobalRole'" +
            "   OR id in (SELECT project_id FROM  members WHERE user_id =:id))"
            , nativeQuery = true)
    List <Project> findProjectsByUserRoles(Long id);

}