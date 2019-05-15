package com.epam.finaltask.mynotes.dao.impl.note;

import com.epam.finaltask.mynotes.dao.NotesDAO;
import com.epam.finaltask.mynotes.dao.exception.DaoException;
import com.epam.finaltask.mynotes.dao.impl.SqlDao;
import com.epam.finaltask.mynotes.dao.impl.connection.ConnectionPool;
import com.epam.finaltask.mynotes.dao.impl.util.SQLUtils;
import com.epam.finaltask.mynotes.entity.Note;
import com.epam.finaltask.mynotes.entity.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.finaltask.mynotes.dao.impl.note.SQLNotesDAOQueries.*;

public class SQLNotesDAO extends SqlDao implements NotesDAO {
	protected static final Logger logger = LogManager.getLogger(SQLNotesDAO.class);

	protected static final ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public List<Note> getCreatedNotes(int idUser) throws DaoException {
		return getCreatedNotes(idUser, 0);
	}

	@Override
	public List<Note> getAvailableNotes(int idUser) throws DaoException {
		return getAvailableNotes(idUser, 0);
	}

	@Override
	public List<Note> getLastNotes(int idUser) throws DaoException {
		return getLastNotes(idUser, 0);
	}

	@Override
	public List<Note> getSharedNotes(int idUser) throws DaoException {
		return getSharedNotes(idUser, 0);
	}

