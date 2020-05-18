package com.serviceoauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    //登录成功后重定向地址
    @RequestMapping("/hello")
    public String loginSuccess() {
        return "登录成功";
    }

    @GetMapping(value = "/r/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1() {
        return " 访问资源1";
    }


    @GetMapping(value = "/r/r2")
    @PreAuthorize("hasAuthority('p2')")
    public String r2() {
        return " 访问资源2";
    }

    @GetMapping(value = "/r/r3")
    @PreAuthorize("hasAuthority('p3')")
    public String r3() {
        return " 访问资源3";
    }
}