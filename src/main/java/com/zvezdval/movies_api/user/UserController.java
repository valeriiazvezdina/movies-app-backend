package com.zvezdval.movies_api.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isLoggedIn = auth != null &&
                auth.isAuthenticated() &&
                !(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"));

        return ResponseEntity.ok(Map.of("loggedIn", isLoggedIn));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto payload) {
        try {
            UserDto user = userService.login(payload.email(), payload.password());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto payload) {
        try {
            userService.register(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return ResponseEntity.ok().body("Logged out");
    }
}