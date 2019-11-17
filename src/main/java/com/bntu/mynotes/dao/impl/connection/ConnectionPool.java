package com.epam.finaltask.mynotes.dao.impl.connection;

import com.epam.finaltask.mynotes.dao.impl.SqlDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConnectionPool extends SqlDao {
	private static final int INITIAL_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 5;

	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

	private static final ConnectionPool instance = new ConnectionPool();

	private List<Connection> connectionPool;
	private List<Connection> usedConnections = new ArrayList<>();

	private ConnectionPool() {

		List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
		try {
			for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
				pool.add(createConnection(url, login, password));
			}
		} catch (SQLException ex) {
			logger.error("can't create conPool" + ex);
		}
		
		this.connectionPool = pool;

	}
	
	public Connection getConnection() throws SQLException {
	    if (connectionPool.isEmpty()) {
	        if (usedConnections.size() < MAX_POOL_SIZE) {
	            connectionPool.add(createConnection(url, login, password));
	        } else {
	        	//any variant to use queue?
	            throw new RuntimeException(
	              "Maximum pool size reached, no available connections!");
	        }
	    }
	 
	    Connection connection = connectionPool
	      .remove(connectionPool.size() - 1);
	    usedConnections.add(connection);
	    
	    
	    if (connection == null) {
	    }
	    return connection;
	}

	public void releaseConnection(Connection connection) {
		//should i check for null? connection could be dead
		usedConnections.remove(connection);
		connectionPool.add(connection);
	}

	private static Connection createConnection(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}
	
	//singleton
	public static ConnectionPool getInstance() {
		return instance;
	}

}
