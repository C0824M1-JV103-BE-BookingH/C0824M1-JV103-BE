package com.example.casestudy_g2_m4.controller;

import com.example.casestudy_g2_m4.model.Hotel;
import com.example.casestudy_g2_m4.service.hotel.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private IHotelService hotelService;
    @GetMapping
    public String home(Model model) {
        List<Hotel> list = hotelService.findAll();
        model.addAttribute("list",list);
        return "homepage_hotel";
    }
}
