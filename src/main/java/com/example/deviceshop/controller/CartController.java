package com.example.deviceshop.controller;

import com.example.deviceshop.dto.ProductDto;
import com.example.deviceshop.entity.CartEntity;
import com.example.deviceshop.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class CartController {


        private final CartService cartService;
        @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
            List<CartEntity> cartList = cartService.getAllCartItems();

            model.addAttribute("cartList", cartList);
            return "viewCart";
    }


//    // Add product to cart
        @PostMapping("/cart/add")
        public String addCart( @RequestParam Long productId, Model model) {
                UUID cartId = cartService.addCart( productId);

            model.addAttribute("cartId", cartId);
                return "cartSuccess";

        }


        // Remove product from cart
        @PostMapping("/remove")
        public String removeProductFromCart(@RequestParam("productId") UUID id, Model model) {
            cartService.removeCart(id);
            model.addAttribute("message", "Product removed from cart successfully!");
            return "redirect:/cart";
        }


}


