package com.example.casestudy_g2_m4.controller.security;

import com.example.casestudy_g2_m4.model.User;
import com.example.casestudy_g2_m4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login(@RequestParam(value = "lang", required = false) String lang) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            String redirectUrl;
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                redirectUrl = "/dashboard";
            } else {
                redirectUrl = "/";
            }
            // Preserve the lang parameter in the redirect URL
            if (lang != null) {
                redirectUrl = ServletUriComponentsBuilder.fromPath(redirectUrl)
                        .queryParam("lang", lang)
                        .build()
                        .toUriString();
            }
            return "redirect:" + redirectUrl;
        }
        return "login_page";
    }
    @GetMapping("register")
    public String register(){
        return "register";
    }
    @PostMapping("register")
    public String registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String phone,
            Model model) {

        // Kiểm tra xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp!");
            return "register";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRole(User.Role.customer);
        user.setStatus(User.Status.active);

        // Lưu user
        userService.saveUser(user);

        return "redirect:/login";
    }
}