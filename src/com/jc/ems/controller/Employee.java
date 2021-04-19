package com.jc.ems.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jc.ems.App;
import com.jc.ems.constant.CommonConstant;
import com.jc.ems.constant.MessageConstant;
import com.jc.ems.modal.common.Calculator;
import com.jc.ems.modal.common.Calenders;
import com.jc.ems.modal.common.FieldValidation;
import com.jc.ems.modal.common.Messages;
import com.jc.ems.modal.common.TableData;
import com.jc.ems.modal.impl.AddEmployeeImpl;
import com.jc.ems.modal.impl.ConfigImpl;
import com.jc.ems.modal.impl.EmployeeImpl;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Employee {

	private ArrayList<String> array;

	@FXML
	private Tab demographicsTab, educationTab, memosTab, leavesTab, payrollTab;

	@FXML
	private AnchorPane demographicsPane, educationPane, memosPane, leavePane, payrollPane;

	@FXML
	private TextField givenName, surName, phone, email, noOfChildren, proofValue, empTitle, qualification1,
			qualification2, qualification3;

	@FXML
	private CheckBox disableEmployee, unpaid;

	@FXML
	private ComboBox<String> proof, maritalStatus, gender, department, education1, education2, education3;

	@FXML
	private DatePicker dob, doj, from1, to1, from2, to2, from3, to3, leaveDate;

	@FXML
	private TextArea address, memo1, memo2, memo3;

	@FXML
	private Text employeeId, wages, currentMonthLeave, currentMonthPayroll, allowedLeavesForMonth, unpaidLeaveMonth,
			paidLeaveMonth, noOfWorkingDays, noOfPayday, noOfUnPayday, perDayWages, currentMonthSalary, wagesBy;

	@FXML
	private TableView<String> leaveTable;

	@FXML
	private Button generatePayroll;

	@FXML
	public void navigateListEmployee() throws IOException {
		App.setRoot("ListOfEmployees");
	}

	@FXML
	public void initialize() throws SQLException {
		if (getEmployeeImpl().employeeIdExists(CommonConstant.employeeID)) {
			loadComboBox();
			setDemographics();
			setEducation();
			setMemo();
			loadLeaveDetails();
			setLeaves();
			setWages();
			disableEmployee();
		}
	}

	@FXML
	public void addleave() throws SQLException {
		if (!getFieldValidation().getDatePicker(leaveDate).equals("")) {
			ArrayList<String> addleave = new ArrayList<String>();
			addleave.add(0, employeeId.getText());
			addleave.add(1, (unpaid.isSelected() ? "TRUE" : "FALSE"));
			addleave.add(2, getFieldValidation().getDatePicker(leaveDate));
			addleave.add(3, getCalenders().getCurrentMonth().toUpperCase());

			if (isLeaveAllowed(employeeId.getText()) && !unpaid.isSelected()) {
				getEmployeeImpl().insertLeaveSql(addleave);
				setLeaves();
				getTableData().refreshTableData(leaveTable, getEmployeeImpl().getLeaveSql());
				
			} else if (unpaid.isSelected()) {
				getEmployeeImpl().insertLeaveSql(addleave);
				setLeaves();
				getTableData().refreshTableData(leaveTable, getEmployeeImpl().getLeaveSql());
				
			} else {
				Messages.errorMessage(MessageConstant.allowedLeaves, null, MessageConstant.defaultError);
			}

		} else {
			Messages.errorMessage(MessageConstant.leaveMandtory, null, MessageConstant.defaultError);
		}

	}
	
	@FXML
	private void generateWages() throws SQLException {
		ArrayList<String> generateWages = new ArrayList<String>();
		
		String paid = getEmployeeImpl().getPaidLeaveCount().toString();
		String unpaid = getEmployeeImpl().getUnpaidLeaveCount().toString();
		String workDay = getCalculator().getNoOfWorkingDays(getConfigImpl().getConfig("WORKINGDAYS"), unpaid);
		String salary = getCalculator().getWages(workDay, unpaid, getConfigImpl().getConfig("PERDAY"));
		
		generateWages.add(0, getFieldValidation().getText(employeeId));
		generateWages.add(1, getCalenders().getCurrentMonth());
		generateWages.add(2, workDay);
		generateWages.add(3, unpaid);
		generateWages.add(4, paid);
		generateWages.add(5, salary);
		generateWages.add(6, CommonConstant.userName);
		
		getEmployeeImpl().insertWagesSql(generateWages);
		setWages(); 
	}
	
	@FXML
	public void updateDemographics() throws SQLException {
		ArrayList<String> employeeDemographics = new ArrayList<String>();
		
		employeeDemographics.add(0, (disableEmployee.isSelected() ? "TRUE" : "FALSE"));
		employeeDemographics.add(1, givenName.getText());
		employeeDemographics.add(2, surName.getText());
		employeeDemographics.add(3, empTitle.getText());
		employeeDemographics.add(4, getFieldValidation().getDatePicker(doj));
		employeeDemographics.add(5, address.getText());
		employeeDemographics.add(6, getFieldValidation().getDatePicker(dob));
		employeeDemographics.add(7, gender.getValue());
		employeeDemographics.add(8, phone.getText());
		employeeDemographics.add(9, email.getText());
		employeeDemographics.add(10, proof.getValue());
		employeeDemographics.add(11, proofValue.getText());
		employeeDemographics.add(12, maritalStatus.getValue());
		employeeDemographics.add(13, noOfChildren.getText());
		employeeDemographics.add(14, department.getValue());
		employeeDemographics.add(15, employeeId.getText().toUpperCase());

		if(getEmployeeImpl().updateDemographics(employeeDemographics)) {
			setDemographics();
			Messages.infoMessage(MessageConstant.updateMessageSuccess, null, MessageConstant.defaultTitle);
		} else {
			Messages.errorMessage(MessageConstant.updateMessageFailure, null, MessageConstant.defaultTitle);
		}
	}
	
	@FXML
	public void updateEducation() throws SQLException {
		ArrayList<String> employeeEducation = new ArrayList<String>();

		employeeEducation.add(0, education1.getValue());
		employeeEducation.add(1, qualification1.getText());
		employeeEducation.add(2, getFieldValidation().getDatePicker(from1));
		employeeEducation.add(3, getFieldValidation().getDatePicker(to1));
		employeeEducation.add(4, education2.getValue());
		employeeEducation.add(5, qualification2.getText());
		employeeEducation.add(6, getFieldValidation().getDatePicker(from2));
		employeeEducation.add(7, getFieldValidation().getDatePicker(to2));
		employeeEducation.add(8, education3.getValue());
		employeeEducation.add(9, qualification3.getText());
		employeeEducation.add(10, getFieldValidation().getDatePicker(from3));
		employeeEducation.add(11, getFieldValidation().getDatePicker(to3));
		employeeEducation.add(12, employeeId.getText());

		if(getEmployeeImpl().updateEducation(employeeEducation)) {
			setEducation();
			Messages.infoMessage(MessageConstant.updateMessageSuccess, null, MessageConstant.defaultTitle);
		} else {
			Messages.errorMessage(MessageConstant.updateMessageFailure, null, MessageConstant.defaultTitle);
		}
	}
	
	@FXML
	public void updateMemos() throws SQLException {
		ArrayList<String> employeeMemos = new ArrayList<String>();
		
		employeeMemos.add(0, memo1.getText());
		employeeMemos.add(1, memo2.getText());
		employeeMemos.add(2, memo3.getText());
		employeeMemos.add(3, employeeId.getText());

		if(getEmployeeImpl().updateMemos(employeeMemos)){
			setMemo();
			Messages.infoMessage(MessageConstant.updateMessageSuccess, null, MessageConstant.defaultTitle);
		} else {
			Messages.errorMessage(MessageConstant.updateMessageFailure, null, MessageConstant.defaultTitle);
		}
	}
	
	private AddEmployeeImpl getAddEmployeeImpl() {
		return new AddEmployeeImpl();
	}

	private EmployeeImpl getEmployeeImpl() {
		return new EmployeeImpl();
	}

	private FieldValidation getFieldValidation() {
		return new FieldValidation();
	}

	private TableData getTableData() {
		return new TableData();
	}

	private Calenders getCalenders() {
		return new Calenders();
	}

	private ConfigImpl getConfigImpl() {
		return new ConfigImpl();
	}

	private Calculator getCalculator() {
		return new Calculator();
	}

	private void loadComboBox() {
		getFieldValidation().setComboItems(proof, getAddEmployeeImpl().getProofCombo());
		getFieldValidation().setComboItems(maritalStatus, getAddEmployeeImpl().getMartialCombo());
		getFieldValidation().setComboItems(gender, getAddEmployeeImpl().getGenderCombo());
		getFieldValidation().setComboItems(department, getAddEmployeeImpl().getDepartmentCombo());
		getFieldValidation().setComboItems(education1, getAddEmployeeImpl().getEducationCombo());
		getFieldValidation().setComboItems(education2, getAddEmployeeImpl().getEducationCombo());
		getFieldValidation().setComboItems(education3, getAddEmployeeImpl().getEducationCombo());

		education1.setEditable(true);
		education2.setEditable(true);
		education3.setEditable(true);
	}

	private void disableEmployee() {
		if (disableEmployee.isSelected()) {
			ArrayList<Node> arrNodes = getDisableEmployeeFields();
			for (Node node : arrNodes) {
				getFieldValidation().disableFieldList(node, true);
			}
		}
	}

	private void setDemographics() throws SQLException {
		array = getEmployeeImpl().getEmployeeDemographics();

		getFieldValidation().setText(employeeId, array.get(0));
		getFieldValidation().setCheckBox(disableEmployee, array.get(1));
		getFieldValidation().setTextField(givenName, array.get(2));
		getFieldValidation().setTextField(surName, array.get(3));
		getFieldValidation().setTextField(empTitle, array.get(4));
		getFieldValidation().setDatePicker(doj, array.get(5));
		getFieldValidation().setTextArea(address, array.get(6));
		getFieldValidation().setDatePicker(dob, array.get(7));
		getFieldValidation().setComboBox(gender, array.get(8));
		getFieldValidation().setTextField(phone, array.get(9));
		getFieldValidation().setTextField(email, array.get(10));
		getFieldValidation().setComboBox(proof, array.get(11));
		getFieldValidation().setTextField(proofValue, array.get(12));
		getFieldValidation().setComboBox(maritalStatus, array.get(13));
		getFieldValidation().setTextField(noOfChildren, array.get(14));
		getFieldValidation().setComboBox(department, array.get(15));
	}

	private void setEducation() throws SQLException {
		array = getEmployeeImpl().getEmployeeEducation();

		getFieldValidation().setComboBox(education1, array.get(1));
		getFieldValidation().setTextField(qualification1, array.get(2));
		getFieldValidation().setDatePicker(from1, array.get(3));
		getFieldValidation().setDatePicker(to1, array.get(4));
		getFieldValidation().setComboBox(education2, array.get(5));
		getFieldValidation().setTextField(qualification2, array.get(6));
		getFieldValidation().setDatePicker(from2, array.get(7));
		getFieldValidation().setDatePicker(to2, array.get(8));
		getFieldValidation().setComboBox(education3, array.get(9));
		getFieldValidation().setTextField(qualification3, array.get(10));
		getFieldValidation().setDatePicker(from3, array.get(11));
		getFieldValidation().setDatePicker(to3, array.get(12));
	}

	private void setMemo() throws SQLException {
		array = getEmployeeImpl().getEmployeeMemos();

		getFieldValidation().setTextArea(memo1, array.get(1));
		getFieldValidation().setTextArea(memo2, array.get(2));
		getFieldValidation().setTextArea(memo3, array.get(3));
	}

	private void setLeaves() throws SQLException {
		getFieldValidation().setText(currentMonthLeave, getCalenders().getCurrentMonth());
		getFieldValidation().setText(allowedLeavesForMonth, getConfigImpl().getConfig("ALLOWEDPAIDLEAVE"));
		getFieldValidation().setText(paidLeaveMonth, getEmployeeImpl().getPaidLeaveCount().toString());
		getFieldValidation().setText(unpaidLeaveMonth, getEmployeeImpl().getUnpaidLeaveCount().toString());
	}
	
	private void loadLeaveDetails() throws SQLException {
		getTableData().setTableData(leaveTable, getEmployeeImpl().getLeaveSql());
	}

	private void setWages() throws SQLException {
		array = getEmployeeImpl().getEmployeeWages();

		if (!array.isEmpty()) {
			getFieldValidation().setText(currentMonthPayroll, array.get(1));
			getFieldValidation().setText(noOfWorkingDays, array.get(2));
			getFieldValidation().setText(noOfUnPayday, array.get(3));
			getFieldValidation().setText(noOfPayday, array.get(4));
			getFieldValidation().setText(perDayWages, getConfigImpl().getConfig("PERDAY"));
			getFieldValidation().setText(currentMonthSalary,array.get(5));
			getFieldValidation().setText(wagesBy, MessageConstant.wagesBy + array.get(6));
			wagesBy.setVisible(true);
			generatePayroll.setDisable(true);

		} else {
			wagesBy.setVisible(false);
			generatePayroll.setDisable(false);
		}
	}
	
	

	private Boolean isLeaveAllowed(String employeeId) {
		return (Integer.valueOf(allowedLeavesForMonth.getText()) > Integer.valueOf(paidLeaveMonth.getText())) ? true
				: false;
	}

	private ArrayList<Node> getDisableEmployeeFields() {
		ArrayList<Node> node = new ArrayList<Node>();
		
		node.add(educationPane);
		node.add(memosPane);
		node.add(leavePane);
		node.add(payrollPane);
		node.add(givenName);
		node.add(surName);
		node.add(phone);
		node.add(email);
		node.add(noOfChildren);
		node.add(proofValue);
		node.add(empTitle);
		node.add(proof);
		node.add(maritalStatus);
		node.add(gender);
		node.add(department);
		node.add(dob);
		node.add(doj);
		node.add(address);
		
		return node;
	}

}
