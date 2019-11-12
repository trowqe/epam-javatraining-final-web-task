package com.epam.finaltask.mynotes.dao.impl.note;

public class SQLNotesDAOQueries {
	/* default quanity of returned notes */
	protected static final int RESULTS = 10;

	protected static final int NOTE_CREATOR_ACCESS_LEVEL = 2;

	protected static final String QUERY_CHECK_IF_NOTE_EXISTS = "SELECT title FROM note WHERE idnote in (SELECT idnote FROM access WHERE iduser=? and access_level=2) AND title=?";

	protected static final String QUERY_CHECK_IF_NOTE_EXISTS_AND_EDITABLE = "SELECT title FROM note WHERE idnote IN (SELECT idnote FROM access WHERE iduser=? and access_level=2 and idnote=?)";

	protected static final String QUERY_CHECK_IF_NOTE_EXISTS_AND_OWN = "SELECT title FROM note WHERE idnote=? and iduser=?";

	
	
	protected static final String QUERY_GET_NOTE_BY_ID = "SELECT * FROM note WHERE idnote=?";

	protected static final String QUERY_GET_CREATED_NOTES = "SELECT * FROM note WHERE iduser=? LIMIT ?," + RESULTS;

	protected static final String QUERY_GET_CREATED_NOTES_DESC = "SELECT * FROM note WHERE iduser=? order by created DESC LIMIT ?,"
			+ RESULTS;
	protected static final String QUERY_GET_QUANITY_OF_CREATED_NOTES = "SELECT COUNT(*) FROM note WHERE iduser=?";
	
	protected static final String QUERY_GET_AVAILABLE_NOTES = "SELECT * FROM note "
			+ "WHERE idnote IN (SELECT idnote FROM access WHERE iduser=? AND access_level>-1) LIMIT ?," + RESULTS;
	
	protected static final String QUERY_GET_AVAILABLE_NOTES_DESC = "SELECT * FROM note "
			+ "WHERE idnote IN (SELECT idnote FROM access WHERE iduser=?) ORDER BY DESC LIMIT ?," + RESULTS;
	
	protected static final String QUERY_GET_QUANITY_OF_AVAILABLE_NOTES = "SELECT COUNT(*) FROM note "
			+ "WHERE idnote IN (SELECT idnote FROM access WHERE iduser=?)";

	protected static final String QUERY_GET_SHARED_NOTES = "SELECT * FROM note WHERE iduser=? AND idnote IN (SELECT idnote FROM access WHERE iduser<>?) LIMIT ?,"
			+ RESULTS;
	
	protected static final String QUERY_GET_QUANITY_OF_SHARED_NOTES = "SELECT count(*) FROM note WHERE iduser=? AND idnote IN (SELECT idnote FROM access WHERE iduser<>?)";
	
	protected static final String QUERY_GET_USERS_WHO_CAN_ACCES_NOTE = "SELECT user.iduser, user.login, access.access_level FROM user INNER JOIN access using(iduser) where idnote=? and iduser<>?";
	
	
	
	protected static final String QUERY_CREATE_NOTE = "INSERT INTO note"
			+ "(title, text, preview, iduser) VALUES (?,?,?,?) ";

	protected static final String QUERY_CREATE_ACCESS_FIELD = "INSERT INTO access"
			+ "(idnote, iduser, access_level) VALUES(?,?,?)";
	
	protected static final String QUERY_CHANGE_VISIBILITY_OF_NOTE = "UPDATE note " + "SET can_access=? WHERE idnote=?";
	
	protected static final String QUERY_UPDATE_NOTE = "UPDATE note "
			+ "SET title=?, text=?, preview=? WHERE iduser=? AND idnote=?";
	
	protected static final String QUERY_UPDATE_NOTE_WITHOUT_PREVIEW = "UPDATE note "
			+ "SET title=?, text=? WHERE iduser=? AND idnote=?";
	
	protected static final String QUERY_UPDATE_NOTE_ACCESS = "UPDATE access" + "SET can_access=? WHERE idnote=? and iduser=?";
	
	protected static final String QUERY_REMOVE_NOTE = "DELETE FROM note " + "WHERE idnote=? AND iduser=?"; // check if
																																																			// access
	protected static final String QUERY_REMOVE_ACCESS_RECORDS_WITH_NOTE = "DELETE FROM access WHERE idnote=?";

	protected static final String QUERY_REMOVE_ACCESS_RECORD = "DELETE FROM access WHERE idnote=? AND iduser=?";

}
