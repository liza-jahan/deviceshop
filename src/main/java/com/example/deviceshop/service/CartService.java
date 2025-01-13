package com.example.deviceshop.service;

import com.example.deviceshop.entity.CartEntity;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.UUID;

public interface CartService {
    UUID addCart( Long productId);
    void removeCart( UUID id);
    List<CartEntity> getAllCartItems();}

