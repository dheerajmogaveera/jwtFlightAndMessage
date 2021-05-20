package com.example.demo;

import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public class MyUserDetails implements UserDetails {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private User user;
    public MyUserDetails(User u) {
        user=u;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        //return null;
        return Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getUname();
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}