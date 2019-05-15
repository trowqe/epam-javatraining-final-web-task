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

import static com.epam.finaltask.mynotes.controller.command.PageList.*;
import static com.epam.finaltask.mynotes.controller.command.PageList.MAIN_PAGE;

public class EditUserAccessToNote implements Command {
	private static final Logger logger = LogManager.getLogger(EditUserAccessToNote.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		NotesService notesService = provider.getNotesService();

		HttpSession session = request.getSession(false);
		String url = CreatorFullURL.create(request);

		request.getSession(true).setAttribute("prev_request", url);
		
		try {
			System.out.println((int)session.getAttribute("idnote"));
			System.out.println((int)session.getAttribute("iduser") + "id cur user");
			System.out.println(request.getParameter("iduser"));
			System.out.println(Integer.parseInt(request.getParameter("access")));
			notesService.editUserAccess(
					(int)session.getAttribute("idnote"), 
					(int)session.getAttribute("iduser"), 
					(int)session.getAttribute("idTargetUser"), 
					Integer.parseInt(request.getParameter("access")));
		} catch(ServiceException ex) {
			session.setAttribute("error", "can't edit user access to note");
		}
		
		response.sendRedirect(MAIN_PAGE);
		
	}

}
