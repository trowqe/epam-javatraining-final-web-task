package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.MakePages;
import com.epam.finaltask.mynotes.service.CommentService;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import com.epam.finaltask.mynotes.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;

public class GoToComments implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		CommentService commentsService = provider.getCommentService();

		String page = MAIN_PAGE;
		HttpSession session = request.getSession(false);
		try {
			if (request.getParameter("idnote") != null) {
				int quanity = commentsService.getQuanityOfComments((int) session.getAttribute("iduser"),
						Integer.parseInt(request.getParameter("idnote")));

				MakePages.makePages(request, quanity);

				session.setAttribute("idnote", Integer.parseInt(request.getParameter("idnote")));
				session.setAttribute("comments", commentsService.getComments((int) session.getAttribute("iduser"),
						Integer.parseInt(request.getParameter("idnote")), 0));

				page = COMMENTS_PAGE;
			} else {
				session.setAttribute("error", "you should select a note first!");
			}

		} catch (ServiceException e) {
			session.setAttribute("error", "can't get your comments!");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
