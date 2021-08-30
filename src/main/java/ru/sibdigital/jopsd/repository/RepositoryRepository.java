package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.sibdigital.jopsd.model.Repository;

@org.springframework.stereotype.Repository
public interface RepositoryRepository extends JpaRepository<Repository, Long>, JpaSpecificationExecutor<Repository> {
}