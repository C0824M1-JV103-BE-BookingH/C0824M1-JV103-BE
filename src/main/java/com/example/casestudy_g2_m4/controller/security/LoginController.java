package com.example.casestudy_g2_m4.controller.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class LoginController {

    @GetMapping("/login")
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
}