	@Override
	public List<Note> getCreatedNotes(int idUser, int offset) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Note> createdNotes = new ArrayList<>();

		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_GET_CREATED_NOTES);

			st.setInt(1, idUser);
			st.setInt(2, offset);

			rs = st.executeQuery();

			while (rs.next()) {
				createdNotes.add(toNote(rs));
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}
		return createdNotes;
	}

	@Override
	public List<Note> getAvailableNotes(int idUser, int offset) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Note> availableNotes = new ArrayList<>();

		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_GET_AVAILABLE_NOTES);

			st.setInt(1, idUser);
			st.setInt(2, offset);

			rs = st.executeQuery();

			while (rs.next()) {
				availableNotes.add(toNote(rs));
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return availableNotes;
	}

	@Override
	public List<Note> getLastNotes(int idUser, int offset) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Note> lastNotes = new ArrayList<>();

		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_GET_CREATED_NOTES_DESC);

			st.setInt(1, idUser);
			st.setInt(2, offset);

			System.out.println(st.toString());

			rs = st.executeQuery();

			while (rs.next()) {
				lastNotes.add(toNote(rs));
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return lastNotes;
	}

	@Override
	public boolean createNote(Note note) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stCreateNote = null;
		PreparedStatement stCreateAccessRow = null;

		ResultSet rs = null;

		int result = -1;

		try {
			con = pool.getConnection();
			con.setAutoCommit(false);

			stCheck = con.prepareStatement(QUERY_CHECK_IF_NOTE_EXISTS);// TODO: replace w/ own notes
			stCreateNote = con.prepareStatement(QUERY_CREATE_NOTE, Statement.RETURN_GENERATED_KEYS);
			stCreateAccessRow = con.prepareStatement(QUERY_CREATE_ACCESS_FIELD);

			stCheck.setInt(1, note.getIduser());
			stCheck.setString(2, note.getTitle());

			rs = stCheck.executeQuery();

			if (!rs.next()) {
				stCreateNote.setString(1, note.getTitle());
				stCreateNote.setString(2, note.getText());
				stCreateNote.setBytes(3, note.getPreview());
				stCreateNote.setInt(4, note.getIduser());

				result = stCreateNote.executeUpdate();

				try (ResultSet generatedKeys = stCreateNote.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						stCreateAccessRow.setInt(1, generatedKeys.getInt(1));
						stCreateAccessRow.setInt(2, note.getIduser());
						stCreateAccessRow.setInt(3, NOTE_CREATOR_ACCESS_LEVEL);
					} else {
						return false;
					}
				}
				result = stCreateAccessRow.executeUpdate();
			} else {
				return false;
			}

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, stCheck, stCreateNote, stCreateAccessRow, con);
		}

		return true;
	}

	@Override
	public boolean updateNote(Note note) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stUpdateNote = null;
		ResultSet rs = null;

		int result;

		try {
			con = pool.getConnection();
			con.setAutoCommit(false);

			stCheck = con.prepareStatement(QUERY_CHECK_IF_NOTE_EXISTS_AND_EDITABLE);
			// add check by id
			stCheck.setInt(1, note.getIduser());
			stCheck.setInt(2, note.getIdnote());
			rs = stCheck.executeQuery();
			// stUpdateNote = con.prepareStatement(QUERY_UPDATE_NOTE);
			if (note.getPreview() != null) {
				stUpdateNote = con.prepareStatement(QUERY_UPDATE_NOTE);
				System.out.println("not null");
				if (rs.next()) {
					stUpdateNote.setString(1, note.getTitle());
					stUpdateNote.setString(2, note.getText());
					stUpdateNote.setBytes(3, note.getPreview());
					stUpdateNote.setInt(4, note.getIduser());
					stUpdateNote.setInt(5, note.getIdnote());

					result = stUpdateNote.executeUpdate();
				} else {
					logger.trace("note not found!");
					return false;
				}
			} else {
				System.out.println("null");
				stUpdateNote = con.prepareStatement(QUERY_UPDATE_NOTE_WITHOUT_PREVIEW);
				if (rs.next()) {
					stUpdateNote.setString(1, note.getTitle());
					stUpdateNote.setString(2, note.getText());
					stUpdateNote.setInt(3, note.getIduser());
					stUpdateNote.setInt(4, note.getIdnote());

					result = stUpdateNote.executeUpdate();
				} else {
					logger.trace("note not found!");
					return false;
				}
			}

			con.commit();

		} catch (SQLException e) {
			logger.warn("Can't commit note's edit");
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.warn("Can't rollback!");
				throw new DaoException(e1);
			}

			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, stCheck, stUpdateNote, con);
		}

		return true;
	}

	@Override
	public boolean hideNote(Note note) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stUpdateNote = null;
		ResultSet rs = null;

		int result;

		try {
			con = pool.getConnection();
			con.setAutoCommit(false);

			stCheck = con.prepareStatement(QUERY_GET_NOTE_BY_ID);
			stUpdateNote = con.prepareStatement(QUERY_CHANGE_VISIBILITY_OF_NOTE);

			// add check by id
			stCheck.setInt(1, note.getIdnote());

			rs = stCheck.executeQuery();

			if (rs.next()) {
				stUpdateNote.setInt(1, note.getCanAccess());
				stUpdateNote.setInt(2, note.getIdnote());

				result = stUpdateNote.executeUpdate();
			} else {
				return false;
			}

			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, stCheck, stUpdateNote, con);
		}

		return true;
	}

	@Override
	public boolean deleteNote(int idNote, int idUser) throws DaoException {
		System.out.println("idnote" + idNote + ",use:" + idUser);
		Connection con = null;

		PreparedStatement stCheckNote = null;
		PreparedStatement stRemoveAccessToNote = null;
		PreparedStatement stRemoveNote = null;
		ResultSet rs = null;

		int result;

		try {
			con = pool.getConnection();
			con.setAutoCommit(false);

			stCheckNote = con.prepareStatement(QUERY_CHECK_IF_NOTE_EXISTS_AND_OWN);
			stRemoveAccessToNote = con.prepareStatement(QUERY_REMOVE_ACCESS_RECORDS_WITH_NOTE);
			stRemoveNote = con.prepareStatement(QUERY_REMOVE_NOTE);
			// add check by id

			stCheckNote.setInt(1, idNote);
			stCheckNote.setInt(2, idUser);
			stRemoveNote.setInt(1, idNote);
			stRemoveNote.setInt(2, idUser);

			stRemoveAccessToNote.setInt(1, idNote);

			rs = stCheckNote.executeQuery();

			if (rs.next()) {
				result = stRemoveAccessToNote.executeUpdate();
				if (result > 0) {
					result = stRemoveNote.executeUpdate();
				}
			} else {
				return false;
			}

			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, stCheckNote, stRemoveNote, stRemoveAccessToNote, con);
		}

		return true;
	}

	@Override
	public boolean addUserToNote(int idNote, int idHostUser, int idTargetUser, int accessLevel) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stCreateAccessRow = null;

		ResultSet rs = null;

		boolean result = false;
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);

			stCheck = con.prepareStatement(QUERY_CHECK_IF_NOTE_EXISTS_AND_OWN);// TODO: replace w/ own notes
			stCreateAccessRow = con.prepareStatement(QUERY_CREATE_ACCESS_FIELD);

			stCheck.setInt(1, idNote);
			stCheck.setInt(2, idHostUser);

			rs = stCheck.executeQuery();

			
			if (rs.next()) {
				System.out.println("idnote:" + idNote + ", idTarg:" + idTargetUser + ", ac" + accessLevel);
				stCreateAccessRow.setInt(1, idNote);
				stCreateAccessRow.setInt(2, idTargetUser);
				stCreateAccessRow.setInt(3, accessLevel);

				stCreateAccessRow.executeUpdate();
				result = true;
			} else {
				return false;
			}

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, stCheck, stCreateAccessRow, con);
		}

		return result;
	}

	@Override
	public boolean removeUserFromNote(int idNote, int idHostUser, int idTargetUser) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stCreateAccessRow = null;

		ResultSet rs = null;

		int result = -1;

		try {
			con = pool.getConnection();
			con.setAutoCommit(false);

			stCheck = con.prepareStatement(QUERY_CHECK_IF_NOTE_EXISTS_AND_OWN);// TODO: replace w/ own notes
			stCreateAccessRow = con.prepareStatement(QUERY_REMOVE_ACCESS_RECORD);

			stCheck.setInt(1, idNote);
			stCheck.setInt(2, idHostUser);

			rs = stCheck.executeQuery();
			System.out.println("1");
			if (rs.next()) {
				System.out.println("2");
				stCreateAccessRow.setInt(1, idNote);
				stCreateAccessRow.setInt(2, idTargetUser);

				result = stCreateAccessRow.executeUpdate();
				System.out.println(result);
			} else {
				return false;
			}

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, stCheck, stCreateAccessRow, con);
		}
		return true;
	}

	@Override
	public int getNumberOfCreatedNotes(int idUser) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		int result = -1;
		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_GET_QUANITY_OF_CREATED_NOTES);

			st.setInt(1, idUser);

			System.out.println(st.toString());

			rs = st.executeQuery();

			if (rs.next()) {
				result = rs.getInt("count(*)");
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return result;
	}

	@Override
	public int getNumberOfAvailableNotes(int idUser) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		int result = -1;
		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_GET_QUANITY_OF_AVAILABLE_NOTES);

			st.setInt(1, idUser);

			System.out.println(st.toString());

			rs = st.executeQuery();

			if (rs.next()) {
				result = rs.getInt("count(*)");
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return result;
	}

	@Override
	public int getNumberOfSharedNotes(int idUser) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		int result = -1;
		try {
			con = pool.getConnection();
			// SHARED BUT NOT CREATED
			st = con.prepareStatement(QUERY_GET_QUANITY_OF_SHARED_NOTES);

			st.setInt(1, idUser);
			st.setInt(2, idUser);

			System.out.println(st.toString());

			rs = st.executeQuery();

			if (rs.next()) {
				result = rs.getInt("count(*)");
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return result;
	}

	@Override
	public Note getNote(int idNote) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Note note = new Note();

		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_GET_NOTE_BY_ID);

			st.setInt(1, idNote);
			rs = st.executeQuery();

			if (rs.next()) {
				note = toNote(rs);
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return note;
	}

	@Override
	public List<Note> getSharedNotes(int idUser, int offset) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Note> sharedNotes = new ArrayList<>();

		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_GET_SHARED_NOTES);

			st.setInt(1, idUser);
			st.setInt(2, idUser);
			st.setInt(3, offset);

			rs = st.executeQuery();

			while (rs.next()) {
				sharedNotes.add(toNote(rs));
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return sharedNotes;
	}

	@Override
	public List<User> showUsersWithAccess(int idNote, int idUser) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<User> usersWithAccessToNote = new ArrayList<>();

		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_GET_USERS_WHO_CAN_ACCES_NOTE);

			st.setInt(1, idNote);
			st.setInt(2, idUser);

			rs = st.executeQuery();

			while (rs.next()) {
				usersWithAccessToNote.add(toUser(rs)); // refactor w/ using lambda
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return usersWithAccessToNote;
	}
	
	private Note toNote(ResultSet rs) throws SQLException, UnsupportedEncodingException {
		Note note = new Note();

		note.setIdnote(rs.getInt("idnote"));
		note.setTitle(rs.getString("title"));
		note.setText(rs.getString("text"));
		note.setCreated(rs.getString("created"));
		note.setPreview(rs.getBytes("preview"));
		note.setPreviewEnc(new String(Base64.encodeBase64(rs.getBytes("preview")), "UTF-8"));
		note.setCanAccess(rs.getInt("can_access"));
		note.setIduser(rs.getInt("iduser"));

		return note;
	}
	
	private User toUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt("iduser"));
		user.setLogin(rs.getString("login"));
		user.setNoteAccess(rs.getInt("access_level"));

		return user;
	}
}
