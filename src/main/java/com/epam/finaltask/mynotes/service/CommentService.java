package com.epam.finaltask.mynotes.service;

import com.epam.finaltask.mynotes.entity.Comment;
import com.epam.finaltask.mynotes.service.exception.ServiceException;

import java.util.List;

public interface CommentService {
	List<Comment> getComments(int iduser, int idnote, int offset) throws ServiceException;

	int getQuanityOfComments(int iduser, int idnote) throws ServiceException;

	boolean addComment(int iduser, int idnote, String text) throws ServiceException;

	boolean removeComment(int iduser, int idcomment) throws ServiceException;

}
