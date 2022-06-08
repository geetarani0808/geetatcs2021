package com.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Pensioner;
import com.demo.service.PensionerService;

@RestController
public class PensionerController {
	
	@Autowired PensionerService pensionerservice;
	
	@GetMapping(value = "/PensionerDetailsByAaddhar/{aadharcardid}")
	Optional<Pensioner> getPensioerDetailsByAaddhar(@RequestBody Pensioner pensioner,@PathVariable long aadharcardid){
	Optional<Pensioner>	pen= pensionerservice.getPensionerDetailsByAaddhar(aadharcardid);
		return pen;

	}



    @PostMapping(value = "/add")
	Pensioner addPensioner(@RequestBody Pensioner pensioner ) {

		Pensioner p = pensionerservice.addPensioner(pensioner);
		return p;
    }
    
    }