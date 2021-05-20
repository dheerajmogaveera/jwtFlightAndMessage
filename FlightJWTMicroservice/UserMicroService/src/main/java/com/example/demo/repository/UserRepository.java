package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUname(String Uname);
    User findByUnameAndPassword(String Uname,String Password);
}