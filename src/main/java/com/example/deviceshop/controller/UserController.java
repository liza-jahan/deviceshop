package com.example.deviceshop.controller;

import com.example.deviceshop.model.request.UserRequest;
import com.example.deviceshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        System.out.println("GET /register called");
        UserRequest userRequest = new UserRequest();
        System.out.println("Created UserRequest: " + userRequest);
        model.addAttribute("userRequest", userRequest);
        System.out.println("Added userRequest to model: " + userRequest);
        return "registerform";
    }

    //    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        UserRequest userRequest = new UserRequest();
//        model.addAttribute("userRequest", userRequest);
//        System.out.println("Added userRequest to model: " + userRequest);
//        return "registerform";
//    }
    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "Controller is working!";
    }

//@GetMapping("/register")
//public ModelAndView showRegistrationForm() {
//    ModelAndView modelAndView = new ModelAndView();
//    modelAndView.setViewName("registerform");
//    modelAndView.addObject("userRequest", new UserRequest());
//    return modelAndView;
//}


    // Handle form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userRequest") UserRequest userRequest, Model model) {
        try {
            userService.saveUser(userRequest);
            model.addAttribute("successMessage", "User registered successfully!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "registerform";
    }
}
