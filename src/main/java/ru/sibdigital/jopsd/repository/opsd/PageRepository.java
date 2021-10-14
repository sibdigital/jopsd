package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.model.opsd.projection.PageProjection;
import ru.sibdigital.jopsd.model.opsd.projection.PageShortProjection;

import java.util.List;

@RepositoryRestResource(excerptProjection = PageProjection.class)
public interface PageRepository extends JpaRepository<Page, Long>, JpaSpecificationExecutor<Page> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Page page);

    List<Page> findAllByIsGroup(boolean isGroup);

    List<Page> findAllByIsGroupAndIdIsNot(boolean isGroup, Long id);

    List<PageShortProjection> findAllByIsPublicatedAndIsDeletedAndParentIsNull(boolean isPublicated, boolean isDeleted);
}
