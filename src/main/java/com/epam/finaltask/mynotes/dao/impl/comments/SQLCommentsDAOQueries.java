package com.epam.finaltask.mynotes.dao.impl.comments;

public class SQLCommentsDAOQueries {

	protected static final int RESULTS = 10;
	
	protected static final int NOTE_COMMENT_ACCESS_LEVEL = 1;
	
	protected static final String QUERY_CHECK_IF_USER_HAVE_ACCESS = "SELECT iduser from access WHERE iduser=? and idnote=? and access_level>=" + NOTE_COMMENT_ACCESS_LEVEL;
	
	protected static final String QUERY_GET_COMMENTS = "SELECT * from comments WHERE idnote=? ORDER by created DESC limit ?," + RESULTS ;
	
	protected static final String QUERY_GET_QUANITY_OF_COMMENTS = "SELECT count(*) from comments WHERE idnote=? ";

	protected static final String QUERY_ADD_COMMENT = "INSERT INTO comments (comment_text, idnote, iduser) VALUES (?, ?, ?)";
	
	protected static final String QUERY_CHECK_COMMENT_AUTHOR = "SELECT iduser from comments where idcomments=?";
	
	protected static final String QUERY_REMOVE_COMMENT = "DELETE FROM comments WHERE idcomments=?";

	protected static final String QUERY_GET_LOGIN_BY_ID = "SELECT login from user where iduser=?";

	protected static final String QUERY_CHECK_IF_ADMIN = "SELECT access_level from user where iduser=?";
}
