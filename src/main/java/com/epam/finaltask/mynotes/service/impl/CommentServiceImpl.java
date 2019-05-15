package com.epam.finaltask.mynotes.service.impl;

import com.epam.finaltask.mynotes.dao.CommentsDAO;
import com.epam.finaltask.mynotes.dao.exception.DaoException;
import com.epam.finaltask.mynotes.dao.impl.DAOProvider;
import com.epam.finaltask.mynotes.entity.Comment;
import com.epam.finaltask.mynotes.service.CommentService;
import com.epam.finaltask.mynotes.service.exception.ServiceException;

import java.util.List;

public class CommentServiceImpl implements CommentService{

	@Override
	public List<Comment> getComments(int iduser, int idnote, int offset) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		CommentsDAO commentsDAO = daoProvider.getCommentsDAO();
		
		
		List<Comment> commentList = null;
		
		try {
			commentList = commentsDAO.getComments(iduser, idnote, offset);
		} catch(DaoException ex) {
			throw new ServiceException("can't get comments!" + ex);
		}
		
		return commentList;
		
	}

	@Override
	public int getQuanityOfComments(int iduser, int idnote) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		CommentsDAO commentsDAO = daoProvider.getCommentsDAO();
		
		int quanity = -1;
		
		try {
			quanity = commentsDAO.getQuanityOfComments(iduser, idnote);
		} catch(DaoException ex) {
			throw new ServiceException("can't get the quanity of comments." + ex);
		}
		
		return quanity;
	}

	@Override
	public boolean addComment(int iduser, int idnote, String text) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		CommentsDAO commentsDAO = daoProvider.getCommentsDAO();
		
		boolean result = false;
		try {
			result = commentsDAO.addComment(iduser, idnote, text);
		} catch (DaoException ex) {
			throw new ServiceException("can't create new comment.");
		}
		
		return result;
	}

	@Override
	public boolean removeComment(int iduser, int idcomment) throws ServiceException {
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		CommentsDAO commentsDAO = daoProvider.getCommentsDAO();
		
		boolean result = false;
		
		try {
			result = commentsDAO.removeComment(iduser, idcomment);
		} catch(DaoException ex) {
			throw new ServiceException("can't remove comment!" + ex);
		}
		
		return result;
	}


}
