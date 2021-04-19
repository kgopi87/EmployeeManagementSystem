package com.jc.ems.modal.common;

import java.sql.SQLException;
import java.time.LocalDate;

import com.jc.ems.constant.MessageConstant;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FieldValidation {
	
	private Calenders getCalenders() {
		return new Calenders();
	};
	
	public void disableFieldList(Node object, Boolean Disable) {
		if(Disable) {
			object.setDisable(true);
		} else {
			object.setDisable(false);
		}
		
	}

	public boolean textFieldNotNull(TextField textField, String errorMessage) throws SQLException {
		if (!textField.getText().equalsIgnoreCase("")) {
			return true;
		} else {
			Messages.errorMessage(errorMessage, null, MessageConstant.defaultError);
			return false;
		}
	}
	
	public void setText(Text text, String textValue) {
		text.setText(textValue);
	}

	public void setComboBox(ComboBox<String> comboField, String comboValue) {
		comboField.setValue(comboValue);
	}

	public void setCheckBox(CheckBox checkbox, String checkValue) {
		if (checkValue.equalsIgnoreCase("TRUE")) {	
			checkbox.setSelected(true);
		}
	}

	public void setTextField(TextField textField, String textValue) {
		textField.setText(textValue);
	}
	
	public void setComboItems(ComboBox<String> comboField, ObservableList<String> comboValue) {
		comboField.setItems(comboValue);
	}
	
	public void setDatePicker(DatePicker datePicker, String dateValue) {
		if (!dateValue.equalsIgnoreCase("")) {
			datePicker.setValue(getCalenders().getLocalDate(dateValue));
		}
	}
	
	public void setTextArea(TextArea textArea, String textValue) {
		textArea.setText(textValue);
	}
	
	public String getText(Text textField) {
		return textField.getText();
	}

	public String getComboBox(ComboBox<String> comboField) {
		return comboField.getValue();
	}

	public String getCheckBox(CheckBox checkboxField) {
		return String.valueOf(checkboxField.isSelected()).toUpperCase();
	}

	public String getTextField(TextField textField) {
		return textField.getText();
	}
	
	public String getTextArea(TextArea textArea) {
		return textArea.getText();
	}
	
	public String getDatePicker(DatePicker datePicker) {
		LocalDate dateFormat = datePicker.getValue();
		return dateFormat == null ? "" : datePicker.getValue().toString();
	}

}
