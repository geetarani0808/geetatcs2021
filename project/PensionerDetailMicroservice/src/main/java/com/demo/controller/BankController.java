package com.demo.controller;

import com.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Bank;


@RestController
public class BankController {
	
	@Autowired 
	BankService bankservice;
	
	@PostMapping(value="/adddbank")
	
	Bank addBank(@RequestBody Bank bank) {
		
		return bankservice.addBankdetails(bank);
		
	}

}
