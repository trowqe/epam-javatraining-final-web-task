package com.epam.finaltask.mynotes.dao.impl;

import com.epam.finaltask.mynotes.dao.exception.NotDBDriverException;

import java.util.ResourceBundle;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class SqlDao {

//	private static final Logger logger = LogManager.getLogger(ServiceException.class); 
	
	protected static final String driver;
	protected static final String url;
	protected static final String login;
	protected static final String password;
	
	private static final String DB_DRIVER = "db.driver";
	private static final String DB_URL = "db.url";
	private static final String DB_LOGIN = "db.login"; //root?
	private static final String DB_PASSWORD = "db.password"; //is it safe?
	
	private static final String DB_PROPERTIES_FILE_PATH = "resources.db";
	
	static { 
		ResourceBundle jdbcProperties = ResourceBundle.getBundle(DB_PROPERTIES_FILE_PATH);
		
		driver = jdbcProperties.getString(DB_DRIVER);
		url     = jdbcProperties.getString(DB_URL);
		login   = jdbcProperties.getString(DB_LOGIN);
		password= jdbcProperties.getString(DB_PASSWORD);
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new NotDBDriverException("DB-Driver not found.",e);
			
		}
	}
	
	public SqlDao() {
		
	}

}
