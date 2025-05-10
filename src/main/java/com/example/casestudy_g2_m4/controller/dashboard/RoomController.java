package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.Room;
import com.example.casestudy_g2_m4.model.RoomDTO;
import com.example.casestudy_g2_m4.model.RoomType;
import com.example.casestudy_g2_m4.service.room.IRoomService;
import com.example.casestudy_g2_m4.service.roomtype.IRoomTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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
    public String showAddRoomForm(Model model) {
        if (!model.containsAttribute("roomDTO")) {
            model.addAttribute("roomDTO", new RoomDTO());
        }
        model.addAttribute("roomTypes", roomTypeService.findAll());
        return "dashboard/room/add_room";
    }

    @PostMapping("add-room")
    public String addRoom(@Valid @ModelAttribute("roomDTO") RoomDTO roomDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("roomDTO", roomDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.roomDTO", bindingResult);
            return "redirect:/show-form-add-room";
        }

        if (roomService.existsByRoomNumber(roomDTO.getRoomNumber())) {
            redirectAttributes.addFlashAttribute("error", "Room number already exists.");
            redirectAttributes.addFlashAttribute("roomDTO", roomDTO);
            return "redirect:/show-form-add-room";
        }

        try {
            Room room = new Room();
            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setStatus(Room.Status.valueOf(roomDTO.getStatus()));
            room.setImageUrl(roomDTO.getImageUrl());

            if (roomDTO.getRoomTypeId() != null) {
                RoomType roomType = roomTypeService.findById(roomDTO.getRoomTypeId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid room type ID"));
                room.setRoomType(roomType);
            } else {
                room.setRoomType(null);
            }

            roomService.save(room);
            redirectAttributes.addFlashAttribute("message", "Room added successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("roomDTO", roomDTO);
            return "redirect:/show-form-add-room";
        }

        return "redirect:/rooms";
    }

    @GetMapping("/delete-room/{id}")
    public String deleteRoom(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            roomService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Room deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting room: " + e.getMessage());
        }
        return "redirect:/rooms";
    }

    @GetMapping("/edit-room/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Room> optionalRoom = roomService.findById(id);
        if (optionalRoom.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Room not found.");
            return "redirect:/rooms";
        }

        Room room = optionalRoom.get();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setStatus(room.getStatus().name());
        roomDTO.setImageUrl(room.getImageUrl());
        roomDTO.setRoomTypeId(room.getRoomType() != null ? room.getRoomType().getId() : null);

        model.addAttribute("roomDTO", roomDTO);
        model.addAttribute("roomTypes", roomTypeService.findAll());
        return "dashboard/room/update_room";
    }


    @PostMapping("/update-room/{id}")
    public String updateRoom(@PathVariable("id") Integer id,
                             @Valid @ModelAttribute("roomDTO") RoomDTO roomDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("roomDTO", roomDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.roomDTO", bindingResult);
            return "redirect:/edit-room/" + id;
        }

        try {
            Room existingRoom = roomService.findByRoomNumber(roomDTO.getRoomNumber());
            if (existingRoom != null && !existingRoom.getId().equals(id)) {
                redirectAttributes.addFlashAttribute("error", "Room number already exists.");
                redirectAttributes.addFlashAttribute("roomDTO", roomDTO);
                return "redirect:/edit-room/" + id;
            }

            Room room = roomService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid room ID: " + id));

            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setStatus(Room.Status.valueOf(roomDTO.getStatus()));
            room.setImageUrl(roomDTO.getImageUrl());

            if (roomDTO.getRoomTypeId() != null) {
                RoomType roomType = roomTypeService.findById(roomDTO.getRoomTypeId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid room type ID"));
                room.setRoomType(roomType);
            } else {
                room.setRoomType(null);
            }

            roomService.save(room);
            redirectAttributes.addFlashAttribute("message", "Room updated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("roomDTO", roomDTO);
            return "redirect:/edit-room/" + id;
        }

        return "redirect:/rooms";
    }

    @GetMapping("show-form-room-type")
    public ModelAndView showRoomTypeList(@RequestParam(name = "pageRoomType", required = false, defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 4);
        return new ModelAndView("dashboard/roomType/list_room_type").addObject("listType", roomTypeService.findAllRoomType(pageable));
    }

    @GetMapping("show-form-add-room-type")
    public String showFormRoomTypeAdd(Model model) {
        RoomType roomType = new RoomType();
        model.addAttribute("roomType", roomType);
        return "dashboard/roomType/add_room_type";
    }

    @PostMapping("add-room-type")
    public String addRoomType(@ModelAttribute("roomType") RoomType roomType,
                              RedirectAttributes redirectAttributes) {
        roomTypeService.save(roomType);
        return "redirect:/show-form-room-type";
    }

    @GetMapping("/edit-room-type/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<RoomType> roomType = roomTypeService.findById(id);
        if (roomType.isPresent()) {
            model.addAttribute("roomType", roomType.get());
            return "dashboard/roomType/update_room_type";
        } else {
            return "redirect:/room-types";
        }
    }

    @PostMapping("/update-room-type/{id}")
    public String updateRoomType(@PathVariable("id") Integer id,
                                 @ModelAttribute("roomType") RoomType roomType,
                                 RedirectAttributes redirectAttributes) {
        try {
            roomType.setId(id);
            roomTypeService.save(roomType);
            redirectAttributes.addFlashAttribute("message", "Room type updated successfully!");
            return "redirect:/show-form-room-type";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating room type: " + e.getMessage());
            return "redirect:/edit-room-type/" + id;
        }
    }
}
