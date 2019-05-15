package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;

public class GoToAddingUserToNote implements Command {
	private static final Logger logger = LogManager.getLogger(GoToAddingUserToNote.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String page = MAIN_PAGE;
		if (session != null) {
			if (request.getParameter("iduser") != null && request.getParameter("idnote") != null) {
				session.setAttribute("idnote", Integer.parseInt(request.getParameter("idnote")));
				page = ADDING_ACCESS_USER_TO_NOTE;
			} else {
				session.setAttribute("error", "you should choose a user first!");
			}
		} else {
			session = request.getSession(true);
			session.setAttribute("error", "Your session has expired! Log in ");

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}

}
