package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.Relation;

@Repository
public interface RelationRepo extends JpaRepository<Relation, Long> {

}
