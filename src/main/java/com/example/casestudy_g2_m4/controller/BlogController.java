package com.example.casestudy_g2_m4.controller;

import com.example.casestudy_g2_m4.model.Hotel;
import com.example.casestudy_g2_m4.model.Review;
import com.example.casestudy_g2_m4.service.hotel.IHotelService;
import com.example.casestudy_g2_m4.service.review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IReviewService reviewService;

    @GetMapping
    public String showBlogPage(Model model) {
        // Get the first hotel (you might want to modify this based on your requirements)
        Hotel hotel = hotelService.findAll().get(0);
        
        // Get all reviews for the hotel
        List<Review> reviews = reviewService.findByHotelId(hotel.getId());
        
        // Calculate average rating
        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
        
        model.addAttribute("hotel", hotel);
        model.addAttribute("reviews", reviews);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("totalReviews", reviews.size());
        
        return "blog/blog";
    }
} 