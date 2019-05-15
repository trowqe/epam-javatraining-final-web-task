package com.epam.finaltask.mynotes.service.impl;

import com.epam.finaltask.mynotes.dao.NotesDAO;
import com.epam.finaltask.mynotes.dao.exception.DaoException;
import com.epam.finaltask.mynotes.dao.impl.DAOProvider;
import com.epam.finaltask.mynotes.entity.Note;
import com.epam.finaltask.mynotes.entity.User;
import com.epam.finaltask.mynotes.service.NotesService;
import com.epam.finaltask.mynotes.service.exception.ServiceException;
import com.epam.finaltask.mynotes.service.validation.NotesValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class NotesServiceImpl implements NotesService {
	private static final Logger logger = LogManager.getLogger(NotesServiceImpl.class);

	@Override
	public List<Note> getCreatedNotes(int idUser) throws ServiceException {
		return getCreatedNotes(idUser, 0);

	}

	@Override
	public List<Note> getAvailableNotes(int idUser) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		List<Note> notesList = null;

		try {
			notesList = notesDAO.getAvailableNotes(idUser); // should i really use ArrayList?
		} catch (DaoException ex) {
			throw new ServiceException("can't get created notes", ex);
		}

		return notesList;
	}

	@Override
	public boolean createNote(Note note) throws ServiceException {

		if (!NotesValidator.isNoteCorrect(note)) {
			throw new ServiceException("Incorrectly filled note!");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.createNote(note);
		} catch (DaoException ex) {
			throw new ServiceException("failed to create new note." + ex);
		}
	}

	@Override
	public boolean updateNote(Note note) throws ServiceException {

		if (!NotesValidator.isNoteWithoutPreviewCorrect(note)) {
			throw new ServiceException("incorrectly filled note!");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.updateNote(note);
		} catch (DaoException ex) {
			logger.info("can't update this note");
			throw new ServiceException(ex);
		}
	}

	// how should it work?
	@Override
	public boolean hideNote(Note note) throws ServiceException {
		// just use updateNote w/ canAccess changing
		return false;
	}

	@Override
	public boolean deleteNote(int idNote, int iduser) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.deleteNote(idNote, iduser);
		} catch (DaoException ex) {
			throw new ServiceException("failed to remove the note");
		}
	}

	@Override
	public boolean addUserToNote(int idNote, int idHostUser, int idTargetUser, int accessLevel)
			throws ServiceException {
		// check id's
		if (!NotesValidator.isTargetUserCorrect(idTargetUser)) {
			throw new ServiceException("incorrectly filled Targer User Id!");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.addUserToNote(idNote, idHostUser, idTargetUser, accessLevel);
		} catch (DaoException ex) {
			throw new ServiceException("failed to update the note");
		}
	}

	@Override
	public boolean removeUserFromNote(int idNote, int idHostUser, int idTargetUser) throws ServiceException {
		if (!NotesValidator.isTargetUserCorrect(idTargetUser)) {
			System.out.println(idTargetUser);
			throw new ServiceException("incorrectly filled Targer User Id!");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.removeUserFromNote(idNote, idHostUser, idTargetUser);
		} catch (DaoException ex) {
			throw new ServiceException("failed to update the note");
		}
	}

	@Override
	public List<Note> getCreatedNotes(int idUser, int offset) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		List<Note> notesList = null;

		try {
			notesList = notesDAO.getCreatedNotes(idUser, offset); // should i really use ArrayList?
		} catch (DaoException ex) {
			throw new ServiceException("can't get created notes" + ex);
		}

		return notesList;
	}

	@Override
	public List<Note> getAvailableNotes(int idUser, int offset) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		List<Note> notesList = null;

		try {
			notesList = notesDAO.getAvailableNotes(idUser, offset); 
		} catch (DaoException ex) {
			throw new ServiceException("can't get created notes" + ex);
		}

		return notesList;
	}

	@Override
	public List<Note> getLastNotes(int idUser) throws ServiceException {
		return getLastNotes(idUser, 0); // duplicate in SQLDAO
	}

	@Override
	public List<Note> getLastNotes(int idUser, int offset) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.getLastNotes(idUser, offset);
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public int getNumberOfCreatedNotes(int iduser) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.getNumberOfCreatedNotes(iduser);
		} catch (DaoException ex) {
			// logger?
			throw new ServiceException(ex);
		}
	}

	@Override
	public int getNumberOfAvailableNotes(int iduser) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.getNumberOfAvailableNotes(iduser);
		} catch (DaoException ex) {
			// logger?
			throw new ServiceException(ex);
		}
	}

	@Override
	public int getNumberOfSharedNotes(int iduser) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.getNumberOfSharedNotes(iduser);
		} catch (DaoException ex) {
			// logger?
			throw new ServiceException(ex);
		}
	}

	@Override
	public Note getNote(int idnote) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.getNote(idnote);
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}

	}

	@Override
	public List<Note> getSharedNotes(int idUser) throws ServiceException {
		return getSharedNotes(idUser, 0);
	}

	@Override
	public List<Note> getSharedNotes(int idUser, int offset) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.getSharedNotes(idUser, offset);
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<User> showUsersWithAccess(int idNote, int idUser) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		try {
			return notesDAO.showUsersWithAccess(idNote, idUser);
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public boolean editUserAccess(int idNote, int idHostUser, int idTargetUser, int accessLevel) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		NotesDAO notesDAO = daoProvider.getNotesDAO();

		boolean result = false;
		try {
			result = notesDAO.removeUserFromNote(idNote, idHostUser, idTargetUser);
			if (result) {
				result = notesDAO.addUserToNote(idNote, idHostUser, idTargetUser, accessLevel);
			}
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
		return result;

	}

}
