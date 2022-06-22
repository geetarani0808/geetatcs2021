package com.cts.pensionerDetails.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**			This is the Model package PensionerDetails class
 *         which consist of pensioner details like Name, Date of Birth, PAN,
 *         Salary, Allowance Pension Type, Bank Details.
 *
 *         In lombok is used to generate setters, getters and all arguments
 *         constructors
 *
 */
@Getter
@AllArgsConstructor
public class PensionerDetails {

	private String name;
	
	public String getName() {
		return name;
	}
	public PensionerDetails(String name, Date dateOfBirth, String pan, double salary, double allowance,
			String pensionType, BankDetails bank) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.pan = pan;
		this.salary = salary;
		this.allowance = allowance;
		this.pensionType = pensionType;
		this.bank = bank;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getAllowance() {
		return allowance;
	}
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	public String getPensionType() {
		return pensionType;
	}
	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}
	public BankDetails getBank() {
		return bank;
	}
	public void setBank(BankDetails bank) {
		this.bank = bank;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "YYYY-MM-dd" , timezone="IST")
	private Date dateOfBirth;
	private String pan;
	private double salary;
	private double allowance;
	private String pensionType;
	private BankDetails bank;

}
