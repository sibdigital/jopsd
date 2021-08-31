package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.MaterialBudgetItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialBudgetItemRepo extends JpaRepository<MaterialBudgetItem, Long>, JpaSpecificationExecutor<MaterialBudgetItem> {
    Optional<List<MaterialBudgetItem>> findAllByCostObjectIdAndCostTypeId(Long costObjectId, Long costTypeId);

    Optional<List<MaterialBudgetItem>> findAllByCostObjectId(Long costObjectId);
}
