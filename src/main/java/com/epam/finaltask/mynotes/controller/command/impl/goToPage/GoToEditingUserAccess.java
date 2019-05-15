package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;

public class GoToEditingUserAccess implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String page = EDITING_ACCESS_USER_TO_NOTE_PATH;
		if (session != null) {
			if (request.getParameter("idnote") != null && request.getParameter("iduser") != null) {
				session.setAttribute("idnote", Integer.parseInt(request.getParameter("idnote")));
				session.setAttribute("idTargetUser", Integer.parseInt(request.getParameter("iduser")));
			} else {
				page = MAIN_PAGE;
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
