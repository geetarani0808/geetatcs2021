package com.cts.disbursepension.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.disbursepension.feign.PensionerDetailsClient;
import com.cts.disbursepension.model.BankCharges;
import com.cts.disbursepension.model.PensionerDetail;
import com.cts.disbursepension.model.ProcessPensionInput;
import com.cts.disbursepension.model.ProcessPensionResponse;
import com.cts.disbursepension.repository.BankChargesRepository;


import lombok.extern.slf4j.Slf4j;

/**
 * Implementation for DisbursePensionService
 * 
 *
 */
@Service
@Slf4j
public class DisbursePensionServiceImpl implements IDisbursePensionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DisbursePensionServiceImpl.class);

	@Autowired
	BankChargesRepository bankChargesRepository;

	@Autowired
	PensionerDetailsClient pensionerDetailsClient;

	@Override
	public double findBankCharges(String bankType) {
		LOGGER.debug("Start - findBankCharges");
		BankCharges bankCharges = bankChargesRepository.findByBankType(bankType.toLowerCase()).get(0);
		LOGGER.debug("End - findBankCharges");
		return bankCharges.getCharges();
	}

	@Override
	public ProcessPensionResponse verifyPension(ProcessPensionInput processPensionInput) {
		LOGGER.debug("Start - verifyPension");
		PensionerDetail pensionerDetail = getPensionerDetail(processPensionInput.getAadhaarNumber());
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		if (verifyPensionAmount(pensionerDetail, processPensionInput.getPensionAmount()) && verifyBankCharges(
				pensionerDetail.getBank().getBankType(), processPensionInput.getBankServiceCharge())) {
			processPensionResponse.setProcessPensionStatusCode(10);
		} else {
			processPensionResponse.setProcessPensionStatusCode(21);
		}
		LOGGER.debug("End - verifyPension");
		return processPensionResponse;
	}

	@Override
	public boolean verifyPensionAmount(PensionerDetail pensionerDetails, double pensionAmount) {
		LOGGER.debug("Start verifyPensionAmount");
		double expectedAmount = 0;

		// calculating Pension Amount
		if (pensionerDetails.getPensionType().equalsIgnoreCase("Self")) {
			expectedAmount = (0.80 * pensionerDetails.getSalary()) + pensionerDetails.getAllowance();
		} else if (pensionerDetails.getPensionType().equalsIgnoreCase("Family")) {
			expectedAmount = (0.50 * pensionerDetails.getSalary()) + pensionerDetails.getAllowance();
		}
		LOGGER.debug("Expected Amount is {}", expectedAmount);
		LOGGER.debug("End - verifyPensionAmount");

		return expectedAmount == pensionAmount;
	}

	@Override
	public boolean verifyBankCharges(String bankType, double bankCharges) {
		LOGGER.debug("Start - verifyBankCharges");
		LOGGER.debug("End - verifyBankCharges");
		return findBankCharges(bankType) == bankCharges;
	}

	@Override
	public PensionerDetail getPensionerDetail(String aadhaarNumber) {
		LOGGER.debug("Start - getPensionDetail");
		LOGGER.debug("End - getPensionerDetail()");
		return pensionerDetailsClient.pensionerDetailByAadhaar(aadhaarNumber);
	}

}
