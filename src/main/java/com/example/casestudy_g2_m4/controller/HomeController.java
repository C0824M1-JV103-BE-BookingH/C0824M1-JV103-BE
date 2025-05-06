package com.example.casestudy_g2_m4.controller;

import com.example.casestudy_g2_m4.model.Hotel;
import com.example.casestudy_g2_m4.service.hotel.IHotelService;
import com.example.casestudy_g2_m4.service.roomtype.IRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRoomTypeService roomTypeService;

    @GetMapping
    public String home(Model model, @RequestParam(value = "lang", required = false) String lang) {
        List<Hotel> list = hotelService.findAll();
        model.addAttribute("list", list);
        return "homepage_hotel";
    }

    @GetMapping("/about")
    public String about(Model model, @RequestParam(value = "lang", required = false) String lang) {
        return "about_page";
    }

    @GetMapping("/gallery")
    public String gallery(Model model, @RequestParam(value = "lang", required = false) String lang) {
        return "gallery_page";
    }

    @GetMapping("/services")
    public String services(Model model, @RequestParam(value = "lang", required = false) String lang) {
        return "services_page";
    }

    @GetMapping("bookingNow")
    public ModelAndView booking() {
        return new ModelAndView("dashboard/book_now").addObject("booking", roomTypeService.findAllRoomType());
    }
}