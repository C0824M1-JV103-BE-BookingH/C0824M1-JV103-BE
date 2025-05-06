package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.Room;
import com.example.casestudy_g2_m4.service.room.IRoomService;
import com.example.casestudy_g2_m4.service.roomtype.IRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class RoomController {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IRoomTypeService roomTypeService;

    @GetMapping("rooms")
    public ModelAndView showRoomList(@RequestParam(name = "pageRoom", required = false, defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 5, Sort.by("roomNumber").ascending());
        Page<Room> roomPage = roomService.findAllRoom(pageable);
        if (page >= roomPage.getTotalPages() && roomPage.getTotalPages() > 0) {
            page = roomPage.getTotalPages() - 1;
            pageable = PageRequest.of(page, 5, Sort.by("roomNumber").ascending());
            roomPage = roomService.findAllRoom(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("dashboard/room/list_room");
        modelAndView.addObject("listRoom", roomPage);
        return modelAndView;
    }

    @GetMapping("show-form-add-room")
    public String showFormRoomAdd(Model model) {
        Room room = new Room();
        model.addAttribute("room", room);
        model.addAttribute("roomTypes", roomTypeService.findAllRoomType());
        return "dashboard/room/add_room";
    }

    @PostMapping("add-room")
    public String addRoom(@ModelAttribute("room") Room room,
                          RedirectAttributes redirectAttributes) {
        roomService.save(room);
        return"redirect:/rooms";
    }
}
