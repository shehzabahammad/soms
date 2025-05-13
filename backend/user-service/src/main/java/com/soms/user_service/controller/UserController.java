package com.soms.user_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity getProfileDetails(@PathVariable String id, HttpServletRequest request) {
        request.getHeader("X-User-Id");
        return ResponseEntity.ok("Profile Details for User ID: " + id);
    }
}
