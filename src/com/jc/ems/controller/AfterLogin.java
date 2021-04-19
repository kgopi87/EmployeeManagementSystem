package com.jc.ems.controller;

import java.io.IOException;
import com.jc.ems.App;
import com.jc.ems.constant.CommonConstant;
import com.jc.ems.constant.MessageConstant;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AfterLogin {

	@FXML
	public Label logininfo;

	@FXML
	public void navigateAddEmployee() throws IOException {
		App.setRoot("AddEmployee");
	}

	@FXML
	public void navigateListEmployee() throws IOException {
		App.setRoot("ListOfEmployees");
	}

	@FXML
	public void navigateReports() throws IOException {
		App.setRoot("Reports");
	}

	@FXML
	public void initialize() {
		logininfo.setText(MessageConstant.welcomeBack + CommonConstant.userName);
	}
}
