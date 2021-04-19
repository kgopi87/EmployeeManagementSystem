package com.jc.ems.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jc.ems.App;
import com.jc.ems.modal.common.Calenders;
import com.jc.ems.modal.common.TableData;
import com.jc.ems.modal.impl.ReportsImpl;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Reports {

	@FXML
	public void navigateAfterLogin() throws IOException {
		App.setRoot("AfterLogin");
	}

	@FXML
	private TableView<String> currentMonthPayrollReport, currentMonthPaidLeave, currentMonthUnpaidLeave,
			totalActiveEmployee, totalInactiveEmployee;

	@FXML
	public void initialize() throws SQLException {
		loadCurrentMonthPayroll();
		loadCurrentMonthPaidLeave();
		loadCurrentMonthUnPaidLeave();
		loadActiveEmployee();
		loadInActiveEmployee();
	}

	@FXML
	private void loadCurrentMonthPayroll() throws SQLException {
		getTableData().setTableData(currentMonthPayrollReport,
				getReportsImpl().currentMonthPayrollSql(getCalenders().getCurrentMonth()));
	}

	@FXML
	private void loadCurrentMonthPaidLeave() throws SQLException {
		getTableData().setTableData(currentMonthPaidLeave,
				getReportsImpl().currentMonthLeaveSql(getCalenders().getCurrentMonth(), "FALSE"));
	}

	@FXML
	private void loadCurrentMonthUnPaidLeave() throws SQLException {
		getTableData().setTableData(currentMonthUnpaidLeave,
				getReportsImpl().currentMonthLeaveSql(getCalenders().getCurrentMonth(), "TRUE"));
	}

	@FXML
	private void loadActiveEmployee() throws SQLException {
		getTableData().setTableData(totalActiveEmployee, getReportsImpl().employeeActiveStatusSql("FALSE"));
	}

	@FXML
	private void loadInActiveEmployee() throws SQLException {
		getTableData().setTableData(totalInactiveEmployee, getReportsImpl().employeeActiveStatusSql("TRUE"));
	}

	private TableData getTableData() {
		return new TableData();

	}

	private ReportsImpl getReportsImpl() {
		return new ReportsImpl();
	}

	private Calenders getCalenders() {
		return new Calenders();
	}

}
