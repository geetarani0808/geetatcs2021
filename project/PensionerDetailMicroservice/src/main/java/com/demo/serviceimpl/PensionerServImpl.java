package com.demo.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Pensioner;
import com.demo.repo.PensionserRepo;
import com.demo.service.PensionerService;

@Service
public class PensionerServImpl implements PensionerService{
	@Autowired
	PensionserRepo pensionserRepo;

	@Override
	public Optional<Pensioner> getPensionerDetailsByAaddhar(long aadharcardid) {
		Optional<Pensioner> pensionerget =pensionserRepo.findById(aadharcardid);

		return pensionerget;
	}
	
	
	@Override
	
	public Pensioner addPensioner(Pensioner pensioner) {
		
		Pensioner details = pensionserRepo.save(pensioner);
		return details;
	}


	@Override
	public Integer pensionCalculator(double basicsalary, double dearnessallowance, int workedageinorganization) {
		// TODO Auto-generated method stub
		return null;
	}
	

    
}
