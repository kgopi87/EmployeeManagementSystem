package com.jc.ems.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jc.ems.App;
import com.jc.ems.constant.MessageConstant;
import com.jc.ems.modal.common.FieldValidation;
import com.jc.ems.modal.common.Messages;
import com.jc.ems.modal.impl.AddEmployeeImpl;
import com.jc.ems.modal.impl.EmployeeImpl;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddEmployee {

	@FXML
	private Tab demographicsTab, educationTab;

	@FXML
	private TextField employeeId, givenName, surName, phone, email, noOfChildren, proofValue, empTitle;

	@FXML
	private TextField qualification1, qualification2, qualification3, paidLeaves, unpaidLeaves, workingDays,
			pfAccountNo, lop, perDayWages;

	@FXML
	private CheckBox disableEmployee;

	@FXML
	private ComboBox<String> proof, maritalStatus, gender, department, education1, education2, education3;

	@FXML
	private DatePicker dob, doj, from1, to1, from2, to2, from3, to3;

	@FXML
	private TextArea address, memo1, memo2, memo3;

	@FXML
	private Text wages;

	@FXML
	public void navigateAfterLogin() throws IOException {
		App.setRoot("AfterLogin");
	}

	@FXML
	public Boolean employeeIdValidation() throws SQLException {
		if (getEmployeeImpl().employeeIdExists(employeeId.getText().toUpperCase())) {
			Messages.errorMessage(MessageConstant.existsEmployeeId, null, MessageConstant.defaultTitle);
			return false;
		}

		if (employeeId.getLength() > 30) {
			Messages.errorMessage(MessageConstant.defaultLength30 + "Employee Id", null, MessageConstant.defaultTitle);
			return false;
		}
		return true;
	}

	@FXML
	private void initialize() {
		this.loadComboBox();
	}

	@FXML
	public void addEmployees() throws SQLException, IOException {
		if (getFieldValidation().textFieldNotNull(employeeId, MessageConstant.employeeIdMandatory)
				&& employeeIdValidation()) {
			addDemographics();
			addEducation();
			addMemos();
			Messages.infoMessage(MessageConstant.addEmployeeSuccess, null, MessageConstant.defaultTitle);
			navigateAfterLogin();
		}
	}

	@FXML
	public void navigateEducation() throws SQLException {
		if (getFieldValidation().textFieldNotNull(employeeId, MessageConstant.employeeIdMandatory)
				&& employeeIdValidation()) {
			demographicsTab.getTabPane().getSelectionModel().selectNext();
		}
	}

	@FXML
	public void navigateMemos() {
		educationTab.getTabPane().getSelectionModel().selectNext();
	}
	
	private AddEmployeeImpl getAddEmployee() {
		return new AddEmployeeImpl();
	}

	private EmployeeImpl getEmployeeImpl() {
		return new EmployeeImpl();
	}

	private FieldValidation getFieldValidation() {
		return new FieldValidation();
	}

	private void loadComboBox() {
		getFieldValidation().setComboItems(proof, getAddEmployee().getProofCombo());
		getFieldValidation().setComboItems(maritalStatus, getAddEmployee().getMartialCombo());
		getFieldValidation().setComboItems(gender, getAddEmployee().getGenderCombo());
		getFieldValidation().setComboItems(department, getAddEmployee().getDepartmentCombo());
		getFieldValidation().setComboItems(education1, getAddEmployee().getEducationCombo());
		getFieldValidation().setComboItems(education2, getAddEmployee().getEducationCombo());
		getFieldValidation().setComboItems(education3, getAddEmployee().getEducationCombo());

		education1.setEditable(true);
		education2.setEditable(true);
		education3.setEditable(true);
	}

	public void addDemographics() throws SQLException {
		ArrayList<String> employeeDemographics = new ArrayList<String>();
		employeeDemographics.add(0, employeeId.getText().toUpperCase());
		employeeDemographics.add(1, (disableEmployee.isSelected() ? "TRUE" : "FALSE"));
		employeeDemographics.add(2, givenName.getText());
		employeeDemographics.add(3, surName.getText());
		employeeDemographics.add(4, empTitle.getText());
		employeeDemographics.add(5, getFieldValidation().getDatePicker(doj));
		employeeDemographics.add(6, address.getText());
		employeeDemographics.add(7, getFieldValidation().getDatePicker(dob));
		employeeDemographics.add(8, gender.getValue());
		employeeDemographics.add(9, phone.getText());
		employeeDemographics.add(10, email.getText());
		employeeDemographics.add(11, proof.getValue());
		employeeDemographics.add(12, proofValue.getText());
		employeeDemographics.add(13, maritalStatus.getValue());
		employeeDemographics.add(14, noOfChildren.getText());
		employeeDemographics.add(15, department.getValue());

		getAddEmployee().addDemographics(employeeDemographics);
	}

	public void addEducation() throws SQLException {
		ArrayList<String> employeeEducation = new ArrayList<String>();
		employeeEducation.add(0, employeeId.getText());
		employeeEducation.add(1, education1.getValue());
		employeeEducation.add(2, qualification1.getText());
		employeeEducation.add(3, getFieldValidation().getDatePicker(from1));
		employeeEducation.add(4, getFieldValidation().getDatePicker(to1));
		employeeEducation.add(5, education2.getValue());
		employeeEducation.add(6, qualification2.getText());
		employeeEducation.add(7, getFieldValidation().getDatePicker(from2));
		employeeEducation.add(8, getFieldValidation().getDatePicker(to2));
		employeeEducation.add(9, education3.getValue());
		employeeEducation.add(10, qualification3.getText());
		employeeEducation.add(11, getFieldValidation().getDatePicker(from3));
		employeeEducation.add(12, getFieldValidation().getDatePicker(to3));

		getAddEmployee().addEducation(employeeEducation);
	}

	public void addMemos() throws SQLException {
		ArrayList<String> employeeMemos = new ArrayList<String>();
		employeeMemos.add(0, employeeId.getText());
		employeeMemos.add(1, memo1.getText());
		employeeMemos.add(2, memo2.getText());
		employeeMemos.add(3, memo3.getText());

		getAddEmployee().addMemos(employeeMemos);
	}
}
