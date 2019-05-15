package com.epam.finaltask.mynotes.dao.impl;

import com.epam.finaltask.mynotes.dao.CommentsDAO;
import com.epam.finaltask.mynotes.dao.NotesDAO;
import com.epam.finaltask.mynotes.dao.UserDAO;
import com.epam.finaltask.mynotes.dao.impl.comments.SQLCommentsDAO;
import com.epam.finaltask.mynotes.dao.impl.note.SQLNotesDAO;
import com.epam.finaltask.mynotes.dao.impl.user.SQLUserDAO;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final NotesDAO notesDAO = new SQLNotesDAO();
	private final CommentsDAO commentsDAO = new SQLCommentsDAO();
	
	private DAOProvider() {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public NotesDAO getNotesDAO() {
		return notesDAO;
	}
	
	public CommentsDAO getCommentsDAO() {
		return commentsDAO;
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}

}
