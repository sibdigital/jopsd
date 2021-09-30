package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.OauthAccessToken;

@RepositoryRestResource
public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken, Long>, JpaSpecificationExecutor<OauthAccessToken> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(OauthAccessToken oauthAccessToken);
}