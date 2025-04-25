package com.example.casestudy_g2_m4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "homepage_hotel";
    }

    @GetMapping("/login-page")
    public String login(){
        return "login_page";
    }
}
