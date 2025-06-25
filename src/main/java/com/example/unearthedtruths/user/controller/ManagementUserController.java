package com.example.unearthedtruths.user.controller;

import com.example.unearthedtruths.user.service.JwtService;
import com.example.unearthedtruths.user.service.ManagementUserService;
import com.example.unearthedtruths.user.dto.ManagementUserLoginRequest;
import com.example.unearthedtruths.user.dto.ManagementUserRequest;
import com.example.unearthedtruths.user.dto.ManagementUserResponse;
import com.example.unearthedtruths.user.model.ManagementUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ManagementUserController {

    private final ManagementUserService managementUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("register")
    public ResponseEntity<ManagementUserResponse> register(@RequestBody ManagementUserRequest user) {
        System.out.println("REGISTER CONTROLLER HIT");

        user.setPassword(encoder.encode(user.getPassword()));

        return managementUserService.addManagementUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody ManagementUserLoginRequest userLoginRequest) {
        System.out.println("LOGIN CONTROLLER HIT");

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));

        String role = managementUserService.getRole(userLoginRequest.getUsername());
        String token = jwtService.generateToken(userLoginRequest.getUsername(), role);

        System.out.println("Token: " + token);

        if (authentication.isAuthenticated()) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("admins")
    public ResponseEntity<List<ManagementUser>> getAllAdmins() {
        return managementUserService.getAllAdmins();
    }

}
