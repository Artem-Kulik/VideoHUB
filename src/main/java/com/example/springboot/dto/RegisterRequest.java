package com.example.springboot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RegisterRequest {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String re_password;
    @NotNull
    private String phone;
    @NotNull
    private Date birthday;
    @NotNull
    private String gender;
}
