package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo")
	String demomethod()
	{
		return "you are working in spring - boot demo app";
	}
	@GetMapping("/welcome")
	String welcomeMessage()
	{
		return "Welcome to Spring Boot Project World!!";
	}
}
