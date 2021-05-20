package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.MaterialBudgetItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialBudgetItemRepo extends JpaRepository<MaterialBudgetItem, Long> {
    Optional<List<MaterialBudgetItem>> findAllByCostObjectIdAndAndCostTypeId(Long costObjectId, Long costTypeId);
}
