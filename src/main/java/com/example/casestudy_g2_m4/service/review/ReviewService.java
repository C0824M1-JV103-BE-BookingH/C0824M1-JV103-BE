package com.example.casestudy_g2_m4.service.review;

import com.example.casestudy_g2_m4.model.Review;
import com.example.casestudy_g2_m4.repository.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }
}
