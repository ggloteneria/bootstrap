package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(User user);

    List<User> getUsers();

    User findById(Long id);

    void update(Long id, User userToUpdate);

    void deleteById(Long id);

    User findUserByUsername(String email);

}
