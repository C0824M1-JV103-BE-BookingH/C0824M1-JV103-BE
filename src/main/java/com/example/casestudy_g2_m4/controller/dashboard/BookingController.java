package com.example.casestudy_g2_m4.controller.dashboard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.casestudy_g2_m4.model.Booking;
import com.example.casestudy_g2_m4.model.BookingDTO;
import com.example.casestudy_g2_m4.service.booking.IBookingService;
import com.example.casestudy_g2_m4.service.room.IRoomService;
import com.example.casestudy_g2_m4.service.roomtype.IRoomTypeService;
import com.example.casestudy_g2_m4.service.user.IUserService;

import jakarta.validation.Valid;

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

//    @GetMapping("list_booking")
//    public ModelAndView listBooking(@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime checkIn,
//                                    @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime checkOut) {
//        ModelAndView modelAndView = new ModelAndView("dashboard/booking/list_booking");
//        List<Booking> bookings;
//        List<BookingDTO> bookingDTOs;
//        bookingDTOs = bookingService.findAllBooking();
//        // Kiểm tra nếu checkIn lớn hơn checkOut
//        if (checkIn != null && checkOut != null && checkIn.isAfter(checkOut)) {
//            modelAndView.addObject("error", "Check-in date must be before check-out date");
//            bookingDTOs = bookingService.findAllBooking();
//        } else if (checkIn != null && checkOut != null) {
//            bookings = bookingService.findBookingByDateRange(checkIn, checkOut);
//            logger.info("Searching bookings by date range: checkIn={}, checkOut={}", checkIn, checkOut);
//
//            bookingDTOs = bookings.stream()
//                    .map(BookingDTO::new)
//                    .collect(Collectors.toList());
//        } else {
//            bookingDTOs = bookingService.findAllBooking();
//            logger.info("Displaying all bookings");
//        }
//
//        logger.info("Number of bookings: {}", bookingDTOs.size());
//        if (bookingDTOs.isEmpty()) {
//            logger.warn("No bookings found");
//        } else {
//            bookingDTOs.forEach(dto -> logger.debug("Booking DTO - id: {}, checkIn: {}, checkOut: {}, createdAt: {}",
//                    dto.getId(), dto.getCheckIn(), dto.getCheckOut(), dto.getCreatedAt()));
//        }
//
//        modelAndView.addObject("listBooking", bookingDTOs);
//        modelAndView.addObject("checkIn", checkIn);
//        modelAndView.addObject("checkOut", checkOut);
//        return modelAndView;
//    }

    @GetMapping("list_booking")
    public ModelAndView listBooking(@RequestParam(required = false) String keyword,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime checkIn,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime checkOut,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime createdAt) {
        ModelAndView modelAndView = new ModelAndView("dashboard/booking/list_booking");
//        List<Booking> bookings = bookingService.findAllBooking();
        List<BookingDTO> bookingDTOs;
        if ((keyword != null && !keyword.isEmpty()) || checkIn != null || checkOut != null || createdAt != null) {
            bookingDTOs = bookingService.search(keyword, checkIn, checkOut, createdAt);
            modelAndView.addObject("keyword", keyword);
            modelAndView.addObject("checkIn", checkIn);
            modelAndView.addObject("checkOut", checkOut);
            modelAndView.addObject("createdAt", createdAt);
        } else {
            // Nếu không có keyword, hiển thị toàn bộ danh sách booking
            List<Booking> bookings = bookingService.findAllBooking();
            bookingDTOs = bookings.stream()
                    .map(BookingDTO::new)
                    .collect(Collectors.toList());
        }

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
            return "redirect:/show-form-add";
        }

        // Check role
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isUser = authentication.getAuthorities().stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER") || auth.getAuthority().equals("ROLE_CUSTOMER"));
        if (isUser) {
            return "redirect:/";
        } else {
            return "redirect:/list_booking";
        }
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
    @GetMapping("delete_booking/{id}")
    public String deleteBooking(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.deleteBooking(id);
            redirectAttributes.addFlashAttribute("message", "Booking deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete booking");
        }
        return "redirect:/list_booking";
    }
    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime checkIn,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime checkOut,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime createdAt) {
        String redirectUrl = "redirect:/list_booking";
        boolean hasParams = false;

        if ((keyword != null && !keyword.trim().isEmpty()) || checkIn != null || checkOut != null || createdAt != null) {
            redirectUrl += "?";
            hasParams = true;

            if (keyword != null && !keyword.trim().isEmpty()) {
                redirectUrl += "keyword=" + keyword.trim() + "&";
            }
            if (checkIn != null) {
                redirectUrl += "checkIn=" + checkIn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) + "&";
            }
            if (checkOut != null) {
                redirectUrl += "checkOut=" + checkOut.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) + "&";
            }
            if (createdAt != null) {
                redirectUrl += "createdAt=" + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            }

            if (redirectUrl.endsWith("&")) {
                redirectUrl = redirectUrl.substring(0, redirectUrl.length() - 1);
            }
        }

        return redirectUrl;
    }


}

