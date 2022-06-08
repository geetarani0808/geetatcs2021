package com.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Bank;
import com.demo.repo.BankRepo;
import com.demo.service.BankService;

@Service
public class BankServiceImpl implements BankService{
	@Autowired
	BankRepo bankRepo;

	@Override
	public Bank addBankdetails(Bank bank) {
		
		return bankRepo.save(bank);
	}

	
	
	






	/*@Override
	public Integer pensionCalculator(double basicsalary, double dearnessallowance, int workedageinorganization) {
	
		return null;
	} */

}
