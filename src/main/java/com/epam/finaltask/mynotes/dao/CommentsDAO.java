package com.epam.finaltask.mynotes.dao;

import com.epam.finaltask.mynotes.dao.exception.DaoException;
import com.epam.finaltask.mynotes.entity.Comment;

import java.util.List;

public interface CommentsDAO {
	
	List<Comment> getComments(int iduser, int idnote, int offset) throws DaoException;
	
	int getQuanityOfComments(int iduser, int idnote) throws DaoException;
	
	boolean addComment(int iduser, int idnote, String text) throws DaoException;
	 
	boolean removeComment(int iduser, int idcomment) throws DaoException;

}
