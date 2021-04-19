package com.jc.ems.modal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jc.ems.constant.DBConstant;
import com.jc.ems.modal.common.Crypto;
import com.jc.ems.modal.database.DatabaseConnector;

public class LoginImpl {
	
	private DatabaseConnector sql = new DatabaseConnector();
	private ResultSet resultSet = null;
	
	private Crypto getCrypto() {
		return new Crypto();
	}
	
	public Boolean loginExists(String userName, String password) throws SQLException {
		boolean loginExists = false;
		
		sql.setStatement(DBConstant.loginQuery);
		sql.prepareQuery();
		sql.getStatement().setString(1, userName);
		sql.getStatement().setString(2, getCrypto().encryptData(password));
		
		resultSet = sql.getStatement().executeQuery();

		while (resultSet.next()) {
			if (resultSet.getBoolean(1)) {
				loginExists = false;
			} else {
				loginExists = true;
			}
		}
		
		resultSet.close();
		sql.getStatement().close();
		sql.getConnection().close();
		
		return loginExists;
	}
}
