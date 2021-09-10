package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.Session;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>, JpaSpecificationExecutor<Session> {
    Session findBySessionId(String sessionId);
}