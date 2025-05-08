package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.User;
import com.example.casestudy_g2_m4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String viewProfile(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user/view_profile";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user/edit_profile";
    }

    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute User updatedUser,
                                @RequestParam(required = false) String newPassword,
                                Principal principal) {
        User existingUser = userService.findByEmail(principal.getName());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        if (newPassword != null && !newPassword.isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
        }
        userService.save(existingUser);
        return "redirect:/profile";
    }
}
