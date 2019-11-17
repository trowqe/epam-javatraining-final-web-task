package com.epam.finaltask.mynotes.controller.command.impl;

import com.epam.finaltask.mynotes.controller.command.Command;
import com.epam.finaltask.mynotes.controller.command.util.CreatorFullURL;
import com.epam.finaltask.mynotes.entity.Note;
import com.epam.finaltask.mynotes.service.ServiceProvider;
import com.epam.finaltask.mynotes.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static com.epam.finaltask.mynotes.controller.command.PageList.ADMIN_PAGE_URL;

public class GoToAdminPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();

        HttpSession session = request.getSession(false);
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(ADMIN_PAGE_URL);
        dispatcher.forward(request, response);

        session.removeAttribute("error");
        session.removeAttribute("success");

        session.removeAttribute("notes");;
    }
}
