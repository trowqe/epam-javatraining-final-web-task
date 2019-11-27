package com.bntu.mynotes.service.impl;
//

import com.bntu.mynotes.dao.UserDAO;
import com.bntu.mynotes.dao.exception.DaoException;
import com.bntu.mynotes.dao.impl.DAOProvider;
import com.bntu.mynotes.entity.User;
import com.bntu.mynotes.service.ClientService;
import com.bntu.mynotes.service.exception.ServiceException;
import com.bntu.mynotes.service.validation.CredentionalValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class ClientServiceImpl implements ClientService{
	private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class); 
	
	@Override
	public User authorization(String login, String password) throws ServiceException{
		
		if(!CredentionalValidator.isCorrect(login, password)) {
			throw new ServiceException("validator of entries returned false");
		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		
		User user = null;
				
		try {
			user = userDAO.authentification(login, password);
		} catch(DaoException e) {
			logger.info(e);
			throw new ServiceException("authentification error"); 
		}
	
		return user;
	}
	
	@Override
	
	public boolean registration(String login, String password, String email) throws ServiceException {

		if(!CredentionalValidator.isCorrect(login, password)) {
			throw new ServiceException("incorrect login!");
		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();
		String bcryptHashString = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		try{
			return userDAO.registration(login, bcryptHashString, email);
		} catch(DaoException ex) {
			logger.warn(ex);
			throw new ServiceException("registration error!" + ex);
		}
	}
}
