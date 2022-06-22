package com.processPensionController;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.processPension.feign.AuthorisationClient;
import com.processPension.model.PensionDetail;
import com.processPension.model.PensionerInput;
import com.processPension.model.ProcessPensionInput;
import com.processPension.model.ProcessPensionResponse;
import com.processPension.service.ProcessPensionServiceImpl;
import com.processPensionexception.GlobalExceptionHandler;
import com.processPensionexception.InvalidTokenException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ProcessPensionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPensionController.class);
	@Autowired
	ProcessPensionServiceImpl processPensionService;

	@Autowired
	AuthorisationClient authorisationClient;

	/**
	 * @URL: http://localhost:8082/pensionerInput
	 * @Input: { "aadhaarNumber": "123456789012", "dateOfBirth": "1956-09-12",
	 *         "name": "Achyuth", "pan": "BHMER12436", "pensionType": "self" }
	 * @param pensionerInput
	 * @return
	 */
	@PostMapping("/pensionerInput")
	public ResponseEntity<PensionDetail> getPensionDetails(@RequestHeader(name = "Authorization") String token,
			@RequestBody @Valid PensionerInput pensionerInput) {
		LOGGER.info("START - getPensionDetails()");
		if (!authorisationClient.validate(token)) {
			throw new InvalidTokenException("You are not allowed to access this resource");
		}
		LOGGER.info("END - getPensionDetails()");
		return new ResponseEntity<>(processPensionService.getPensionDetails(pensionerInput), HttpStatus.OK);
	}

	/**
	 * @URL: http://localhost:8082/processPension
	 * @param token
	 * @param processPensionInput {"aadhaarNumber":"123456789012","pensionAmount":31600,"bankServiceCharge":550}
	 * @return status code indicating whether the process was success or not
	 */
	@PostMapping("/processPension")
	public ResponseEntity<ProcessPensionResponse> processPension(@RequestHeader(name = "Authorization") String token,
			@RequestBody @Valid ProcessPensionInput processPensionInput) {
		LOGGER.info("START - processPension()");

		if (!authorisationClient.validate(token)) {
			throw new InvalidTokenException("You are not allowed to access this resource");
		}
		LOGGER.info("END - processPension()");
		return new ResponseEntity<>(processPensionService.processPension(token, processPensionInput), HttpStatus.OK);
	}

}