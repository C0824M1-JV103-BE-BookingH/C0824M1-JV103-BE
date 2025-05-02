package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
