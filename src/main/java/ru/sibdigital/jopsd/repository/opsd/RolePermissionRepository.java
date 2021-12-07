package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.RolePermission;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RepositoryRestResource
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>, JpaSpecificationExecutor<RolePermission> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(RolePermission rolePermission);

    @Query(nativeQuery = true, value = "with input_permissions as (\n" +
            "    select distinct role_permissions.permission\n" +
            "    from role_permissions\n" +
            "    where permission in :permissions\n" +
            "),\n" +
            "     user_permissions as (\n" +
            "         select distinct role_permissions.permission\n" +
            "         from members\n" +
            "                  inner join member_roles\n" +
            "                             on members.id = member_roles.member_id\n" +
            "                  inner join role_permissions\n" +
            "                             on member_roles.role_id = role_permissions.role_id\n" +
            "         where members.user_id = :user_id\n" +
            "           and members.project_id = :project_id\n" +
            "     )\n" +
            "select input_permissions.permission as permission,\n" +
            "       case\n" +
            "           when user_permissions.permission is null then false\n" +
            "           else true\n" +
            "           end is_exist\n" +
            "from input_permissions\n" +
            "         left join user_permissions\n" +
            "                   on input_permissions.permission = user_permissions.permission")
    List<Map<String, Object>> checkPermissions(@Param("project_id") Long projectId, @Param("user_id") Long userId, @Param("permissions") Set<String> permissions);
}
