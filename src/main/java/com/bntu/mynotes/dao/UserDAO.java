package com.bntu.mynotes.dao;

import com.bntu.mynotes.dao.exception.DaoException;
import com.bntu.mynotes.entity.User;

public interface UserDAO {

	User authentification(String login, String password) throws DaoException;
	
	boolean registration(String login, String password, String email)  throws DaoException;
}
