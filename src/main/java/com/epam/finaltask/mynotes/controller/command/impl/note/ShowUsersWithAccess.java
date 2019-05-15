package com.epam.finaltask.mynotes.controller.command.impl.note;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.service.NotesService;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import com.epam.finaltask.mynotes.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;

public class ShowUsersWithAccess implements Command {
	private static final Logger logger = LogManager.getLogger(ShowUsersWithAccess.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

//		String url = CreatorFullURL.create(request);
//		request.getSession(true).setAttribute("prev_request", url);
		String page = MAIN_PAGE;
		HttpSession session = request.getSession(false);
		try {
			if (request.getParameter("idnote") == null) {
				session.setAttribute("error", "you should choose a note first!");
			} else {
				page = USERS_WITH_ACCESS;
				System.out.println("/"+request.getParameter("idnote") + "/");
				session.setAttribute("idnote", Integer.parseInt(request.getParameter("idnote")));
				session.setAttribute("users", notesService.showUsersWithAccess(
						Integer.parseInt(request.getParameter("idnote")), (int) session.getAttribute("iduser")));
			}
		} catch (ServiceException ex) {
			logger.info(ex);
		} catch (NullPointerException | NumberFormatException ex) {
			request.setAttribute("error", "You should choose a note!");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
