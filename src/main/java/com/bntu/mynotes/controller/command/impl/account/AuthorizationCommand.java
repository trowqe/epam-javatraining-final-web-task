package com.bntu.mynotes.controller.command.impl.account;

import com.bntu.mynotes.controller.command.Command;
import com.bntu.mynotes.controller.command.util.CreatorFullURL;
import com.bntu.mynotes.entity.User;
import com.bntu.mynotes.service.ClientService;
import com.bntu.mynotes.service.ServiceProvider;
import com.bntu.mynotes.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.bntu.mynotes.controller.command.PageList.*;

public class AuthorizationCommand implements Command {

	private static final Logger logger = LogManager.getLogger(ServiceException.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();

		String login;
		String password;

		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD); // valid?

		User user = null;

		String page;

		HttpSession session = request.getSession(true);

		if (session.getAttribute("iduser") == null) {
			try {
				user = service.authorization(login, password);

				if (user == null) {
					session.setAttribute("error", "incorrect login!");

					page = INDEX_PAGE;

				} else {
					System.out.println("got");
					session.setAttribute("user", user);
					session.setAttribute("iduser", user.getId());
					if (user.getAccessLevel() == -1)
					{page = BANNED_PAGE;}
					if (user.getAccessLevel()==1){
						page=ADMIN_PAGE;
					}
					else {
						// send redirect to
						page = MAIN_PAGE;
					}
				}

			} catch (ServiceException e) {
				logger.warn("incorrect login" + e);
				session.setAttribute("error", "incorrect login");
				page = INDEX_PAGE;
			}
		} else {
			session.setAttribute("error", "You are already logged in!");
			page = MAIN_PAGE;
		}

		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);

		response.sendRedirect(page);

	}

}
