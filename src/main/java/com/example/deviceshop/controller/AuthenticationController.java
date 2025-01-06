package com.example.deviceshop.controller;


import com.example.deviceshop.model.request.AuthenticationRequest;
import com.example.deviceshop.model.response.AuthenticationResponse;
import com.example.deviceshop.service.AuthenticationService;
import com.example.deviceshop.utils.ApplicationConstantsUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Returns the login.html page (if using Thymeleaf)
    }

    // Handle POST request for login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            AuthenticationRequest authRequest = new AuthenticationRequest(username, password);

            String jwtToken = authenticationService.loginUser(authRequest);

            model.addAttribute("jwtToken", jwtToken);

            return "redirect:/dashboard";  // Redirects to the dashboard after successful login
        } catch (AuthenticationException e) {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";  // Re-renders the login page with an error message
        }
    }
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";  // Renders dashboard.html
    }

}


