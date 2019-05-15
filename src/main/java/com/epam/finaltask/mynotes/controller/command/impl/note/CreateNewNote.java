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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@MultipartConfig
public class CreateNewNote implements Command {
	private static final Logger logger = LogManager.getLogger(CreateNewNote.class);

	private static final String MAIN_PAGE = "controller?command=go_to_main_page";
	

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		HttpSession session = request.getSession(false);
		String url = CreatorFullURL.create(request);

		request.getSession(true).setAttribute("prev_request", url);

		Note note = new Note();

		try {
			note.setTitle(request.getParameter("title"));
			note.setText(request.getParameter("text"));
			note.setPreview(readFully(request.getPart("preview").getInputStream()));
			note.setIduser((int) session.getAttribute("iduser"));

			logger.trace(note.toString());

			notesService.createNote(note);
		} catch (ServiceException ex) {
			logger.info("this note can't be created right now");
			session.setAttribute("error", "Incorrect input! Check out all fields and try again!");
			// dunno what happened;
		} catch (NullPointerException | NumberFormatException ex) {
			logger.info("Bad input");
			session.setAttribute("error", "Note wasn't created. Recheck your input and try again.");
		}
		request.getSession(true).setAttribute("prev_request", url);

//		RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE);
//		dispatcher.forward(request, response);

		response.sendRedirect(MAIN_PAGE);
	}
	
	public static byte[] readFully(InputStream input) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    int bytesRead;
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    while ((bytesRead = input.read(buffer)) != -1)
	    {
	        output.write(buffer, 0, bytesRead);
	    }
	    return output.toByteArray();
	}

}
