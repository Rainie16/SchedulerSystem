package com.mercury.SchedulerSystem.controller;

import com.mercury.SchedulerSystem.http.Response;
import com.mercury.SchedulerSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/checklogin")
    public Response checklogin(Authentication authentication) { return authService.checklogin(authentication); }
}
