package com.jc.ems.modal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jc.ems.constant.CommonConstant;
import com.jc.ems.constant.DBConstant;
import com.jc.ems.modal.common.Calenders;
import com.jc.ems.modal.database.DatabaseConnector;

public class EmployeeImpl {

	private DatabaseConnector sql = new DatabaseConnector();
	private ResultSet resultSet = null;
	private ArrayList<String> alist = new ArrayList<String>();
	
	private Calenders getCalenders() {
		return new Calenders();
	}

	public Boolean employeeIdExists(String employeeId) throws SQLException {
		boolean employeeIdExists = false;
		resultSet = viewSql(DBConstant.employeeId, employeeId);

		while (resultSet.next()) {
			if (resultSet.getInt(1) > 0) {
				employeeIdExists = true;
			}
		}
		return employeeIdExists;
	}

	public ArrayList<String> getEmployeeDemographics() throws SQLException {
		resultSet = viewSql(DBConstant.demographics, CommonConstant.employeeID);

		while (resultSet.next()) {
			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
				alist.add(i, resultSet.getString(i + 1));
			}
		}
		return alist;
	}

	public ArrayList<String> getEmployeeEducation() throws SQLException {
		resultSet = viewSql(DBConstant.education, CommonConstant.employeeID);

		while (resultSet.next()) {
			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
				alist.add(i, resultSet.getString(i + 1));
			}
		}
		return alist;
	}

	public ArrayList<String> getEmployeeWages() throws SQLException {
		resultSet = viewSql(DBConstant.wages, CommonConstant.employeeID);

		while (resultSet.next()) {
			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
				alist.add(i, resultSet.getString(i + 1));
			}
		}
		return alist;
	}

	public ArrayList<String> getEmployeeMemos() throws SQLException {
		resultSet = viewSql(DBConstant.memos, CommonConstant.employeeID);

		while (resultSet.next()) {
			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
				alist.add(i, resultSet.getString(i + 1));
			}
		}
		return alist;
	}
	
	public Integer getUnpaidLeaveCount() throws SQLException {
		resultSet = getUnpaidLeaveSql("TRUE");
		
		while (resultSet.next()) {
			return resultSet.getInt(1);
		}
		return null;
	}
	
	public Integer getPaidLeaveCount() throws SQLException {
		resultSet = getUnpaidLeaveSql("FALSE");
		
		while (resultSet.next()) {
			return resultSet.getInt(1);
		}
		return null;
	}
	
	public ArrayList<String> getLeaveList() throws SQLException {
		resultSet = viewSql(DBConstant.memos, CommonConstant.employeeID);

		while (resultSet.next()) {
			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
				alist.add(i, resultSet.getString(i + 1));
			}
		}
		return alist;
	}

	private ResultSet viewSql(String selectQuery, String employeeId) throws SQLException {
		sql.setStatement(selectQuery);
		sql.prepareQuery();
		sql.getStatement().setString(1, employeeId.toUpperCase());
		return sql.getStatement().executeQuery();
	}
	
	public ResultSet getLeaveSql() throws SQLException {
		sql.setStatement(DBConstant.leaves);
		sql.prepareQuery();
		sql.getStatement().setString(1, (CommonConstant.employeeID).toUpperCase());
		sql.getStatement().setString(2, getCalenders().getCurrentMonth().toUpperCase());
		return sql.getStatement().executeQuery();
	}
	
	public ResultSet getUnpaidLeaveSql(String unpaidLeave) throws SQLException {
		sql.setStatement(DBConstant.paidLeave);
		sql.prepareQuery();
		sql.getStatement().setString(1, (CommonConstant.employeeID));
		sql.getStatement().setString(2, getCalenders().getCurrentMonth().toUpperCase());
		sql.getStatement().setString(3, unpaidLeave.toUpperCase());
		return sql.getStatement().executeQuery();
	}
	public void insertLeaveSql(ArrayList<String> addleave) throws SQLException {
		sql.setStatement(DBConstant.insertLeaves);
		sql.prepareQuery();
		sql.getStatement().setString(1, addleave.get(0));
		sql.getStatement().setString(2, addleave.get(1));
		sql.getStatement().setString(3, addleave.get(2));
		sql.getStatement().setString(4, addleave.get(3));
		sql.getStatement().execute();
	}
	
	public void insertWagesSql(ArrayList<String> addWage) throws SQLException {
		sql.setStatement(DBConstant.insertWages);
		sql.prepareQuery();
		sql.getStatement().setString(1, addWage.get(0));
		sql.getStatement().setString(2, addWage.get(1));
		sql.getStatement().setString(3, addWage.get(2));
		sql.getStatement().setString(4, addWage.get(3));
		sql.getStatement().setString(5, addWage.get(4));
		sql.getStatement().setString(6, addWage.get(5));
		sql.getStatement().setString(7, addWage.get(6));
		sql.getStatement().execute();
	}
	
	public Boolean updateDemographics(ArrayList<String> data) {
		return (updateSql(data,DBConstant.updateDemographics) > 0) ? true : false;
	}
	
	public Boolean updateEducation(ArrayList<String> data) {
		return (updateSql(data,DBConstant.updateEducation) > 0) ? true : false;
	}
	
	public Boolean updateMemos(ArrayList<String> data) {
		return (updateSql(data,DBConstant.updateMemos) > 0) ? true : false;
	}
	
	private Integer updateSql(ArrayList<String> data, String updateQuery) {
		sql.setStatement(updateQuery);
		sql.prepareQuery();

		try {
			for (int i = 0; i < data.size(); i++) {
				sql.getStatement().setString(i + 1, data.get(i));
			}

			return sql.getStatement().executeUpdate();

		} catch (SQLException sqlExp) {
			System.err.println("**************************** " + this.getClass().toString() + " SQL exception ****************************");
			sqlExp.printStackTrace();
		}
		return null;

	}
	
	
}
