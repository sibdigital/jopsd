package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.EnabledModule;

@Repository
public interface EnabledModuleRepo extends JpaRepository<EnabledModule, Long> {

}
