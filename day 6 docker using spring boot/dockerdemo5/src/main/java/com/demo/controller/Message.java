package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Message {
	
	@GetMapping(value="/thismyendpoint")
	String showMessage() {
		return"  this is my  first example of docker";
		
	}

}
