package com.example.casestudy_g2_m4.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookingController {
    @GetMapping("listBooking")
    public String listBooking() {

    }
}
