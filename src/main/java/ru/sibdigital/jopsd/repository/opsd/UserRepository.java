package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sibdigital.jopsd.model.opsd.User;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findUserByLogin(String login);

    @Query(value = "SELECT * FROM users where identificator =:identificator"
            , nativeQuery = true)
    User findByIdent(@Param("identificator") String identificator);

    @Query(value = "SELECT *\n" +
            "FROM users\n" +
            "    INNER JOIN members\n" +
            "        ON members.user_id = users.id\n" +
            "               AND members.project_id =:id"
            , nativeQuery = true)
    List<User> findMembersByProjectId(Long id);
}
