package com.example.casestudy_g2_m4.service.user;

import com.example.casestudy_g2_m4.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAllUsers();
    User saveUser(User user);
    Optional<User> findById(Integer id);

    User findByEmail(String name);

    void save(User existingUser);
}
