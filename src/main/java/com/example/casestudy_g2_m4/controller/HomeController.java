package com.example.casestudy_g2_m4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.casestudy_g2_m4.model.Hotel;
import com.example.casestudy_g2_m4.service.hotel.IHotelService;
import com.example.casestudy_g2_m4.service.roomtype.IRoomTypeService;

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
    public ModelAndView booking(@RequestParam(value = "checkIn", required = false) String checkIn,
                               @RequestParam(value = "checkOut", required = false) String checkOut) {
        ModelAndView modelAndView = new ModelAndView("dashboard/book_now");
        modelAndView.addObject("booking", roomTypeService.findAllRoomType());
        modelAndView.addObject("checkIn", checkIn);
        modelAndView.addObject("checkOut", checkOut);
        // Tính số ngày ở lại nếu có đủ thông tin
        long days = 0;
        if (checkIn != null && checkOut != null && !checkIn.isEmpty() && !checkOut.isEmpty()) {
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                java.time.LocalDate checkInDate = java.time.LocalDate.parse(checkIn, formatter);
                java.time.LocalDate checkOutDate = java.time.LocalDate.parse(checkOut, formatter);
                days = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            } catch (Exception e) {
                days = 0; // Nếu lỗi parse thì để 0
            }
        }
        modelAndView.addObject("days", days);
        return modelAndView;
    }
    @GetMapping("/book_room")
public String showBookRoom(
        @RequestParam String roomType,
        @RequestParam String price,
        @RequestParam String checkIn,
        @RequestParam String checkOut,
        @RequestParam String imageUrl,
        @RequestParam String maxPeople,
        @RequestParam String description,
        Model model) {
    model.addAttribute("roomType", roomType);
    model.addAttribute("price", price);
    model.addAttribute("checkIn", checkIn);
    model.addAttribute("checkOut", checkOut);
    model.addAttribute("imageUrl", imageUrl);
    model.addAttribute("maxPeople", maxPeople);
    model.addAttribute("description", description);
    return "dashboard/book_room";
}
}