package com.processPension.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.processPension.model.ProcessPensionInput;
import com.processPension.model.ProcessPensionResponse;
import com.processPensionexception.InvalidTokenException;

/**
 * Feign client to connect with Pension disbursement micro-service
 * 
 *
 */
@FeignClient("PENSION-DISBURSEMENT-SERVICE")
public interface PensionDisbursementClient {
	@PostMapping("/DisbursePension")
	public ProcessPensionResponse disbursePension(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput) throws InvalidTokenException;
}