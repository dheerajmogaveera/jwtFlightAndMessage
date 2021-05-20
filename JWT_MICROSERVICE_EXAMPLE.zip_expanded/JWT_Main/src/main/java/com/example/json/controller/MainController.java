package com.example.json.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import com.example.json.model.UserVo;
import java.net.URI;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class MainController {

	@Autowired
	RestTemplate restTemplate;
	HttpHeaders reqHeaders;
	String uri = null;

	@GetMapping("/login")
	@HystrixCommand(fallbackMethod = "someFailure")
	public String getLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	@HystrixCommand(fallbackMethod = "someFailure")
	public String getLoginPage(@ModelAttribute UserVo user, ModelMap model) {
		System.out.println(user.getUsername());
		uri = "http://USER-SERVICE/getToken";
		String result = restTemplate.postForObject(uri, user, String.class);
		if (result != null) {
			String uri = "http://USER-SERVICE/";
			System.out.println("RESULT : " + result);
			reqHeaders = new HttpHeaders();
			reqHeaders.setBearerAuth(result);

			HttpEntity request = new HttpEntity(reqHeaders);

			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

			if (response != null)
				return "welcome";

		}
		model.addAttribute("result", result);
		return result;

	}

	@GetMapping("/option")
	public String welcome() {

		uri = "http://USER-SERVICE/";
		return restTemplate.getForObject(uri, String.class);
	}

	public String someFailure() {
		return "error";
	}

	public String someFailure(@ModelAttribute UserVo user, ModelMap model) {
		return "error";
	}

}