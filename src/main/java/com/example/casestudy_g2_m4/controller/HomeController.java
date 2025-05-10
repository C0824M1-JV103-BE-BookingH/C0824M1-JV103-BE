package com.example.casestudy_g2_m4.controller;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        ModelAndView modelAndView = new ModelAndView("book_now");
        modelAndView.addObject("booking", roomTypeService.findAllRoomType());
        modelAndView.addObject("checkIn", checkIn);
        modelAndView.addObject("checkOut", checkOut);
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
        // Get the logged-in user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication != null && authentication.isAuthenticated() ? 
            authentication.getName() : "";
        
        model.addAttribute("roomType", roomType);
        model.addAttribute("price", price);
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("maxPeople", maxPeople);
        model.addAttribute("description", description);
        model.addAttribute("userEmail", userEmail);
        return "book_room";
    }
    @GetMapping("confirm_booking")
    public String confirmBooking(@RequestParam String userName,
                                 @RequestParam String email,
                                 @RequestParam String paymentMethod,
                                 @RequestParam String roomType,
                                 @RequestParam String checkIn,
                                 @RequestParam String checkOut,
                                 @RequestParam String price,
                                 Model model){
        model.addAttribute("userName", userName);
        model.addAttribute("email", email);
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("roomType", roomType);
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
        model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        model.addAttribute("price", price);
        return "confirm_booking";
    }
}