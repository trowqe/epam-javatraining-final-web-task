package com.bntu.mynotes.service;

import com.bntu.mynotes.entity.User;
import com.bntu.mynotes.service.exception.ServiceException;

public interface ClientService {

	User authorization(String login, String password) throws ServiceException;
	
	boolean registration(String login, String password, String email) throws ServiceException;
}
