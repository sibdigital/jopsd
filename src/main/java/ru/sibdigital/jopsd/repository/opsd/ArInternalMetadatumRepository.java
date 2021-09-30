package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.ArInternalMetadatum;

@RepositoryRestResource
public interface ArInternalMetadatumRepository extends JpaRepository<ArInternalMetadatum, String>, JpaSpecificationExecutor<ArInternalMetadatum> {
    @Override
    @RestResource(exported = false)
    void deleteById(String id);


    @Override
    @RestResource(exported = false)
    void delete(ArInternalMetadatum arInternalMetadatum);
}