package com.epam.finaltask.mynotes.controller.command.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MakePages {
	private static final Logger logger = LogManager.getLogger(MakePages.class);

	private static final int RESULTS_ON_PAGE = 10; // also checkout the constant in dao

	public static void makePages(HttpServletRequest requeset, int quanity) {

		HttpSession session = requeset.getSession(false);

		if (session != null) {
			try {
				
				
				session.removeAttribute("info");
				session.removeAttribute("lastPage");
				session.removeAttribute("buttonPrev");
				session.removeAttribute("pageNumber");

				session.removeAttribute("buttonNow");
				session.removeAttribute("buttonNext");
				session.setAttribute("quanity", quanity);
				session.removeAttribute("firstButton");


				if (quanity != 0) {
					int lastPage = quanity % RESULTS_ON_PAGE == 0 ? quanity / RESULTS_ON_PAGE
							: quanity / RESULTS_ON_PAGE + 1;
					int currentPage = 1;
					
					if (requeset.getParameter("pageNumber") != null) {
						currentPage = Integer.parseInt(requeset.getParameter("pageNumber"));
					}
					
					System.out.println("quanity::" + quanity);
					
//					session.setAttribute("pageNumber", currentPage);

					if (currentPage - 1 > 1) {
						session.setAttribute("buttonPrev", currentPage - 1);
					}

					if (currentPage + 1 < lastPage) {
						session.setAttribute("buttonNext", currentPage + 1);
					}

					if (currentPage != 1 && currentPage != lastPage) {
						session.setAttribute("buttonNow", currentPage);
					}
					
					if (lastPage != 1) {
						session.setAttribute("lastButton", lastPage);
						System.out.println("last page is:" + lastPage);
					} else {
						
						session.setAttribute("lastButton", null);
						
					}
					
					session.setAttribute("firstButton", 1);
					session.setAttribute("currentPage", currentPage); // cause of "first button conflict";
					System.out.println((int) session.getAttribute("currentPage") + " cur page");

				} else {
					session.setAttribute("info", "You have no notes!");
				}

			} catch (NullPointerException | NumberFormatException ex) {
				logger.warn("?");
				session.setAttribute("error", "Can't get your notes. Please, try again later..");
			}
		}
	}
}
