package com.epam.finaltask.mynotes.controller.command.impl;

import com.epam.finaltask.mynotes.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newLocale;
		HttpSession session;

		newLocale = request.getParameter("locale");

		session = request.getSession(true);
		session.setAttribute("local", newLocale);

		System.out.println((String) request.getSession(false).getAttribute("prev_request") + "= prev");
		String url = (String) request.getSession(false).getAttribute("prev_request");
		if (!response.isCommitted()) {
			response.sendRedirect(url);
		}
	}

}
