package com.epam.finaltask.mynotes.dao.impl.user;

import com.epam.finaltask.mynotes.dao.UserDAO;
import com.epam.finaltask.mynotes.dao.exception.DaoException;
import com.epam.finaltask.mynotes.dao.impl.SqlDao;
import com.epam.finaltask.mynotes.dao.impl.connection.ConnectionPool;
import com.epam.finaltask.mynotes.dao.impl.util.SQLUtils;
import com.epam.finaltask.mynotes.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.finaltask.mynotes.dao.impl.user.SQLUserDAOQueries.*;

public class SQLUserDAO extends SqlDao implements UserDAO {

	private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);
	
	private static final ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public User authentification(String userLogin, String userPassword) throws DaoException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		User user = null;

		try {
			con = pool.getConnection();
			st = con.prepareStatement(QUERY_CHECK_PASSWORD);

			st.setString(1, userLogin);

			rs = st.executeQuery();

			if (rs.next()) {
				boolean matched = BCrypt.checkpw(userPassword, rs.getString("password"));
				if (matched) {
					user = createUser(rs);
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, st, con);
		}

		return user;
	}

	private User createUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt("iduser"));
		user.setEmail(rs.getString("email"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setAccessLevel(Integer.parseInt(rs.getString("access_Level")));

		return user;

	}

	
	@Override
	public boolean registration(String userLogin, String userPassword, String userEmail) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stRegister = null;
		ResultSet rs = null;
		int result;

		User user = null;

		try {
			con = pool.getConnection();
			con.setAutoCommit(false);

			stCheck = con.prepareStatement(QUERY_USER_EXISTS_CHECK);
			stRegister = con.prepareStatement(QUERY_REGISTER_CREDENTIONALS);

			stCheck.setString(1, userLogin);

			rs = stCheck.executeQuery();

			if (!rs.next()) {
				stRegister.setString(1, userEmail);
				stRegister.setString(2, userLogin);
				stRegister.setString(3, userPassword);

				result = stRegister.executeUpdate();
			} else {
				return false; // user already exists //SQLUtils.close/RETURN THE CONNECTION
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
			SQLUtils.close(rs, stCheck, stRegister, con);
		}

		return true;
	}

}
