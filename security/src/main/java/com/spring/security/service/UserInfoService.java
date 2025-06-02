package com.spring.security.service;

import com.spring.security.repo.UserInfoRepo;
import com.spring.security.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(UserInfo userInfo) {
        userInfo.setPwd(passwordEncoder.encode(userInfo.getPwd()));
        repo.save(userInfo);
        return "User registered";
    }

    public Iterable<UserInfo> getUsers() {
        return repo.findAll();
    }
}
