package com.example.casestudy_g2_m4.service.security;

import com.example.casestudy_g2_m4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = accountService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email);
        }

        // Kiểm tra trạng thái tài khoản
        if (user.getStatus() != User.Status.active) {
            throw new UsernameNotFoundException("Tài khoản không hoạt động");
        }

        // Tạo quyền dựa trên role
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name().toUpperCase()));

        // Tạo UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}