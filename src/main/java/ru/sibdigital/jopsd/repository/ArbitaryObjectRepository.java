package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.Announcement;
import ru.sibdigital.jopsd.model.ArbitaryObject;

@Repository
public interface ArbitaryObjectRepository extends JpaRepository<ArbitaryObject, Long>, JpaSpecificationExecutor<ArbitaryObject> {
    interface AnnouncementRepository extends JpaRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {
    }
}