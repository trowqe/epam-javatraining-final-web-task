package com.bntu.mynotes.controller.command.impl.goToPage;

import com.bntu.mynotes.controller.command.Command;
import com.bntu.mynotes.controller.command.util.CreatorFullURL;
import com.bntu.mynotes.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.bntu.mynotes.controller.command.PageList.MAIN_PAGE_URL;

public class GoToMainPage implements Command {
	
	private static final Logger logger = LogManager.getLogger(GoToMainPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();

		HttpSession session = request.getSession(false);
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE_URL);
		dispatcher.forward(request, response);
		
		session.removeAttribute("error");
		session.removeAttribute("success");

		session.removeAttribute("notes");
	}

}
