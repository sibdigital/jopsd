package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.MaterialBudgetItem;

@Repository
public interface MaterialBudgetItemRepo extends JpaRepository<MaterialBudgetItem, Long> {

}
