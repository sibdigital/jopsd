package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.LdapGroupsMembership;

@RepositoryRestResource
public interface LdapGroupsMembershipRepository extends JpaRepository<LdapGroupsMembership, Long>, JpaSpecificationExecutor<LdapGroupsMembership> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(LdapGroupsMembership ldapGroupsMembership);
}