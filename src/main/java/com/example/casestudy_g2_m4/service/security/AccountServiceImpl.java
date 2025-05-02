package com.example.casestudy_g2_m4.service.security;

import com.example.casestudy_g2_m4.model.User;
import com.example.casestudy_g2_m4.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public User findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public User findByRole(User.Role role) {
        return accountRepository.findByRole(role);
    }

    @Override
    public List<User> findAll() {
        return accountRepository.findAll();
    }
}
