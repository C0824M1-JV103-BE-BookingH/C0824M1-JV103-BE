package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.Review;
import com.example.casestudy_g2_m4.model.User;
import com.example.casestudy_g2_m4.service.hotel.IHotelService;
import com.example.casestudy_g2_m4.service.review.IReviewService;
import com.example.casestudy_g2_m4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IUserService userService;

    @GetMapping("/create/{hotelId}")
    public String showReviewForm(@PathVariable Integer hotelId, Model model) {
        model.addAttribute("hotel", hotelService.findById(hotelId));
        model.addAttribute("review", new Review());
        return "review/create_review";
    }

    @PostMapping("/create/{hotelId}")
    public String submitReview(@PathVariable Integer hotelId,
                               @ModelAttribute Review review,
                               Principal principal) {
        User user = userService.findByEmail(principal.getName());
        review.setUser(user);
        review.setHotel(hotelService.findById(hotelId));
        review.setCreatedAt(LocalDateTime.now());
        reviewService.save(review);
        return "redirect:/hotels/details/" + hotelId;
    }
}
