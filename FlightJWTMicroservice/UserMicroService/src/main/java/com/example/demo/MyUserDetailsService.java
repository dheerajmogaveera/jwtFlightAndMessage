package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //here User u =repository.findByUname(username)
        User u=repo.findByUname(username);
        if(u==null)
            throw new UsernameNotFoundException("Username or Password not valid");
        return new MyUserDetails(u);
    }
    
}