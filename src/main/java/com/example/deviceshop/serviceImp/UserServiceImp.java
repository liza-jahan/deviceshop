package com.example.deviceshop.serviceImp;


import com.example.deviceshop.entity.UserEntity;
import com.example.deviceshop.model.request.UserRequest;
import com.example.deviceshop.repository.UserRepository;
import com.example.deviceshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private  final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UUID saveUser(UserRequest userRequest) {
        if (isUserExists(userRequest.getEmail())) {
            throw new IllegalArgumentException("User not found");
        }
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

        UserEntity userEntity=new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(encodedPassword);
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setCreatedTime(new Date());
        userRepository.save(userEntity);

        return userEntity.getId();
    }

    private boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
