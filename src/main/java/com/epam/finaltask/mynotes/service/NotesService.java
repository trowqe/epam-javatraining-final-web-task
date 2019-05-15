package com.epam.finaltask.mynotes.service;


import com.epam.finaltask.mynotes.entity.Note;
import com.epam.finaltask.mynotes.entity.User;
import com.epam.finaltask.mynotes.service.exception.ServiceException;

import java.util.List;

public interface NotesService {
	
	List<Note> getCreatedNotes(int idUser) throws ServiceException;
	
	List<Note> getCreatedNotes(int idUser, int offset) throws ServiceException;
	
	List<Note> getAvailableNotes(int idUser) throws ServiceException;
	
	List<Note> getAvailableNotes(int idUser, int offset) throws ServiceException;
	
	List<Note> getLastNotes(int idUser) throws ServiceException;
	
	List<Note> getLastNotes(int idUser, int offset) throws ServiceException;

	List<Note> getSharedNotes(int idUser) throws ServiceException;
	
	List<Note> getSharedNotes(int idUser, int offset) throws ServiceException;

	List<User> showUsersWithAccess(int idNote, int idUser) throws ServiceException;
	
	Note getNote(int idnote) throws ServiceException;

	int getNumberOfCreatedNotes(int iduser) throws ServiceException;
	
	int getNumberOfAvailableNotes(int iduser) throws ServiceException;
	
	int getNumberOfSharedNotes(int iduser) throws ServiceException;
	
	boolean createNote(Note note) throws ServiceException;
	
	boolean updateNote(Note note) throws ServiceException;
	
	boolean hideNote(Note note) throws ServiceException; 
	
	boolean deleteNote(int idNote, int idUser) throws ServiceException;
	
	boolean addUserToNote(int idNote, int idHostUser, int idTargetUser, int accessLevel) throws ServiceException;
	
	boolean editUserAccess(int idNote, int idHostUser, int idTargetUser, int accessLevel) throws ServiceException;
	
	boolean removeUserFromNote(int idNote, int idHostUser, int idTargetUser) throws ServiceException;
}
