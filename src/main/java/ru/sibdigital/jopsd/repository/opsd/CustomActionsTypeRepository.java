package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.CustomActionsType;

@Repository
public interface CustomActionsTypeRepository extends JpaRepository<CustomActionsType, Long>, JpaSpecificationExecutor<CustomActionsType> {
}