package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;
import com.epam.finaltask.mynotes.entity.Note;
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
import java.util.ArrayList;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;
import static com.epam.finaltask.mynotes.controller.command.PageList.MAIN_PAGE_URL;

public class GoToMainPage implements Command {
	
	private static final Logger logger = LogManager.getLogger(GoToMainPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		HttpSession session = request.getSession(false);
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);
		try {
			session.setAttribute("notes", notesService.getLastNotes((int) session.getAttribute("iduser")));
		} catch(NullPointerException e) {
			logger.info(e + "got empty list of last notes!");
			session.setAttribute("error", "can't get your notes right now. please try again later!");
		} catch (ServiceException e) {
			session.setAttribute("notes", new ArrayList<Note>());
			session.setAttribute("error", "can't get your notes! please try again later!");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE_URL);
		dispatcher.forward(request, response);
		
		session.removeAttribute("error");
		session.removeAttribute("success");

		session.removeAttribute("notes");;
	}

}
