package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Integer> {
}
