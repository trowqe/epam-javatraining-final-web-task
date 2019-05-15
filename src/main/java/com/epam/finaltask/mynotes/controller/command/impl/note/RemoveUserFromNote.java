package com.epam.finaltask.mynotes.controller.command.impl.note;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;
import com.epam.finaltask.mynotes.service.NotesService;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import com.epam.finaltask.mynotes.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveUserFromNote implements Command {

	private static final String MAIN_PAGE = "controller?command=go_to_main_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		HttpSession session = request.getSession(false);
		String url = CreatorFullURL.create(request);

		request.getSession(true).setAttribute("prev_request", url);

		try {
			System.out.println((int) session.getAttribute("idnote"));
			System.out.println(session.getAttribute("iduser"));
			System.out.println(Integer.parseInt(request.getParameter("iduser")));
			if (request.getParameter("iduser") == null) {
				session.setAttribute("error", "select the user to remove from note first!");
			} else {
				System.out.println(request.getParameter("iduser"));
				notesService.removeUserFromNote(Integer.parseInt(request.getParameter("idnote")),
						(int) session.getAttribute("iduser"), Integer.parseInt(request.getParameter("iduser")));
			}
		} catch (ServiceException ex) {
			request.setAttribute("error", "can't remove this note");
		} catch (NullPointerException | NumberFormatException ex) {
			request.setAttribute("error", "choose the note first!");
		}

		response.sendRedirect(MAIN_PAGE);
	}

}
