package com.example.deviceshop.controller;

import com.example.deviceshop.dto.ProductDto;
import com.example.deviceshop.entity.CartEntity;
import com.example.deviceshop.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@AllArgsConstructor
public class CartController {


        private final CartService cartService;
        @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        CartEntity cart = cartService.getCartFromSession(session);
        model.addAttribute("cart", cart); // Add the cart object to the model
        return "viewCart";
    }


    // Add product to cart
        @PostMapping("/add")
        public String addCart(@RequestParam UUID userId, @RequestParam Long productId, Model model) {
                UUID cartId = cartService.addCart(userId, productId);
                model.addAttribute("cartId", cartId);
                return "cartSuccess";

        }

        // Remove product from cart
        @PostMapping("/remove")
        public String removeCart(@RequestParam UUID userId, @RequestParam Long productId, Model model) {
            try {
                cartService.removeCart(userId, productId);
                model.addAttribute("message", "Product removed from cart successfully.");
                return "cartSuccess";
            } catch (Exception ex) {
                model.addAttribute("error", "Error: " + ex.getMessage());
                return "error"; // Return error page if an exception occurs
            }
        }
    }


