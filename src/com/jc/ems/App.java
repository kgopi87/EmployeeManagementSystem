package com.jc.ems;
	
import java.io.IOException;

import com.jc.ems.constant.CommonConstant;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"));
        scene.setFill(Color.BLACK);
        
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle(CommonConstant.title);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initStyle(StageStyle.UNIFIED);
        stage.getIcons().add(new Image(CommonConstant.logo));
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }   
}
