package com.cts.pensionerDetails.Exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class for customizing error response in exception handler
 * 
 *
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {

	

	private String message;
	public ErrorResponse(String message, LocalDateTime timestamp, List<String> fieldErrors) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.fieldErrors = fieldErrors;
	}

	private LocalDateTime timestamp;

	/**
	 * Used only for input validation errors
	 */
	@JsonInclude(Include.NON_NULL)
	private List<String> fieldErrors;
}
