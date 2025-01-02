package com.example.deviceshop.service;

import com.example.deviceshop.entity.UserEntity;
import com.example.deviceshop.model.request.UserRequest;

import java.util.UUID;

public interface UserService {
    UUID saveUser(UserRequest userRequest);
}
