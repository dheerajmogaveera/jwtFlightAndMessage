package com.SpringFeignDemo.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.SpringFeignDemo.vo.JwtRequest;

@FeignClient(url = "http://localhost:8081", name = "JWT-DEMO")
public interface UserClient {
    @GetMapping("/")
    public String getHi(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
//    
//    @GetMapping("/users/{id}")
//    public UserVO getUserById(@RequestParam int id);

    @PostMapping("/getToken")
	public ResponseEntity<?> tokenCall(@RequestBody JwtRequest authenticationRequest);
    
//    @PutMapping("/users")
//    public ResponseEntity<String> putUser(UserVO user);
//    
//    @DeleteMapping("/users/{id}")
//    public UserVO delById(@RequestParam int id);
    
}