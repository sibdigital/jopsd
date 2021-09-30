package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Page;

import java.util.List;

@RepositoryRestResource
public interface PageRepository extends JpaRepository<Page, Long>, JpaSpecificationExecutor<Page> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Page page);

    List<Page> findAllByIsGroup(boolean isGroup);
}
