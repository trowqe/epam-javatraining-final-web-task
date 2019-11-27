package com.bntu.mynotes.dao.exception;

import com.bntu.mynotes.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoException extends Exception{
	
	private static final long serialVersionUID = -622882989387379671L;
	
	private static final Logger logger = LogManager.getLogger(ServiceException.class); 
	

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
		logger.error(message);
	}

	public DaoException(Exception e) {
		super(e);
		logger.error(e.getMessage().toString());
	}

	public DaoException(String message, Exception e) {
		super(message, e);
		
		logger.error(message + " " + e.getMessage().toString());
	}

}
