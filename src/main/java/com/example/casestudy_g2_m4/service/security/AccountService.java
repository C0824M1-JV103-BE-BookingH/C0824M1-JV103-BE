package com.example.casestudy_g2_m4.service.security;

import com.example.casestudy_g2_m4.model.User;

import java.util.List;


public interface AccountService {
    User findByEmail(String email);
    User findByRole(User.Role role);
    List<User> findAll();
}
