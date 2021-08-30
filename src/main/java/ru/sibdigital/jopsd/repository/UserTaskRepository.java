package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.UserTask;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long>, JpaSpecificationExecutor<UserTask> {
}