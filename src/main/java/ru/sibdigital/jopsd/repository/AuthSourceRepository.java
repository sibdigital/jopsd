package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.AuthSource;

@Repository
public interface AuthSourceRepository extends JpaRepository<AuthSource, Long> {
}