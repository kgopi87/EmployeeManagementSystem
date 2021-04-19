package com.jc.ems.modal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.jc.ems.constant.DBConstant;
import com.jc.ems.modal.database.DatabaseConnector;

public class ConfigImpl {
	private DatabaseConnector sql = new DatabaseConnector();
	private ResultSet resultSet = null;
	private HashMap<String, String> map = new HashMap<String, String>();

	public HashMap<String, String> getConfigAll() throws SQLException {
		resultSet = configSql();
		while (resultSet.next()) {
			map.put(resultSet.getString(1), resultSet.getString(2));
		}
		return map;
	}

	private ResultSet configSql() throws SQLException {
		sql.setStatement(DBConstant.configuration);
		sql.prepareQuery();
		sql.getStatement().setString(1, "TRUE");
		return sql.getStatement().executeQuery();
	}
	
	public String getConfig(String text) throws SQLException {
		resultSet = configSql();
		while (resultSet.next()) {
			if(resultSet.getString(1).equalsIgnoreCase(text)) {
				return resultSet.getString(2);
			}
		}
		return null;
		
	}

}
