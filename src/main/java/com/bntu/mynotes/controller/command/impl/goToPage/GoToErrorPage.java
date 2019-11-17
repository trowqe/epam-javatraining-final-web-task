package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.finaltask.mynotes.controller.command.PageList.*;
import static com.epam.finaltask.mynotes.controller.command.PageList.ERROR_PAGE_PATH;

public class GoToErrorPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_PATH);
		dispatcher.forward(request, response);
	}

}
