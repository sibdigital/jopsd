package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.model.opsd.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    User findByLogin(String login);
    List<Map<String, Object>> checkPermissions(Long projectId, Long userId, Set<String> permissions);
}
