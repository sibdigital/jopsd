package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.AttachableJournal;

@RepositoryRestResource
public interface AttachableJournalRepository extends JpaRepository<AttachableJournal, Long>, JpaSpecificationExecutor<AttachableJournal> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(AttachableJournal attachableJournal);
}