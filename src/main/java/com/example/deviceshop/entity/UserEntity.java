package com.example.deviceshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity  {
    private  String name;
    private  String email;
    private  String phoneNumber;
    private  String password;
   // private  String confirmPassword;

}
