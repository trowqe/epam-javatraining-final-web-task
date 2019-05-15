package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.MakePages;
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

public class GoToSharedNotes implements Command {
	private static final Logger logger = LogManager.getLogger(GoToSharedNotes.class);

	private static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		String page = SHARED_NOTES_PAGE_PATH;

		HttpSession session = request.getSession(false);
		try {
			int quanity = notesService.getNumberOfSharedNotes((int) session.getAttribute("iduser"));
			System.out.println(quanity + "-q");
			session.setAttribute("quanity", quanity);

			MakePages.makePages(request, quanity);

			if (session.getAttribute("currentPage") != null) {
				session.setAttribute("notes", notesService.getSharedNotes((int) session.getAttribute("iduser"),
						((int) session.getAttribute("currentPage") - 1) * RESULTS_ON_PAGE));
			} else {
				session.setAttribute("notes", notesService.getSharedNotes((int) session.getAttribute("iduser"), 0	));
			}

		} catch (ServiceException ex) {
			logger.warn("Can't get notes of" + session.getAttribute("iduser") + ex);
			session.setAttribute("error", "Can't get your notes. Please, try again later.");
			page = MAIN_PAGE;

		} catch (NullPointerException | NumberFormatException ex) {
			logger.warn(ex);
			session.setAttribute("error", "you have no notes!");
			page = MAIN_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		session.removeAttribute("info");

		session.removeAttribute("success");

		session.removeAttribute("error");
	}

}