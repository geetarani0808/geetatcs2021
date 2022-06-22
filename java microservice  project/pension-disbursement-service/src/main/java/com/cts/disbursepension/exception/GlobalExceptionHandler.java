
package com.cts.disbursepension.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

/**
 * This Class will handle all major exception thrown in the application
 * 
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * This method handles data with invalid arguments
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		LOGGER.debug("Handling Argument not valid exception in Disburse Pension Microservice");
		// Get all validation errors
		List<String> errors = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		ErrorResponse errorResponse = new ErrorResponse("Invalid Credentials", errors);
		return new ResponseEntity<>(errorResponse, headers, status);
	}

	/**
	 * This will handle all Feign related Exception
	 * 
	 * @author Shubham Nawani, Anas Zubair
	 * @param exception
	 * @param response
	 * @return ErrorResponse
	 */
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponseNoFieldErrors> handleFeignStatusException(FeignException exception,
			HttpServletResponse response) {
		LOGGER.debug("Handling Feign Client");
		LOGGER.debug("Message: {}", exception.getMessage());
		ErrorResponseNoFieldErrors errorResponse;
		LOGGER.debug("UTF-8 Message: {}", exception.contentUTF8());
		if (exception.contentUTF8().isBlank()) {
			//when feignClient request is timeout or service is unavailable
			errorResponse = new ErrorResponseNoFieldErrors("Service is offline");
		} else {
			try {
				//when error response has valid format
				LOGGER.debug("Trying...");
				errorResponse = objectMapper.readValue(exception.contentUTF8(), ErrorResponseNoFieldErrors.class);
				LOGGER.debug("Successful.. Message is: {}", errorResponse.getMessage());
			} catch (JsonProcessingException e) {
				//Unknown error response
				//Converting raw response to valid format
				errorResponse = new ErrorResponseNoFieldErrors(exception.contentUTF8());
				LOGGER.error("Processing Error {}", e.toString());
			}
		}
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method will handle InvalidTokenException
	 * 
	 * @param exception
	 * @param response
	 * @return ErrorResponse
	 */
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException exception,
			HttpServletResponse response) {
		LOGGER.debug("Handling Invalid Token exception");
		LOGGER.debug("Message: {}", exception.getMessage());
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.FORBIDDEN);
	}

}
