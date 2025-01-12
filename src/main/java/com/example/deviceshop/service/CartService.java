package com.example.deviceshop.service;

import com.example.deviceshop.entity.CartEntity;
import jakarta.servlet.http.HttpSession;

import java.util.UUID;

public interface CartService {
    UUID addCart(UUID userId, Long productId);
    void removeCart(UUID userId, Long productId);
    CartEntity getCartFromSession(HttpSession session);
}
