package com.bntu.mynotes.controller.command.impl.goToPage;

import com.bntu.mynotes.controller.command.Command;
import com.bntu.mynotes.controller.command.util.CreatorFullURL;
import com.bntu.mynotes.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class GoToIndexPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/default.jsp");
		dispatcher.forward(request, response);
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("error"); 
	}
	
}
