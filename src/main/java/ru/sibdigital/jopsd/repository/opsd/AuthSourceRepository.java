package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.AuthSource;

@Repository
public interface AuthSourceRepository extends JpaRepository<AuthSource, Long> {
}