package com.example.cv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private final db database=new db();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,400);
        String css = this.getClass().getResource("welcome_page.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("CV_BUILDER");
        stage.setScene(scene);

        stage.show();
        database.get_connection();
    }
}
