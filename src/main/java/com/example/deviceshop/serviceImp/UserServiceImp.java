package com.example.deviceshop.serviceImp;


import com.example.deviceshop.entity.UserEntity;
import com.example.deviceshop.exception.IdentifierExistException;
import com.example.deviceshop.model.request.UserRequest;
import com.example.deviceshop.repository.UserRepository;
import com.example.deviceshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.deviceshop.utils.ErrorDetails.ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private  final UserRepository userRepository;
    @Override
    public UUID saveUser(UserRequest userRequest) {
        if (isUserExists(userRequest.getEmail())) {
            throw new IdentifierExistException(ALREADY_EXISTS);
        }
        UserEntity userEntity=new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userRepository.save(userEntity);

        return userEntity.getId();
    }

    private boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
