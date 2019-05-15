package com.epam.finaltask.mynotes.dao.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class NotDBDriverException extends RuntimeException {
	private static final long serialVersionUID = -7223276245503461734L;

	private static final Logger logger = LogManager.getLogger(NotDBDriverException.class);

	public NotDBDriverException() {
		super();
	}

	public NotDBDriverException(String message) {
		super(message);
		logger.error(message);
	}

	public NotDBDriverException(Exception e) {
		super(e);
		logger.error(e.getStackTrace().toString());
	}

	public NotDBDriverException(String message, Exception e) {
		super(message, e);
		logger.error(message + " " + e.getMessage().toString());
	}
}
