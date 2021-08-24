package com.example.springboot.dto;

import com.example.springboot.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserView {

    private String id;

    private String name;
    private String password;
    private String icon;

    private String birthday;
    private String gender;
    private String phone;
    private List<Role> roles;
}