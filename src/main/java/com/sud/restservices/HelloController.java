package com.sud.restservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping(method=RequestMethod.GET, path="/hello")
	public String hello() {
		return "Hello Sudi";
	}
	@GetMapping("/helloUser")
	public UserDetails helloworldBean() {
		return new UserDetails("firstName1", "lastName1", "city1");
	}
}
