package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<User,Long> {
    User findByEmail (String email);
    User findByRole (User.Role role);
    List<User> findAll();

}