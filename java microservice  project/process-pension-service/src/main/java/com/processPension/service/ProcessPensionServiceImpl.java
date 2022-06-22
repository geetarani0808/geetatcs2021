package com.processPension.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.processPension.feign.PensionDisbursementClient;
import com.processPension.feign.PensionerDetailsClient;
import com.processPension.model.PensionAmountDetail;
import com.processPension.model.PensionDetail;
import com.processPension.model.PensionerDetail;
import com.processPension.model.PensionerInput;
import com.processPension.model.ProcessPensionInput;
import com.processPension.model.ProcessPensionResponse;
import com.processPension.repository.PensionDetailsRepository;
import com.processPension.repository.PensionerDetailsRepository;
import com.processPensionexception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class for Process Pension
 * 
 *
 */
@Service
@Slf4j
public class ProcessPensionServiceImpl implements IProcessPensionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPensionServiceImpl.class);

	@Autowired
	private PensionerDetailsClient pensionerDetailClient;

	@Autowired
	private PensionDisbursementClient pensionDisbursementClient;

	@Autowired
	private PensionDetailsRepository pensionDetailsRepository;

	@Autowired
	private PensionerDetailsRepository pensionerDetailsRepository;

	/**
	 * This method is responsible to get the pension details if input details are
	 * valid
	 * 
	 * @param pensionerInput
	 * @return Verified Pension Detail with pension amount
	 */
	@Override
	public PensionDetail getPensionDetails(PensionerInput pensionerInput) {
		

		// get the pensioner details from the detail micro-service
		PensionerDetail pensionerDetail = pensionerDetailClient
				.getPensionerDetailByAadhaar(pensionerInput.getAadhaarNumber());

		LOGGER.info("Details found by details microservice");

		// check if the entered details are correct
		if (checkdetails(pensionerInput, pensionerDetail)) {
			// save the input pensioner details into the database
			pensionerDetailsRepository.save(pensionerInput);
			// calculate the amount and return the pension detail object
			return calculatePensionAmount(pensionerDetail);
		} else {
			throw new NotFoundException("Details entered are incorrect");
		}
	}

	/**
	 * Calculate the pension amount and return the pensioner details according to
	 * the type of pension "self" or "family"
	 * 
	 * 
	 * @param Verified Pensioner Details
	 * @return Pension Details with Pension amount
	 */
	@Override
	public PensionDetail calculatePensionAmount(PensionerDetail pensionDetail) {
		double pensionAmount = 0;
		if (pensionDetail.getPensionType().equalsIgnoreCase("self"))
			pensionAmount = (pensionDetail.getSalary() * 0.8 + pensionDetail.getAllowance());
		else if (pensionDetail.getPensionType().equalsIgnoreCase("family"))
			pensionAmount = (pensionDetail.getSalary() * 0.5 + pensionDetail.getAllowance());
		return new PensionDetail(pensionDetail.getName(), pensionDetail.getDateOfBirth(), pensionDetail.getPan(),
				pensionDetail.getPensionType(), pensionAmount);
	}

	/**
	 * Method to check the details entered by the user
	 * 
	 * @Data {"aadhaarNumber":"123456789012","pensionAmount":31600,"bankServiceCharge":550}
	 * @param PensionerInput
	 * @param PensionerDetail
	 * @return true if details match, else false
	 */
	@Override
	public boolean checkdetails(PensionerInput pensionerInput, PensionerDetail pensionerDetail) {
		return (pensionerInput.getName().equalsIgnoreCase(pensionerDetail.getName())
				&& (pensionerInput.getDateOfBirth().compareTo(pensionerDetail.getDateOfBirth()) == 0)
				&& pensionerInput.getPan().equalsIgnoreCase(pensionerDetail.getPan())
				&& pensionerInput.getPensionType().equalsIgnoreCase(pensionerDetail.getPensionType()));
	}

	/**
	 * Method to get status code from the disbursement micro-service
	 * 
	 *
	 * @param token               Authentication JWT Token
	 * @param processPensionInput Processing input given by user
	 * @return status code: {10: Pension Disbursed, 21: Invalid Input}
	 */
	@Override
	public ProcessPensionResponse processPension(String token, ProcessPensionInput processPensionInput) {
		int hitCounter = 0;
		ProcessPensionResponse pensionResponse = pensionDisbursementClient.disbursePension(token, processPensionInput);

		// retry the disbursement 2 more times if status code is 21
		while (pensionResponse.getProcessPensionStatusCode() == 21 && hitCounter < 2) {
			LOGGER.debug("Hitting the disbursement service again...");
			pensionResponse = pensionDisbursementClient.disbursePension(token, processPensionInput);
			++hitCounter;
		}

		// if response is 10, then we store the amount details in the database 
		pensionDetailsRepository.save(new PensionAmountDetail(
				processPensionInput.getAadhaarNumber(), 
				processPensionInput.getPensionAmount(),
				processPensionInput.getBankServiceCharge(),
				processPensionInput.getPensionAmount() - processPensionInput.getBankServiceCharge()));
		
		return pensionResponse;
	}
}