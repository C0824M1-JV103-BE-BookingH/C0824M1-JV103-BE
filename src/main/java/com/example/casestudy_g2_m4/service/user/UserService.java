package com.example.casestudy_g2_m4.service.user;

import com.example.casestudy_g2_m4.model.User;
import com.example.casestudy_g2_m4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll() ;
    }

    @Override
    public User saveUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String name) {
        return null;
    }

    @Override
    public void save(User existingUser) {

    }


}
