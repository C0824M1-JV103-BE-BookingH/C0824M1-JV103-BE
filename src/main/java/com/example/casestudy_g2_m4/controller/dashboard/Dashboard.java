package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.Hotel;
import com.example.casestudy_g2_m4.service.hotel.IHotelService;
import com.example.casestudy_g2_m4.service.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class Dashboard {
    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IPaymentService paymentService;

    @GetMapping("dashboard")
    public String showDashboard(Model model) {
        Map<String, Double> revenueData = paymentService.getRevenueByMonth();
                model.addAttribute("revenueData", revenueData);
        return "dashboard/dashboard";
    }
    @GetMapping("hotel-inf")
    public String showHotelInf(Model model){
        List<Hotel> list = hotelService.findAll();
        model.addAttribute("list",list);
        return "dashboard/hotel_inf";
    }
    @GetMapping("update-hotel-inf/{owner}")
    public String showUpdateHotelInf(@PathVariable(name = "owner") String owner, Model model) {
        Hotel hotel = hotelService.findByOwner(owner);
        model.addAttribute("hotel", hotel);
        model.addAttribute("owner", owner);
        return "dashboard/update_hotel_inf";
    }
    @PostMapping("update-hotel-inf")
    public String updateHotelInf(@ModelAttribute Hotel hotel) {
        hotelService.update(hotel);
        return "redirect:/hotel-inf";
    }
}
