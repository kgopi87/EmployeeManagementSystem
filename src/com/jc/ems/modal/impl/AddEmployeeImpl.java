package com.jc.ems.modal.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jc.ems.constant.ComboConstant;
import com.jc.ems.constant.DBConstant;
import com.jc.ems.modal.database.DatabaseConnector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddEmployeeImpl {
	private DatabaseConnector sql = new DatabaseConnector();

	public void addDemographics(ArrayList<String> employeeDemographics) {
		addSql(employeeDemographics, DBConstant.insertDemographics);
	}
	
	public void addEducation(ArrayList<String> employeeEducation) {
		addSql(employeeEducation, DBConstant.insertEducation);
	}
	
	public void addWages(ArrayList<String> employeeWages) {
		addSql(employeeWages, DBConstant.insertWages);
	}
	
	public void addMemos(ArrayList<String> employeeMemos) {
		addSql(employeeMemos, DBConstant.insertMemos);
	}

	public ObservableList<String> getProofCombo() {
		return FXCollections.observableArrayList(ComboConstant.proofComboList);
	}

	public ObservableList<String> getMartialCombo() {
		return FXCollections.observableArrayList(ComboConstant.martialList);
	}

	public ObservableList<String> getGenderCombo() {
		return FXCollections.observableArrayList(ComboConstant.genderList);
	}

	public ObservableList<String> getEducationCombo() {
		return FXCollections.observableArrayList(ComboConstant.educationList);
	}
	
	public ObservableList<String> getDepartmentCombo() {
		return FXCollections.observableArrayList(ComboConstant.departmentList);
	}
	
	private void addSql(ArrayList<String> data, String insertQuery) {
		sql.setStatement(insertQuery);
		sql.prepareQuery();

		try {
			for (int i = 0; i < data.size(); i++) {
				sql.getStatement().setString(i + 1, data.get(i));
			}

			sql.getStatement().execute();

		} catch (SQLException sqlExp) {
			System.err.println("**************************** " + this.getClass().toString() + " SQL exception ****************************");
			sqlExp.printStackTrace();
		}

	}
}
