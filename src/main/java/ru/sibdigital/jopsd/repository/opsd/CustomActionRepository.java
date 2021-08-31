package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.CustomAction;

@Repository
public interface CustomActionRepository extends JpaRepository<CustomAction, Long>, JpaSpecificationExecutor<CustomAction> {
}