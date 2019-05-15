package com.epam.finaltask.mynotes.dao;


import com.epam.finaltask.mynotes.dao.exception.DaoException;
import com.epam.finaltask.mynotes.entity.Note;
import com.epam.finaltask.mynotes.entity.User;

import java.util.List;

public interface NotesDAO {

	List<Note> getCreatedNotes(int idUser) throws DaoException;

	List<Note> getCreatedNotes(int idUser, int offset) throws DaoException;

	List<Note> getAvailableNotes(int idUser) throws DaoException;

	List<Note> getAvailableNotes(int idUser, int offset) throws DaoException;

	List<Note> getLastNotes(int idUser) throws DaoException;

	List<Note> getLastNotes(int idUser, int offset) throws DaoException;
	
	List<Note> getSharedNotes(int idUser) throws DaoException;

	List<Note> getSharedNotes(int idUser, int offset) throws DaoException;
	
	List<User> showUsersWithAccess(int idNote, int idUser) throws DaoException;
	
	Note getNote(int idnote) throws DaoException;

	int getNumberOfCreatedNotes(int iduser) throws DaoException;
	
	int getNumberOfAvailableNotes(int iduser) throws DaoException;
	
	int getNumberOfSharedNotes(int iduser) throws DaoException;
	
	boolean createNote(Note note) throws DaoException;

	boolean updateNote(Note note) throws DaoException;

	boolean hideNote(Note note) throws DaoException;

	boolean deleteNote(int idNote, int idUser) throws DaoException;

	boolean addUserToNote(int idNote, int idHostUser, int idTargetUser, int accessLevel) throws DaoException;

	boolean removeUserFromNote(int idNote, int idHostUser, int idTargetUser) throws DaoException;
}
