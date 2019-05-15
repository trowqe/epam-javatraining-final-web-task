package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;
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

public class GoToCreatedNotes implements Command {
	private static final Logger logger = LogManager.getLogger(GoToCreatedNotes.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);

		HttpSession session = request.getSession(false);

		try {
			int quanity = notesService.getNumberOfAvailableNotes((int) session.getAttribute("iduser"));
			session.setAttribute("quanity", quanity);

			MakePages.makePages(request, quanity);

			session.setAttribute("notes", notesService.getCreatedNotes((int) session.getAttribute("iduser"),
					((int) session.getAttribute("currentPage") - 1) * RESULTS_ON_PAGE));

		} catch (ServiceException ex) {
			logger.warn("Can't get notes of" + session.getAttribute("iduser") + ex);
			session.setAttribute("error", "Can't get your notes. Please, try again later.");
		} catch (NullPointerException | NumberFormatException ex) {
			logger.warn("?");
			request.setAttribute("error", "Can't get your notes. Please, try again later.");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(CREATED_NOTES_PAGE_PATH);
		dispatcher.forward(request, response);
	}

}
