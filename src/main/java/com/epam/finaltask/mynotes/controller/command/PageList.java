package com.epam.finaltask.mynotes.controller.command;

public class PageList {
	public static final String ADDING_ACCESS_USER_TO_NOTE = "/WEB-INF/jsp/addingUserAccess.jsp";
	public static final String AVAILABLE_NOTES_PAGE_PATH = "/WEB-INF/jsp/availableNotes.jsp";
	public static final String CREATING_NOTE_PATH = "/WEB-INF/jsp/createNote.jsp";
	public static final String CREATED_NOTES_PAGE_PATH = "/WEB-INF/jsp/createdNotes.jsp";
	public static final String EDITING_ACCESS_USER_TO_NOTE_PATH = "/WEB-INF/jsp/editingUserAccess.jsp";
	
	public static final String USERS_WITH_ACCESS = "WEB-INF/jsp/UsersWithAccess.jsp";
	public static final String UPDATE_NOTE_PATH = "/WEB-INF/jsp/editNote.jsp";
	public static final String SHARED_NOTES_PAGE_PATH = "/WEB-INF/jsp/sharedNotes.jsp";

	public static final String BANNED_PAGE = "/WEB-INF/jsp/banned.jsp";
	public static final String ERROR_PAGE_PATH = "/general-error.html";
	public static final String INDEX_PAGE = "controller?command=go_to_index";
	public static final String MAIN_PAGE = "controller?command=go_to_main_page"; 
	public static final String MAIN_PAGE_URL = "/WEB-INF/jsp/main.jsp";

	public static final String ADMIN_PAGE = "controller?command=go_to_admin_page";
	public static final String ADMIN_PAGE_URL = "/WEB-INF/jsp/admin.jsp";

	public static final String COMMENTS_PAGE = "/WEB-INF/jsp/showComments.jsp";

	public static final String PARAMETER_EMAIL = "email";
	public static final String PARAMETER_LOGIN = "login";
	public static final String PARAMETER_PASSWORD = "password";

	public static final String PARAMETER_USER_ID = "iduser";
	public static final String PARAMETER_ACCESS_LEVEL = "accessLevel";
	public static final String PARAMETER_NOTES_LIST = "notes";
	public static final String PARAMETER_NOTE_ID = "idnote";
	
	
	public static final int RESULTS_ON_PAGE = 10; // also checkout the constant in dao

}
