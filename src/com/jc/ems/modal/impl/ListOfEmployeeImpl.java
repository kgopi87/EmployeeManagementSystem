package com.jc.ems.modal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jc.ems.constant.DBConstant;
import com.jc.ems.modal.database.DatabaseConnector;

public class ListOfEmployeeImpl {
	private DatabaseConnector sql = new DatabaseConnector();
	private ResultSet resultSet = null;
	
	public ResultSet employeeList() throws SQLException {

		sql.setStatement(DBConstant.employeeList);
		sql.prepareQuery();
		resultSet = sql.getStatement().executeQuery();
		return resultSet;
	}
	
	public ResultSet getEmployeeById(String employeeId) throws SQLException {
		sql.setStatement(DBConstant.demographics);
		sql.prepareQuery();
		sql.getStatement().setString(1, employeeId);
		resultSet = sql.getStatement().executeQuery();
		return resultSet;
	}
}
