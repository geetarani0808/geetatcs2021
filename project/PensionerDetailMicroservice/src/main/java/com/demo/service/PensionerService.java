package com.demo.service;

import java.util.Optional;


import com.demo.bean.Pensioner;

public interface PensionerService {
	
	//GET PensionerDetailss servcie
	Optional<Pensioner> getPensionerDetailsByAaddhar(long aadharcardid);

	Pensioner addPensioner(Pensioner pensioner);//post
	//post
	Integer pensionCalculator(double basicsalary,double dearnessallowance,int workedageinorganization);

}
