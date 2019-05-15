package com.epam.finaltask.mynotes.controller.command.impl.account;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;
import com.epam.finaltask.mynotes.service.ClientService;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import com.epam.finaltask.mynotes.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;

public class Registration implements Command {

	private static final Logger logger = LogManager.getLogger(ServiceException.class); 
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email;
		String login;
		String password;
		
		email = request.getParameter(PARAMETER_EMAIL);
		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();
		
		
		String page;
		
		
		//TODO: localize it 
		try {
			boolean result = service.registration(login, password, email);
			
			if (result == false) {
				request.setAttribute("error", "User with that login are already registered!");	//use this to say about bad entriees
				page = INDEX_PAGE;
			} else {
				//login
				request.setAttribute("error", "User has been registered! Now you can log in.");	//add info as an alternative to error section
				page = INDEX_PAGE;
			}
		} catch (ServiceException e) {
			request.setAttribute("error", "Your entered data is invalid.");	//or problem w/ mysql. who knows. TODO: google it.better exception handling
			logger.info(e);
			page = INDEX_PAGE;
		}
		
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}

}
