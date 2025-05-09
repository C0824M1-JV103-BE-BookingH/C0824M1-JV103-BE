package com.example.casestudy_g2_m4.service.review;

import com.example.casestudy_g2_m4.model.Review;
import java.util.List;

public interface IReviewService {
    void save(Review review);
    List<Review> findByHotelId(Integer hotelId);
}
