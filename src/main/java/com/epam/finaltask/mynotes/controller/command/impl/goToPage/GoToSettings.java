package com.epam.finaltask.mynotes.controller.command.impl.goToPage;

import com.epam.finaltask.mynotes.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSettings implements Command {
	private static final Logger logger = LogManager.getLogger(GoToSettings.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}