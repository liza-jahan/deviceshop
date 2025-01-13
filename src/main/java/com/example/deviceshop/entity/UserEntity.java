package com.example.deviceshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
           name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private Set<RoleEntity> roles = new HashSet<>();


    public UserEntity(UUID id) {
        super(id, null); // Pass 'id' to the BaseEntity constructor
    }
}
