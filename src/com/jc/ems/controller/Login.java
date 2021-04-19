package com.jc.ems.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jc.ems.App;
import com.jc.ems.constant.CommonConstant;
import com.jc.ems.constant.MessageConstant;
import com.jc.ems.modal.common.Messages;
import com.jc.ems.modal.impl.LoginImpl;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {
	
	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private void loginValidate() throws IOException {
		
		//Variables
		boolean validLogin = false;

		//DatabaseConnection
		try {
			validLogin = getLoginImpl().loginExists(userName.getText().toLowerCase(), password.getText());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Validation
		if (validLogin) {
			Messages.infoMessage(MessageConstant.validLogin + userName.getText().toUpperCase(), null, MessageConstant.defaultTitle);
			setUsername(userName.getText().toUpperCase());
			App.setRoot("AfterLogin");
		} else {
			Messages.errorMessage(MessageConstant.invalidLogin, null, MessageConstant.defaultTitle);
		}

	}
	
	private LoginImpl getLoginImpl() {
		return new LoginImpl();
	}
	
	public void setUsername(String username) {
		CommonConstant.userName = username;
	}
}
