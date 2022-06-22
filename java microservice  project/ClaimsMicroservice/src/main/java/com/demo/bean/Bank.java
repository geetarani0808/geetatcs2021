package com.demo.bean;


public class Bank {
	
private long id;
private String bankname;
private String banktype;
private double BankServiceCharge;


public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getBankname() {
	return bankname;
}
public void setBankname(String bankname) {
	this.bankname = bankname;
}
public String getBanktype() {
	return banktype;
}
public void setBanktype(String banktype) {
	this.banktype = banktype;
}
public double getBankServiceCharge() {
	return BankServiceCharge;
}
public void setBankServiceCharge(double bankServiceCharge) {
	BankServiceCharge = bankServiceCharge;
}


}
