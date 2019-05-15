package com.epam.finaltask.mynotes.controller.command.impl.comment;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.service.CommentService;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import com.epam.finaltask.mynotes.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;
import static com.epam.finaltask.mynotes.controller.command.PageList.MAIN_PAGE;

public class DeleteComment implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CommentService commentsService = provider.getCommentService();

		HttpSession session = request.getSession(false);

		String page = MAIN_PAGE;
		boolean result = false;
		try {
			if (request.getParameter("idComment") != null) {
				result = commentsService.removeComment((int) session.getAttribute("iduser"),
						Integer.parseInt(request.getParameter("idComment")));
				if (result) {
					session.setAttribute("success", "you deleted a comment!");
				} else {
					session.setAttribute("error", "you have no access to this comment");
				}
			} else {
				session.setAttribute("error", "you should choose a comment first!");
			}
		} catch (ServiceException ex) {
			session.setAttribute("error", "try again later!");
		}

		response.sendRedirect(MAIN_PAGE);
	}

}
