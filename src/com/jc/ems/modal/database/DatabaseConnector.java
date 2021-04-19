package com.jc.ems.modal.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jc.ems.constant.DBConstant;

/**
 * @author Janani.c
 * @since 17-April-2021
 * @category Modal
 * @version 1.0
 * @apiNote DatabaseConnector.class utlility will connect to Access Database and
 *          perform DML operation. API used to access database are
 *          "net.ucanaccess.jdbc.UcanaccessDriver". Dependencies used for DB
 *          access 
 *          |Libraries 					|Version|
 *          -------------------------------------
 *          |commons-lang-2.6.jar		|2.6	|
 *          |commons-logging-1.1.1.jar	|1.1.1	|
 *          |hsqldb-2.3.1.jar			|2.3.1	|
 *          |jackcess-2.1.6.jar			|2.1.6	|
 *          |ucanaccess-4.0.1.jar		|4.0.1	|
 *
 */
public class DatabaseConnector {

	private String QUERY = null;
	private Connection connection = null;
	private PreparedStatement statement = null;
	
	
	/**
	 * @implNote prepareQuery makes handshake to database connection
	 */
	public void prepareQuery() {

		try {

			Class.forName(DBConstant.dbClassName);
		} catch (ClassNotFoundException cnfex) {

			System.err.println("Problem in loading or registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}

		try {

			connection = DriverManager.getConnection(DBConstant.connectionString);
			statement = connection.prepareStatement(QUERY);

		} catch (SQLException sqlex) {
			System.err.println("SQL Queries Execution Failed !!!!");
			sqlex.printStackTrace();
		}
	}

	/**
	 * @param sqlQuery
	 * @implNote Setter method accepts prepared statement query
	 */
	public void setStatement(String sqlQuery) {
		this.QUERY = sqlQuery;
	}
	
	
	/**
	 * @implNote Closes all the connection with Database
	 * @throws SQLException
	 */
	public void closeAll() throws SQLException {
		getStatement().close();
		getConnection().close();
	}

	/**
	 * 
	 * @return this.statment is returned
	 */
	public PreparedStatement getStatement() {
		return statement;
	}

	/**
	 * @return this.connection is returned
	 */
	public Connection getConnection() {
		return connection;
	}

}
