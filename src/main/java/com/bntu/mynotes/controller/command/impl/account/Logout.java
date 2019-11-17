package com.epam.finaltask.mynotes.controller.command.impl.account;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.service.ClientService;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {
	private static final Logger logger = LogManager.getLogger(Logout.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();

		HttpSession session = request.getSession(false);
		if (session.getAttribute("iduser") != null) {
			session.removeAttribute("iduser");
			session.invalidate();
			session = null;
		} else { 
			;//user already logged out
		}
		
		response.sendRedirect("index.jsp");	//post-redirect-get pattern
	}

}
