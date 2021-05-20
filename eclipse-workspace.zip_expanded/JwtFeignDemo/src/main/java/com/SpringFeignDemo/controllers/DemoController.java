package com.SpringFeignDemo.controllers;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringFeignDemo.feignclients.UserClient;
import com.SpringFeignDemo.vo.JwtRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class DemoController {
	@Autowired
    private UserClient client;
//    @GetMapping("/getDemo")
//    public List<UserVO> getAllUsers(){
//        return client.getUsers();
//    }
    
	
    @PostMapping("/token")
    public String tokenCall(@RequestBody JwtRequest authenticationRequest) throws ParseException {
    	System.out.println(client.tokenCall(authenticationRequest).getBody());
    	JsonParser parser = new JsonParser();
    	String s = client.tokenCall(authenticationRequest).getBody().toString();
    	System.out.println(s);
    	JsonElement jsonTree = parser.parse(s);
    	
    	JsonObject jsonObject = jsonTree.getAsJsonObject();
    	String authorizationHeader = "Bearer "+jsonObject.get("token").getAsString();
    	System.out.println(authorizationHeader);
    	String output = client.getHi(authorizationHeader);
        return output;
    }
    

//    
//    @DeleteMapping("/delete/{id}")
//    public UserVO deleteUSer(@PathVariable int id) {
//    	return client.delById(id);
//    }
}
