package com.example.deviceshop.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private  String name;
    private  String email;
    private  String phoneNumber;
    private  String password;
  //  private  String confirmPassword;


}
