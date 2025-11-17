package com.example.cv;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField fullNameField;
    @FXML

    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextArea skillsTextArea;
    @FXML
    private TextArea projectsTextArea;

    // Dynamic Containers (from hello-view2.fxml)
    @FXML
    private VBox educationContainer;
    @FXML
    private VBox workExperienceContainer;

    public static class educationentry{
        String degree;
        String institution;
        String year;
    }
    public static class workexperienceentry{
        String company_name;
        String period;
        String job_title;


    }
    @FXML
    public void addnewedu(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("educationentry.fxml"));
            fxmlLoader.setController(this);
            Node eduentry=fxmlLoader.load();
            educationContainer.getChildren().add(eduentry);



        }
        catch(IOException e){
            System.err.println(e.getMessage());
            e.printStackTrace();

        }

    }
    @FXML
    public void addnewworkexperience(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workexperienceentry.fxml"));
            fxmlLoader.setController(this);
            Node workentry=fxmlLoader.load();
            educationContainer.getChildren().add(workentry);



        }
        catch(IOException e){
            System.err.println(e.getMessage());
            e.printStackTrace();

        }

    }
    @FXML
    public void removeEntry(ActionEvent event) {

        Node source = (Node) event.getSource();
        Node entryToRemove = source.getParent();


        if (entryToRemove.getParent() instanceof VBox) {
            ((VBox)entryToRemove.getParent()).getChildren().remove(entryToRemove);
        }
    }

    public void switch_to_scene1(ActionEvent event) throws IOException {
        Parent root =FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }




    @FXML
    public void switch_to_scene2(ActionEvent event) throws IOException {
        /*Parent root =FXMLLoader.load(HelloApplication.class.getResource("hello-view2.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        */
        Parent root = FXMLLoader.load(getClass().getResource("hello-view2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void switch_to_final_cv(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("preview_cv.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
