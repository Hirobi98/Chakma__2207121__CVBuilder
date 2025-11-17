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
    @FXML
    private Scene scene;
    @FXML
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
    @FXML
    private TextArea skillTextArea;
    @FXML
    private TextArea educationTextArea;
    @FXML
    private TextArea workExperienceTextArea;


    @FXML
    private VBox educationContainer;
    @FXML
    private VBox workExperienceContainer;

    @FXML
    private Label name;
    @FXML
    private Label contact;
    @FXML
    private Label skilldisplay;
    @FXML

    private Label projectdisplay;






    @FXML
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
    public void generateCvFileAndSwitch(ActionEvent event) throws IOException {


        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String skills = skillsTextArea.getText();
        String projects = projectsTextArea.getText();
        String educationText = educationTextArea.getText();
        String workExperienceText = workExperienceTextArea.getText();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("preview_cv.fxml"));
        Parent root = loader.load();


        HelloController finalSceneController = loader.getController();


        finalSceneController.display_personal_info(fullName, email, phone, address);
        finalSceneController.display_skills(skills);
        finalSceneController.display_work_experience(workExperienceText);
        finalSceneController.display_projects(projects);
        finalSceneController.display_education(educationText);


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CV Builder - Final CV");
        stage.show();
    }


    @FXML
    public void display_personal_info(String name2, String email2, String phone2, String address2) {
        if (name != null) {

            name.setText(name2.trim().isEmpty() ? "CV Placeholder Name" : name2);
            contact.setText(
                    "Email: " + email2 + " | Phone: " + phone2 + " | Address: " + address2
            );
        }
    }
    @FXML
    public void display_skills(String skills) {
        if (skilldisplay != null) {
            skilldisplay.setText(skills);

        }
    }
    @FXML
    public void display_projects(String projects) {
        if (projectdisplay != null) {
            projectdisplay.setText(projects);

        }
    }

    @FXML
    public void display_work_experience(String workExperienceText) {
        if (workExperienceContainer != null) {
            workExperienceContainer .getChildren().clear();


            Label rawTextLabel = new Label(workExperienceText);
            rawTextLabel.setWrapText(true);
            rawTextLabel.setStyle("-fx-padding: 0 0 0 15; -fx-text-fill: #343A40;");

            workExperienceContainer .getChildren().add(rawTextLabel);
        }
    }

    @FXML
    public void display_education(String educationText) {
        if (educationContainer != null) {
            educationContainer.getChildren().clear();

            // Display the raw text input by the user
            Label rawTextLabel = new Label(educationText);
            rawTextLabel.setWrapText(true);
            rawTextLabel.setStyle("-fx-padding: 0 0 0 15; -fx-text-fill: #343A40;");

            educationContainer.getChildren().add(rawTextLabel);
        }
    }


}
