package com.serviceoauth.entity;

import lombok.Data;

@Data
public class Users {

    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;


}