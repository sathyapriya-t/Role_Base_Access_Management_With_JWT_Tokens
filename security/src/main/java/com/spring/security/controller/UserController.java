package com.spring.security.controller;

import com.spring.security.service.UserInfoService;
import com.spring.security.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserInfoService service;

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo){
        System.out.println(userInfo);
        return service.register(userInfo);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public Iterable<UserInfo> userInfos(){
        return service.getUsers();
    }

}