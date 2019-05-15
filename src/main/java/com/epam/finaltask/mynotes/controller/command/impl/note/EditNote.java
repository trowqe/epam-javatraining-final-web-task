package com.epam.finaltask.mynotes.controller.command.impl.note;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;
import com.epam.finaltask.mynotes.entity.Note;
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

public class EditNote implements Command {
	private static final Logger logger = LogManager.getLogger(EditNote.class);

	private static final String MAIN_PAGE = "controller?command=go_to_main_page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		HttpSession session = request.getSession(false);
		String url = CreatorFullURL.create(request);

//		request.getSession(true).setAttribute("prev_request", url);

		Note note = new Note();

		System.out.println("noww");
		try {
			note.setIdnote(Integer.parseInt(request.getParameter("idnote")));
			note.setTitle(request.getParameter("title"));
			note.setText(request.getParameter("text"));
			
			
			if (request.getPart("preview").getSize() < 10) {
				System.out.println(request.getParameter("oldPreview"));
				note.setPreview(null);
			} else {
				System.out.println("sfsdf");
				note.setPreview(CreateNewNote.readFully(request.getPart("preview").getInputStream())); // temp solution
			}
			note.setIduser((int) session.getAttribute("iduser"));
			logger.trace(note.toString());
			System.out.println(note.getPreview());

			notesService.updateNote(note);

			request.setAttribute("success", "Note has been edited!");
		} catch (ServiceException ex) {
			logger.info("this note can't be edited");
			request.setAttribute("error", "this note can't be edited!");
		} catch (NullPointerException | NumberFormatException ex) {
			System.out.println(request.getParameter("idnote"));
			request.setAttribute("error", "You should choose a note to edit!");
		} catch (Exception ex) {
			System.out.println(ex);
		}

		response.sendRedirect(MAIN_PAGE);

	}

}
