package com.bntu.mynotes.service.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -5518922679314644113L;
//	private static final Logger logger = LogManager.getLogger(ServiceException.class); 

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);

	}
}
