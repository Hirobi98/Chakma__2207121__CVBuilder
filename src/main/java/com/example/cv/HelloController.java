package com.example.cv;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloController {



    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;

    @FXML private TextArea skillsTextArea;
    @FXML private TextArea projectsTextArea;

    @FXML private TextArea educationTextArea;
    @FXML private TextArea workExperienceTextArea;


    @FXML private VBox educationContainer;
    @FXML private VBox workExperienceContainer;

    @FXML private Label name;
    @FXML private Label contact;
    @FXML private Label skilldisplay;
    @FXML private Label projectdisplay;




    @FXML
    public void switch_to_scene1(ActionEvent event) throws IOException {
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root =FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
        Scene newScene = new Scene(root, currentStage.getScene().getWidth(), currentStage.getScene().getHeight());
        newScene.getStylesheets().addAll(currentStage.getScene().getStylesheets());
        currentStage.setScene(newScene);
        currentStage.setTitle("CV Builder - Welcome");
        currentStage.show();
    }

    @FXML
    public void switch_to_scene2(ActionEvent event) throws IOException {
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view2.fxml"));
        Scene newScene = new Scene(root, currentStage.getScene().getWidth(), currentStage.getScene().getHeight());
        newScene.getStylesheets().addAll(currentStage.getScene().getStylesheets());
        currentStage.setScene(newScene);
        currentStage.setTitle("CV Builder - Data Entry");
        currentStage.show();
    }

    @FXML
    public void switch_to_scene3(ActionEvent event) throws IOException {
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view3.fxml"));
        Scene newScene = new Scene(root, currentStage.getScene().getWidth(), currentStage.getScene().getHeight());
        newScene.getStylesheets().addAll(currentStage.getScene().getStylesheets());
        currentStage.setScene(newScene);
        currentStage.setTitle("Manage your entry");
        currentStage.show();
    }


    @FXML
    public void generateCvFileAndSwitch(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm CV Generation");
        alert.setHeaderText("Are you sure you want to generate your CV?");
        alert.setContentText("Check your data one last time before previewing.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() != ButtonType.OK) {
            return;
        }

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

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

        Scene newScene = new Scene(root, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        newScene.getStylesheets().addAll(primaryStage.getScene().getStylesheets());
        primaryStage.setScene(newScene);
        primaryStage.setTitle("CV Builder - Final CV");
        primaryStage.show();
    }



    @FXML
    public void display_personal_info(String name2, String email2, String phone2, String address2) {
        if (name != null) {
            name.setText(name2.toUpperCase().trim().isEmpty() ? "CV PLACEHOLDER NAME" : name2.toUpperCase());
            // Concatenate contact details into a single label below the name
            contact.setText(
                    email2.trim() + " | " + phone2.trim() + " | " + address2.trim()
            );
        }
    }

    @FXML
    public void display_skills(String skills) {
        if (skilldisplay != null) {
            skilldisplay.setText(skills.trim().isEmpty() ? "No skills entered." : skills);
        }
    }

    @FXML
    public void display_projects(String projects) {
        if (projectdisplay != null) {
            projectdisplay.setText(projects.trim().isEmpty() ? "No professional summary entered." : projects);
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
