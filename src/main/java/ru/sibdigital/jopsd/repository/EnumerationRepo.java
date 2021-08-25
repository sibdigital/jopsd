package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.Enumeration;

@Repository
public interface EnumerationRepo extends JpaRepository<Enumeration, Long> {

}
