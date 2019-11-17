package com.epam.finaltask.mynotes.service;

import com.epam.finaltask.mynotes.entity.User;
import com.epam.finaltask.mynotes.service.exception.ServiceException;

public interface ClientService {

	User authorization(String login, String password) throws ServiceException;
	
	boolean registration(String login, String password, String email) throws ServiceException;
}
