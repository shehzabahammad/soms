package com.soms.user_service.controller;

import com.soms.user_service.dto.UserContext;
import com.soms.user_service.util.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity getProfileDetails(HttpServletRequest request) {
        UserContext userContext = HttpUtil.getUserContext(request);
        return ResponseEntity.ok("Profile Details for User ID: " + userContext.userId());
    }
}
