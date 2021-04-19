package com.jc.ems.modal.common;

import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TableData {

	private ObservableList<ObservableList> setObservableList = FXCollections.observableArrayList();

	public void setTableData(TableView tableView, ResultSet resultSet) {
	
		try {

			ResultSet rs = resultSet;

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, ?>, ObservableValue<?>>() {
					public ObservableValue<?> call(CellDataFeatures<ObservableList, ?> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});
				tableView.getColumns().add(col);
			}

			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String rowValue = (rs.getString(i) == null || rs.getString(i) == "") ? "" : rs.getString(i);
					row.add(rowValue);
				}
				this.setObservableList.addAll(row);
			}
			
			tableView.autosize();
			tableView.setItems(setObservableList);

		} catch (Exception e) {
			System.err.println(
					"********************************Error on Building Data*****************************************");
			e.printStackTrace();

		}
	}
	
	public void refreshTableData(TableView tableView, ResultSet resultSet) {

		try {

			ResultSet rs = resultSet;

			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String rowValue = (rs.getString(i) == null || rs.getString(i) == "") ? "" : rs.getString(i);
					row.add(rowValue);
				}
				this.setObservableList.addAll(row);
			}

			tableView.setItems(setObservableList);

		} catch (Exception e) {
			System.err.println(
					"********************************Error on Building Data*****************************************");
			e.printStackTrace();

		}
	}
}
