package com.example.demo.controller;

import com.example.demo.model.VehicleAppUser;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserinfoController {

    private UserRepository users;

    @GetMapping("/me")
    public String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        VehicleAppUser user = users.findByUsername((String) authentication.getPrincipal())
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + authentication.getPrincipal() + " not found"));
        return user.getUsername() + "\n" + user.getRoles();
    }
}
