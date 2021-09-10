package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.User;

@Service
@Slf4j
public class UserServiceImpl extends SuperServiceImpl implements UserService{

    @Override
    public User findByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}
