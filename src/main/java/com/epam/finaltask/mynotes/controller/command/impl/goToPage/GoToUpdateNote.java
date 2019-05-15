package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.impl.note.EditNote;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;
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
import static com.epam.finaltask.mynotes.controller.command.PageList.UPDATE_NOTE_PATH;

public class GoToUpdateNote implements Command {
	private static final Logger logger = LogManager.getLogger(EditNote.class);

	
	private static final String MAIN_PAGE = "controller?command=go_to_main_page";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		HttpSession session = request.getSession(false);
		String url = CreatorFullURL.create(request);

		request.getSession(true).setAttribute("prev_request", url);

		String page = UPDATE_NOTE_PATH;
		try {
			session.setAttribute("note", notesService.getNote(Integer.parseInt(request.getParameter("idnote"))));
		} catch (ServiceException ex) {
			logger.warn("can't process the note!" + ex);
		} catch (NullPointerException | NumberFormatException ex) {
			logger.info(ex);
			session.setAttribute("error", "You should choose a note to edit!");
			session.setAttribute("prev_request", url);
			
			page = MAIN_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
