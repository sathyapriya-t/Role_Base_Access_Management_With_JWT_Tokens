package com.spring.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class userInfoUserDetails implements UserDetails {

    private int id;
    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;


    public userInfoUserDetails(UserInfo userInfo) {
        this.userName = userInfo.getName();
        this.password = userInfo.getPwd();
        this.id = userInfo.getId();
        this.authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(val -> new SimpleGrantedAuthority("ROLE_" + val))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
