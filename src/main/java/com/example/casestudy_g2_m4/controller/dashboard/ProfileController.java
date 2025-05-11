package com.example.casestudy_g2_m4.controller.dashboard;

import com.example.casestudy_g2_m4.model.User;
import com.example.casestudy_g2_m4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Trang xem thông tin cá nhân (người dùng thường)
    @GetMapping()
    public String viewProfile(Model model) {
        List<User> user = userService.findAllUsers();
        model.addAttribute("users", user);
        return "user/view_profile";
    }

    // Trang sửa thông tin cá nhân (người dùng thường)
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Optional<User> user = userService.findById(id);
<<<<<<< HEAD
            model.addAttribute("users", user.get());
            return "user/edit_profile";
    }
    @Transactional
    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute("users") User user) {
        // Nếu trường role hoặc status là null, giữ nguyên giá trị cũ
        User currentUser = userService.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getRole() == null) {
            user.setRole(currentUser.getRole());
        }
        if (user.getStatus() == null) {
            user.setStatus(currentUser.getStatus());
        }
        System.out.println(user);
        userService.saveUser(user); // Lưu thông tin người dùng
        System.out.println("Update thanh cong");
        return "redirect:/profile"; // Quay lại trang thông tin cá nhân sau khi cập nhật
    }
    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        User user = new User();
        user.setStatus(User.Status.active); // Đặt trạng thái mặc định
        model.addAttribute("user", user);
        return "user/add_user"; // Trỏ đến file add_user.html
    }

    // Xử lý dữ liệu sau khi submit form
    @Transactional
    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/profile"; // Quay về trang danh sách người dùng (nếu có)
    }
=======
        model.addAttribute("users", user.get());
        return "user/edit_profile";
    }
    @Transactional
    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute("users") User user) {
        // Nếu trường role hoặc status là null, giữ nguyên giá trị cũ
        User currentUser = userService.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getRole() == null) {
            user.setRole(currentUser.getRole());
        }
        if (user.getStatus() == null) {
            user.setStatus(currentUser.getStatus());
        }
        System.out.println(user);
        userService.saveUser(user); // Lưu thông tin người dùng
        System.out.println("Update thanh cong");
        return "redirect:/profile"; // Quay lại trang thông tin cá nhân sau khi cập nhật
    }
    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        User user = new User();
        user.setStatus(User.Status.active); // Đặt trạng thái mặc định
        model.addAttribute("user", user);
        return "user/add_user"; // Trỏ đến file add_user.html
    }

    // Xử lý dữ liệu sau khi submit form
    @Transactional
    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/profile"; // Quay về trang danh sách người dùng (nếu có)
    }
>>>>>>> d6544d0 (merge full code VanTai, VietTai, Nguyen, Dat, Hoang)




<<<<<<< HEAD
}
=======
}
>>>>>>> d6544d0 (merge full code VanTai, VietTai, Nguyen, Dat, Hoang)
