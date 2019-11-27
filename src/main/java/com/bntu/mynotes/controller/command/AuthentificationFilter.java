package com.bntu.mynotes.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AuthentificationFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(AuthentificationFilter.class);

	private static final List<String> unauthorizedCommands = Collections
			.unmodifiableList(Arrays.asList("go_to_index", "authorization", "registration", "go_to_registration_page"));

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

//		String uri = req.getRequestURI();

		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("iduser") == null) {
			if (request.getParameter("command") != null
					&& !unauthorizedCommands.contains(request.getParameter("command"))) {
				session = req.getSession(true);
				System.out.println("fasdasd");
				session.setAttribute("error", "you should log in first!");
				res.sendRedirect(PageList.INDEX_PAGE);
			}
		}
		filterChain.doFilter(req, res);
	}

	@Override
	public void destroy() {

	}

}
