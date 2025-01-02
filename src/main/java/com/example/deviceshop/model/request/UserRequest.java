package com.example.deviceshop.model.response;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private  String name;
    private  String email;
    private  String phoneNumber;
    private  String password;
    private  String confirmPassword;
}
