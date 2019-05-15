package com.epam.finaltask.mynotes.dao.impl.comments;

import com.epam.finaltask.mynotes.dao.CommentsDAO;
import com.epam.finaltask.mynotes.dao.exception.DaoException;
import com.epam.finaltask.mynotes.dao.impl.SqlDao;
import com.epam.finaltask.mynotes.dao.impl.connection.ConnectionPool;
import com.epam.finaltask.mynotes.dao.impl.util.SQLUtils;
import com.epam.finaltask.mynotes.entity.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.finaltask.mynotes.dao.impl.comments.SQLCommentsDAOQueries.*;

public class SQLCommentsDAO extends SqlDao implements CommentsDAO {
	protected static final Logger logger = LogManager.getLogger(SQLCommentsDAO.class);

	protected static final ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public List<Comment> getComments(int iduser, int idnote, int offset) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stGet = null;
		ResultSet rs = null;

		List<Comment> comments = new ArrayList<>();

		try {
			con = pool.getConnection();
			stCheck = con.prepareStatement(QUERY_CHECK_IF_USER_HAVE_ACCESS);
			stGet = con.prepareStatement(QUERY_GET_COMMENTS);

			stCheck.setInt(1, iduser);
			stCheck.setInt(2, idnote);

			rs = stCheck.executeQuery();

			if (rs.next()) {
				stGet.setInt(1, idnote);
				stGet.setInt(2, offset);

				rs = stGet.executeQuery();

				while (rs.next()) {
					comments.add(toComment(rs));
				}
			}
		} catch (SQLException ex) {
			logger.warn(ex);
		} finally {
			SQLUtils.close(rs, stCheck, stGet, con);
		}

		return comments;
	}

	private String getAuthor(int iduser) {
		Connection con = null;
		PreparedStatement stCheck = null;
		ResultSet rs = null;

		String result = null;
		try {
			con = pool.getConnection();
			stCheck = con.prepareStatement(QUERY_GET_LOGIN_BY_ID);

			stCheck.setInt(1, iduser);

			rs = stCheck.executeQuery();

			if (rs.next()) {

				result = rs.getString("login");
			}

		} catch (SQLException ex) {
			logger.warn(ex);
		} finally {
			SQLUtils.close(rs, stCheck, con);
		}

		return result;
	}

	private Comment toComment(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		
		comment.setIdComment(rs.getInt("idcomments"));
		comment.setAuthor(getAuthor(rs.getInt("iduser")));
		comment.setDate(rs.getString("created"));
		comment.setIdUser(rs.getInt("iduser"));
		comment.setText(rs.getString("comment_text"));

		return comment;
	}

	@Override
	public int getQuanityOfComments(int iduser, int idnote) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stGet = null;
		ResultSet rs = null;

		int result = 0;
		try {
			con = pool.getConnection();
			stCheck = con.prepareStatement(QUERY_CHECK_IF_USER_HAVE_ACCESS);
			stGet = con.prepareStatement(QUERY_GET_QUANITY_OF_COMMENTS);

			stCheck.setInt(1, iduser);
			stCheck.setInt(2, idnote);

			rs = stCheck.executeQuery();

			if (rs.next()) {
				stGet.setInt(1, idnote);
				rs.close();

				rs = stGet.executeQuery();
				if (rs.next()) {
					result = rs.getInt("count(*)");
				}
			}

		} catch (SQLException ex) {
			logger.warn(ex);
		} finally {
			SQLUtils.close(rs, stCheck, stGet, con);
		}

		return result;
	}

	@Override
	public boolean addComment(int iduser, int idnote, String text) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stAdd = null;
		ResultSet rs = null;

		int result = 0;
		boolean res = false;
		try {

			con = pool.getConnection();
			con.setAutoCommit(false);
			stCheck = con.prepareStatement(QUERY_CHECK_IF_USER_HAVE_ACCESS);
			stAdd = con.prepareStatement(QUERY_ADD_COMMENT);

			stCheck.setInt(1, iduser);
			stCheck.setInt(2, idnote);

			rs = stCheck.executeQuery();

			if (rs.next()) {
				stAdd.setString(1, text);
				stAdd.setInt(2, idnote);
				stAdd.setInt(3, iduser);

				result = stAdd.executeUpdate();
				res = true;
			}

			con.commit();
		} catch (SQLException e) {
			logger.warn(e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.warn("can't rollback while adding note!" + e1);
			}

			throw new DaoException(e);
		} finally {
			SQLUtils.close(rs, stCheck, stAdd, con);
		}

		return res;
	}

	@Override
	public boolean removeComment(int iduser, int idcomment) throws DaoException {
		Connection con = null;
		PreparedStatement stCheck = null;
		PreparedStatement stCheckIfAdmin = null;
		PreparedStatement stRemove = null;
		ResultSet rs = null;

		boolean result = false;
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);

			stCheck = con.prepareStatement(QUERY_CHECK_COMMENT_AUTHOR);
			stRemove = con.prepareStatement(QUERY_REMOVE_COMMENT);

			stCheck.setInt(1, idcomment);

			rs = stCheck.executeQuery();
			
			if (rs.next() && rs.getInt("iduser") == iduser) {

				stRemove.setInt(1, idcomment);
				stRemove.executeUpdate();
			} else {
				rs.close();
				stCheckIfAdmin = con.prepareStatement(QUERY_CHECK_IF_ADMIN);
				stCheckIfAdmin.setInt(1, iduser);
				if (rs.getInt("access_level") == 1) {

					stRemove.setInt(1, idcomment);
					stRemove.executeUpdate();
				} else {
					return false;
				}
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
			SQLUtils.close(rs, stCheck, stRemove, con);
		}

		return true;
	}

}
