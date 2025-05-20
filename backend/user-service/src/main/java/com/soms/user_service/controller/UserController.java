package com.soms.user_service.controller;

import com.soms.user_service.dto.UserContext;
import com.soms.user_service.service.UserService;
import com.soms.user_service.util.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfileDetails(HttpServletRequest request) {
        UserContext userContext = HttpUtil.getUserContext(request);
        var resp = this.userService.getUserByCredentialId(userContext.userId());
        return ResponseEntity.ok(resp);
    }
}
