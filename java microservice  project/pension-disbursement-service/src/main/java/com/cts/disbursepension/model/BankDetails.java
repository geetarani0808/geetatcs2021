package com.cts.disbursepension.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Model class for bank details
 * 
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
