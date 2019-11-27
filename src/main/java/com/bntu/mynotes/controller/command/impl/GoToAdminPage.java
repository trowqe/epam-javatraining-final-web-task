package com.bntu.mynotes.controller.command.impl;

import com.bntu.mynotes.controller.command.Command;
import com.bntu.mynotes.controller.command.PageList;
import com.bntu.mynotes.controller.command.util.CreatorFullURL;
import com.bntu.mynotes.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToAdminPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();

        HttpSession session = request.getSession(false);
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(PageList.ADMIN_PAGE_URL);
        dispatcher.forward(request, response);

        session.removeAttribute("error");
        session.removeAttribute("success");

        session.removeAttribute("notes");;
    }
}
