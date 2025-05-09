package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByHotelId(Integer hotelId);
}
