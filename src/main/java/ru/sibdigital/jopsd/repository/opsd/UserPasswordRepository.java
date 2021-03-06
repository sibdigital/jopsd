package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.UserPassword;

@RepositoryRestResource(exported = false)
public interface UserPasswordRepository extends JpaRepository<UserPassword, Long>, JpaSpecificationExecutor<UserPassword> {
}