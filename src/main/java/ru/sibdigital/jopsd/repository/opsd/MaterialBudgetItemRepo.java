package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.MaterialBudgetItem;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface MaterialBudgetItemRepo extends JpaRepository<MaterialBudgetItem, Long>, JpaSpecificationExecutor<MaterialBudgetItem> {
    Optional<List<MaterialBudgetItem>> findAllByCostObjectIdAndCostTypeId(Long costObjectId, Long costTypeId);

    Optional<List<MaterialBudgetItem>> findAllByCostObjectId(Long costObjectId);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(MaterialBudgetItem materialBudgetItem);
}
