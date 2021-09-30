package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Announcement;
import ru.sibdigital.jopsd.model.opsd.ArbitaryObject;

@RepositoryRestResource
public interface ArbitaryObjectRepository extends JpaRepository<ArbitaryObject, Long>, JpaSpecificationExecutor<ArbitaryObject> {
    interface AnnouncementRepository extends JpaRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {
    }

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(ArbitaryObject arbitaryObject);
}