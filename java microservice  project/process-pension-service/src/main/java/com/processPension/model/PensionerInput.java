package com.processPension.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Model class for pensioner input, given by the user
 * 
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PensionerInput {

	@Column
	@NotBlank(message = "Name cannot be blank")
	private String name;

	public PensionerInput(String name, Date parseDate, String string2, String string3, String string4) {
		// TODO Auto-generated constructor stub
	}

	public PensionerInput() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
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

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getPensionType() {
		return pensionType;
	}

	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private Date dateOfBirth;

	@Column
	@NotNull(message = "PAN Number cannot be null")
	@NotBlank(message = "PAN Number cannot be blank")
	private String pan;

	@Id
	@Pattern(regexp = "[0-9]{12}", message = "Aadhaar Number is in invalid format")
	private String aadhaarNumber;

	@Column
	@NotBlank(message = "Pension Type cannot be blank")
	private String pensionType;

}