package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.*;
import com.example.casestudy_g2_m4.service.booking.IBookingService;
import com.example.casestudy_g2_m4.service.room.IRoomService;
import com.example.casestudy_g2_m4.service.roomtype.IRoomTypeService;
import com.example.casestudy_g2_m4.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/")
public class BookingController {
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IRoomTypeService roomTypeService;

    @GetMapping("list_booking")
    public ModelAndView listBooking() {
        ModelAndView modelAndView = new ModelAndView("dashboard/booking/list_booking");
        List<Booking> bookings = bookingService.findAllBooking();
        List<BookingDTO> bookingDTOs = bookings.stream()
                .map(BookingDTO::new)
                .collect(Collectors.toList());
        modelAndView.addObject("listBooking", bookingDTOs);
        return modelAndView;
    }

    @PostMapping("add_booking")
    public String addBooking(@Valid @ModelAttribute("bookingDTO") BookingDTO bookingDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Dữ liệu không hợp lệ: " + bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/show-form-add";
        }

        try {
            bookingService.addBooking(bookingDTO);
            redirectAttributes.addFlashAttribute("message", "Booking successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/show-form-add";
        }



    @GetMapping("show-form-add")
    public String showFormAdd(Model model) {
        BookingDTO bookingDTO = new BookingDTO();
//        Room room = new Room();
//        room.setRoomType(new RoomType());
//        booking.setRoom(room);

        model.addAttribute("bookingDTO", bookingDTO);
//        model.addAttribute("users", userService.findAllUsers());

        return "dashboard/booking/add_booking";
    }
    @GetMapping("show-form-update/{id}")
    public String showFormUpdate(@PathVariable("id") Integer id,  Model model) {
       Booking booking = bookingService.findBookingById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
       BookingDTO bookingDTO = new BookingDTO(booking);
       model.addAttribute("bookingDTO", bookingDTO);
       return "dashboard/booking/update_booking";
    }
    @PostMapping("update_booking")
    public String updateBooking(@Valid @ModelAttribute("bookingDTO") BookingDTO bookingDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Dữ liệu không hợp lệ: " + bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/show-form-update/" + bookingDTO.getId();
        }

        try {
            bookingService.updateBooking(bookingDTO);
            redirectAttributes.addFlashAttribute("message", "Booking updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/list_booking";
    }

}
