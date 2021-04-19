package com.jc.ems.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jc.ems.App;
import com.jc.ems.constant.CommonConstant;
import com.jc.ems.constant.MessageConstant;
import com.jc.ems.modal.common.Messages;
import com.jc.ems.modal.common.TableData;
import com.jc.ems.modal.impl.EmployeeImpl;
import com.jc.ems.modal.impl.ListOfEmployeeImpl;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ListOfEmployees {

	@FXML
	private TableView<String> listEmployee;

	@FXML
	private TextField searchEmployeeId;

	@FXML
	private ImageView searchIcon;

	@FXML
	public void initialize() throws IOException, SQLException {
		loadEmployeeTableView();
	}

	@FXML
	public void navigateAfterLogin() throws IOException {
		App.setRoot("AfterLogin");
	}

	@FXML
	public void navigateToEmployee() throws IOException, SQLException {
		if (validateEmployeeId()) {
			App.setRoot("Employee");
		}
	}

	public Boolean validateEmployeeId() throws IOException, SQLException {

		if (searchEmployeeId.getText().equalsIgnoreCase("") || searchEmployeeId.getText().isEmpty()) {
			Messages.errorMessage(MessageConstant.employeeIdMandatory, null, MessageConstant.defaultError);
			return false;
		}

		if (getEmployeeImpl().employeeIdExists(searchEmployeeId.getText())) {
			setEmployeeID(searchEmployeeId.getText().toUpperCase());
			return true;
		} else {
			Messages.errorMessage(MessageConstant.notEmployeeId, null, MessageConstant.defaultError);
		}

		return false;
	}

	private void loadEmployeeTableView() throws SQLException {
		getTableData().setTableData(listEmployee, getListOfEmployeeImpl().employeeList());
	}

	private void setEmployeeID(String employeeId) {
		CommonConstant.employeeID = employeeId;
	}

	private ListOfEmployeeImpl getListOfEmployeeImpl() {
		return new ListOfEmployeeImpl();
	}

	private TableData getTableData() {
		return new TableData();
	}

	private EmployeeImpl getEmployeeImpl() {
		return new EmployeeImpl();
	}

}
