package com.shahian.springBootSecurity.controller;

import com.shahian.springBootSecurity.model.LoginDTO;
import com.shahian.springBootSecurity.model.RegistrationDTO;
import com.shahian.springBootSecurity.model.User;
import com.shahian.springBootSecurity.security.JwtTokenUtil;
import com.shahian.springBootSecurity.servic.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/v1/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.userName(), loginDTO.password());
        Authentication authentication = authenticationManager.authenticate(token);
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User user = userService.getUserByUserName(userDetails.getUsername());
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        return ResponseEntity.ok().body(tokens);
    }

    @PostMapping("/v1/refresh")
    public ResponseEntity<Map<String, String>> refresh(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());
            if (jwtTokenUtil.validate(refreshToken)) {
                org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) userService.loadUserByUsername(jwtTokenUtil.getUserName(refreshToken));
                User userByUserName = userService.getUserByUserName(userDetails.getUsername());
                String accessToken = jwtTokenUtil.generateAccessToken(userByUserName);
                Map<String, String> token = new HashMap<>();
                token.put("access_token", accessToken);
                return ResponseEntity.ok().body(token);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/v1/register")
    public User register(@RequestBody RegistrationDTO registrationDTO) {
        return userService.registration(registrationDTO);
    }
}
