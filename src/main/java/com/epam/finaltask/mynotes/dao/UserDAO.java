package com.epam.finaltask.mynotes.dao;

import com.epam.finaltask.mynotes.dao.exception.DaoException;
import com.epam.finaltask.mynotes.entity.User;

public interface UserDAO {

	User authentification(String login, String password) throws DaoException;
	
	boolean registration(String login, String password, String email)  throws DaoException;
}
