package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Lbo;

import java.util.List;

@RepositoryRestResource
public interface LboRepository extends JpaRepository<Lbo, Long>, JpaSpecificationExecutor<Lbo> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(Lbo lbo);

    List<Lbo> findByProject_Id(Long projectId);
}