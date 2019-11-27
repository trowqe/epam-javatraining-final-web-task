package com.bntu.mynotes.controller;

import com.bntu.mynotes.controller.command.Command;
import com.bntu.mynotes.controller.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 13232L;
	
	private static final Logger logger = LogManager.getLogger(Controller.class); 

	private static final String PARAM_COMMAND = "command";

	private final CommandProvider provider = new CommandProvider();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}
	
	private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(PARAM_COMMAND);
		Command command = provider.getCommand(commandName);
		
		logger.trace(commandName);

		if (command == null) {
			command = provider.getCommand("go_to_error_page");
		}
		command.execute(request, response);
	}

}
