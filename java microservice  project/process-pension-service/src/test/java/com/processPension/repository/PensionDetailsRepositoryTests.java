package com.processPension.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.processPension.model.PensionAmountDetail;
import com.processPension.model.PensionerInput;
import com.processPension.repository.PensionDetailsRepository;
import com.processPension.repository.PensionerDetailsRepository;
import com.processPension.util.DateUtil;
import com.processPensionexception.GlobalExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Test class to test all the repository functionality
 * 
 *
 */
@SpringBootTest
@Slf4j
class PensionDetailsRepositoryTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(PensionDetailsRepositoryTests.class);

	@Autowired
	private PensionDetailsRepository pensionDetailsRepository;

	@Autowired
	private PensionerDetailsRepository pensionerDetailsRepository;

	@Test
	@DisplayName("This method is responsible to test save()")
	void testSave() {
		LOGGER.info("START - testSave()");

		PensionAmountDetail pensionAmountDetail = new PensionAmountDetail();
		pensionAmountDetail.setAadhaarNumber("123456789012");
		pensionAmountDetail.setBankServiceCharge(550.00);
		pensionAmountDetail.setPensionAmount(31600.00);
		pensionAmountDetail.setFinalAmount(31050.00);

		PensionAmountDetail savedDetails = pensionDetailsRepository.save(pensionAmountDetail);
		assertEquals(savedDetails.getAadhaarNumber(), pensionAmountDetail.getAadhaarNumber());
		assertEquals(savedDetails.getBankServiceCharge(), pensionAmountDetail.getBankServiceCharge());
		assertEquals(savedDetails.getPensionAmount(), pensionAmountDetail.getPensionAmount());
		assertEquals(savedDetails.getFinalAmount(), pensionAmountDetail.getFinalAmount());

		assertNotNull(savedDetails);
		LOGGER.info("END - testSave()");
	}

	@Test
	@DisplayName("This method is responsible to test save() for pensioner details")
	void testSaveForPensionerDetails() throws ParseException {
		LOGGER.info("START - testSaveForPensionerDetails()");

		PensionerInput pi_empty = new PensionerInput();
		PensionerInput pi = new PensionerInput("Shubham", DateUtil.parseDate("1999-02-02"), "BHPKN12931",
				"211228329912", "Self");

		PensionerInput savedDetails = pensionerDetailsRepository.save(pi);
		assertEquals(savedDetails.getAadhaarNumber(), pi.getAadhaarNumber());
		assertNotNull(savedDetails);
		
		LOGGER.info("END - testSaveForPensionerDetails()");
	}
}
