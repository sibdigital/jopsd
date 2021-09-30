package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sibdigital.jopsd.model.opsd.Token;

@RepositoryRestResource(exported = false)
public interface TokenRepository extends JpaRepository<Token, Long>, JpaSpecificationExecutor<Token> {
}