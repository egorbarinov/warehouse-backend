package ru.egorbarinov.warehouse.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		log.warn("ErrorDetails: timestamp: {}, message: {}, details: {}",timestamp, message, details);
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
