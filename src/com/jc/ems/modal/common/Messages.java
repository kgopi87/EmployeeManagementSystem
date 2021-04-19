package com.jc.ems.modal.common;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Messages {
    public static void infoMessage(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    public static void errorMessage(String errorMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(errorMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
