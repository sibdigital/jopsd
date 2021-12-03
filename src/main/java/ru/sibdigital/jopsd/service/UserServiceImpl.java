package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl extends SuperServiceImpl implements UserService{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public List<Map<String, Object>> checkPermissions(Long projectId, Long userId, Set<String> permissions) {
        return rolePermissionRepository.checkPermissions(projectId, userId, permissions);
    }
}
