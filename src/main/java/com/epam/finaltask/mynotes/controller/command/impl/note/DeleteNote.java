package com.epam.finaltask.mynotes.controller.command.impl.note;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;
import com.epam.finaltask.mynotes.service.NotesService;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import com.epam.finaltask.mynotes.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteNote implements Command {
	private static final Logger logger = LogManager.getLogger(DeleteNote.class);

	private static final String MAIN_PAGE = "controller?command=go_to_main_page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		HttpSession session = request.getSession(false);
		String url = CreatorFullURL.create(request);

		request.getSession(true).setAttribute("prev_request", url);
		try {
			notesService.deleteNote(Integer.parseInt(request.getParameter("idnote")),
					(int) session.getAttribute("iduser"));
		} catch (ServiceException Ex) {
			logger.info("i can't remove this note!");
			session.setAttribute("error", "Note wasn't removed due to error");
		} catch (NullPointerException | NumberFormatException ex) {
			session.setAttribute("error", "You should choose a note first!");
		}
		request.getSession(true).setAttribute("prev_request", url);
		

		response.sendRedirect(MAIN_PAGE);

	}

}
