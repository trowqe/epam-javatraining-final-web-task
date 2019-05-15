package com.epam.finaltask.mynotes.controller.command.impl.comment;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.service.CommentService;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import com.epam.finaltask.mynotes.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;
import static com.epam.finaltask.mynotes.controller.command.PageList.MAIN_PAGE;

public class AddComment implements Command {

	private static final Logger logger = LogManager.getLogger(AddComment.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		CommentService commentService = provider.getCommentService();
		
		HttpSession session = request.getSession(false);
		
		boolean result = false;
		
		String page = MAIN_PAGE;
		try {
			result = commentService.addComment((int)session.getAttribute("iduser"), (int)session.getAttribute("idnote"), request.getParameter("text"));
			
			if (!result) {
				session.setAttribute("error", "you have no access to comments of this note!");
			}
		} catch(ServiceException e) {
			logger.info(e);
			session.setAttribute("error", "can't add this comment");
		}
		
		response.sendRedirect(MAIN_PAGE);
		
	}

}
