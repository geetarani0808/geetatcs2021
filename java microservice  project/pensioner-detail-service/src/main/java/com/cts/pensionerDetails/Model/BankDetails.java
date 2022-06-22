package com.cts.pensionerDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This is BankDetail class which contains the Bank details
 * like bankName, bankType, accountNumber
 *
 */

@AllArgsConstructor
@Getter
public class BankDetails {
	
	private String bankName;
	
	public String getBankName() {
		return bankName;
	}
	public BankDetails(String bankName, long accountNumber, String bankType) {
		super();
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.bankType = bankType;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	private long accountNumber;
	private String bankType;
	

}
