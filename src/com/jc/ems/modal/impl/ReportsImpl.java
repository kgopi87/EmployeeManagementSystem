package com.jc.ems.modal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jc.ems.constant.DBConstant;
import com.jc.ems.modal.database.DatabaseConnector;

public class ReportsImpl {
	
	private DatabaseConnector sql = new DatabaseConnector();
	
	public ResultSet currentMonthPayrollSql(String Month) throws SQLException {
		sql.setStatement(DBConstant.currentMonthPayrollReport);
		sql.prepareQuery();
		sql.getStatement().setString(1, Month);
		return sql.getStatement().executeQuery();
	}
	
	public ResultSet currentMonthLeaveSql(String Month, String Unpaid) throws SQLException {
		sql.setStatement(DBConstant.currentMonthleave);
		sql.prepareQuery();
		sql.getStatement().setString(1, Month);
		sql.getStatement().setString(2, Unpaid);
		return sql.getStatement().executeQuery();
	}
	
	public ResultSet employeeActiveStatusSql(String Disabled) throws SQLException {
		sql.setStatement(DBConstant.activeEmployee);
		sql.prepareQuery();
		sql.getStatement().setString(1, Disabled);
		return sql.getStatement().executeQuery();
	}

}
