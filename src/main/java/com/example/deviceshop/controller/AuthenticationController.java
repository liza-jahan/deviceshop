package com.example.deviceshop.controller;


import com.example.deviceshop.model.request.AuthenticationRequest;
import com.example.deviceshop.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            AuthenticationRequest authRequest = new AuthenticationRequest(username, password);

            String jwtToken = authenticationService.loginUser(authRequest);

            model.addAttribute("jwtToken", jwtToken);

            return "redirect:/dashboard";
        } catch (AuthenticationException e) {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }

}


