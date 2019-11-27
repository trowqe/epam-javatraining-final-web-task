package com.bntu.mynotes.dao.impl;

import com.bntu.mynotes.dao.UserDAO;
import com.bntu.mynotes.dao.impl.user.SQLUserDAO;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private final UserDAO userDAO = new SQLUserDAO();

	
	private DAOProvider() {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}

}
