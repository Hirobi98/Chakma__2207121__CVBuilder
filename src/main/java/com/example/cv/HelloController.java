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

    private final db a=db.b();




    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;

    @FXML private TextArea skillsTextArea;
    @FXML private TextArea projectsTextArea;

    @FXML private TextArea educationTextArea;
    @FXML private TextArea workExperienceTextArea;

    @FXML private TextField manageFullNameField;
    @FXML private TextField manageEmailField;
    @FXML private VBox educationContainer;
    @FXML private VBox workExperienceContainer;

    @FXML private Label name;
    @FXML private Label contact;
    @FXML private Label skilldisplay;
    @FXML private Label projectdisplay;
    @FXML private TableView<cv_data> cvTable;




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


            Label rawTextLabel = new Label(educationText);
            rawTextLabel.setWrapText(true);
            rawTextLabel.setStyle("-fx-padding: 0 0 0 15; -fx-text-fill: #343A40;");

            educationContainer.getChildren().add(rawTextLabel);
        }
    }
    private cv_data collectAndValidateData() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();

        if (fullName.isEmpty() || email.isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Input Error");
            error.setHeaderText("Mandatory Fields Missing");
            error.setContentText("Full Name and Email Address are mandatory fields.");
            error.showAndWait();
            return null;
        }

        // Collect all data
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        String skills = skillsTextArea.getText().trim();
        String projects = projectsTextArea.getText().trim();
        String educationText = educationTextArea.getText().trim();
        String workExperienceText = workExperienceTextArea.getText().trim();

        // Create the data object
        return new cv_data(
                fullName, email, phone, address, skills, projects, educationText, workExperienceText
        );
    }

    private void save_data(cv_data data) {
        if (data != null) {
            a.e(data); // <--- Use a.e(data) for insert_data
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Save Successful");
            success.setHeaderText(null);
            success.setContentText("CV data saved successfully! Now generating preview.");
            success.showAndWait();
        }

    }

    @FXML
    public void generateCvFileAndSwitch(ActionEvent event) throws IOException {
        // Collect all data
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        String skills = skillsTextArea.getText().trim();
        String projects = projectsTextArea.getText().trim();
        String educationText = educationTextArea.getText().trim();
        String workExperienceText = workExperienceTextArea.getText().trim();

        // Basic validation
        if (fullName.isEmpty() || email.isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Input Error");
            error.setHeaderText("Mandatory Fields Missing");
            error.setContentText("Full Name and Email Address are mandatory fields.");
            error.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm CV Generation");
        alert.setHeaderText("Are you sure you want to save and generate your CV?");
        alert.setContentText("Check your data one last time before previewing. Your data will be SAVED to the database.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() != ButtonType.OK) {
            return;
        }


        cv_data data = new cv_data(fullName, email, phone, address, skills, projects, educationText, workExperienceText);
        a.e(data);

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Save Successful");
        success.setHeaderText(null);
        success.setContentText("CV data saved successfully! Now generating preview.");
        success.showAndWait();

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("preview_cv.fxml"));
        Parent root = loader.load();

        HelloController finalSceneController = loader.getController();


        finalSceneController.display_personal_info(data.getFullname(), data.getEmail(), data.getPhone(), data.getAddress());
        finalSceneController.display_skills(data.getSkills());
        finalSceneController.display_work_experience(data.getWorkexperience());
        finalSceneController.display_projects(data.getProjects());
        finalSceneController.display_education(data.getEducation());

        Scene newScene = new Scene(root, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        newScene.getStylesheets().addAll(primaryStage.getScene().getStylesheets());
        primaryStage.setScene(newScene);
        primaryStage.setTitle("CV Builder - Final CV");
        primaryStage.show();
    }




    @FXML
    public void update_cv_data(ActionEvent event) throws IOException {
        String fullName = manageFullNameField.getText().trim();
        String email = manageEmailField.getText().trim();

        if (fullName.isEmpty() || email.isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Input Error");
            error.setHeaderText("Missing Keys");
            error.setContentText("Please enter the Full Name and Email of the CV entry you want to update.");
            error.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update CV");
        alert.setHeaderText("Data Retrieval for Update");
        alert.setContentText("Functionality to retrieve data for '" + fullName + "' has been triggered.\n\n" +
                "You would typically now load this entry's data into the input screen (Scene 2) for editing, and then call db.update_data() on save.");
        alert.showAndWait();
    }

    @FXML
    public void delete_cv_data(ActionEvent event) {
        String fullName = manageFullNameField.getText().trim();
        String email = manageEmailField.getText().trim();

        if (fullName.isEmpty() || email.isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Input Error");
            error.setHeaderText("Missing Keys");
            error.setContentText("Please enter the Full Name and Email of the CV entry you want to delete.");
            error.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to DELETE the CV data for " + fullName + "?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            a.g(fullName, email); // <--- Use a.g(fullName, email) for delete_data
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Deletion Successful");
            success.setHeaderText(null);
            success.setContentText("CV entry for " + fullName + " has been successfully deleted.");
            success.showAndWait();
            manageFullNameField.clear();
            manageEmailField.clear();

            // Reload the table immediately after deletion
            loadSavedCvList();
        }
    }



    @FXML
    public void loadSavedCvList() {
        if (cvTable != null) {


            a.c();
            cvTable.setItems(a.h());
        }
    }



    @FXML
    public void viewSelectedCv(ActionEvent event) throws IOException {
        cv_data selectedData = cvTable.getSelectionModel().getSelectedItem();

        if (selectedData == null) {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Selection Required");
            error.setHeaderText(null);
            error.setContentText("Please select a CV entry from the table to view it.");
            error.showAndWait();
            return;
        }

        // Switch to the preview scene (preview_cv.fxml)
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("preview_cv.fxml"));
        Parent root = loader.load();
        HelloController finalSceneController = loader.getController();

        // Pass selected data to the preview controller
        finalSceneController.display_personal_info(
                selectedData.getFullname(),
                selectedData.getEmail(),
                selectedData.getPhone(),
                selectedData.getAddress()
        );
        finalSceneController.display_skills(selectedData.getSkills());
        finalSceneController.display_work_experience(selectedData.getWorkexperience());
        finalSceneController.display_projects(selectedData.getProjects());
        finalSceneController.display_education(selectedData.getEducation());

        Scene newScene = new Scene(root, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        newScene.getStylesheets().addAll(primaryStage.getScene().getStylesheets());
        primaryStage.setScene(newScene);
        primaryStage.setTitle("CV Builder - Preview: " + selectedData.getFullname());
        primaryStage.show();
    }

}