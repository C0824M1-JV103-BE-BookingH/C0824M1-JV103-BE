package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.Hotel;
import com.example.casestudy_g2_m4.service.hotel.IHotelService;
import com.example.casestudy_g2_m4.service.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/")
public class HotelInfController {
    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IPaymentService paymentService;
    @GetMapping("hotel-inf")
    public String showHotelInf(Model model){
        List<Hotel> list = hotelService.findAll();
        model.addAttribute("list",list);
        return "dashboard/hotel/hotel_inf";
    }
    @GetMapping("update-hotel-inf/{owner}")
    public String showUpdateHotelInf(@PathVariable(name = "owner") String owner, Model model) {
        Hotel hotel = hotelService.findByOwner(owner);
        model.addAttribute("hotel", hotel);
        model.addAttribute("owner", owner);
        return "dashboard/hotel/update_hotel_inf";
    }
    @PostMapping("update-hotel-inf")
    public String updateHotelInf(@ModelAttribute Hotel hotel) {
        hotelService.update(hotel);
        return "redirect:/hotel-inf";
    }
}
