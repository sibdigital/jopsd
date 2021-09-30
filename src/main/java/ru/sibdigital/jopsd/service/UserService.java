package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.model.opsd.User;

public interface UserService {
    User findByLogin(String login);
}
