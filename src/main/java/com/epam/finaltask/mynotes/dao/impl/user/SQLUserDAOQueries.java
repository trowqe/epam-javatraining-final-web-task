package com.epam.finaltask.mynotes.dao.impl.user;

public class SQLUserDAOQueries {
	protected static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM user WHERE login=? and password=?";
	protected static final String QUERY_CHECK_PASSWORD = "SELECT * FROM user WHERE login=?";
	protected static final String QUERY_USER_EXISTS_CHECK = "SELECT iduser FROM user WHERE login=?";
	protected static final String QUERY_REGISTER_CREDENTIONALS = "INSERT INTO user(email,login,password) VALUES(?, ?, ?)";

}
