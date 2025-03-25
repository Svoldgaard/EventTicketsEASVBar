package dk.easv.eventticketeasvbar.GUI.Controller;

// Other Imports
import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.GUI.Model.UserModel;
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CreateUserController {

    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtFirstName;
    @FXML
    private MFXButton saveBtn;
    @FXML
    private MFXButton btnCancel;
    @FXML
    private MFXButton btnAddPicture;

    String photo;

    private UserModel adminModel;
    private User editableCoordinator;
    private boolean isEditMode = false;

    private Stage stage;

    private boolean isUpdateMode;
    private User coordinatorToUpdate;
    @FXML
    private MediaView MediaPictureEmployee;

    public CreateUserController() throws IOException {
        adminModel = new UserModel();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set coordinator for editing mode
     */
    public void setCoordinator(User coordinator) {
        if (coordinator != null) {
            this.editableCoordinator = coordinator;
            this.isEditMode = true;

            // Populate fields with existing coordinator data
            txtFirstName.setText(coordinator.getFirstname());
            txtLastName.setText(coordinator.getLastname());
            txtPhoneNumber.setText(String.valueOf(coordinator.getPhoneNumber()));
            txtEmail.setText(coordinator.getEmail());


        }
    }

    @FXML
    private void btnSave(ActionEvent actionEvent) {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String phoneNumberStr = txtPhoneNumber.getText().trim();
        String email = txtEmail.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumberStr.isEmpty() || email.isEmpty()) {
            showErrorAlert("Field is empty", "All fields must be filled.");
            return;
        }

        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(phoneNumberStr);
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Input", "Phone number must be a number.");
            return;
        }

        if (isEditMode) {
            // Update existing coordinator
            editableCoordinator.setPhoto(photo);
            editableCoordinator.setFirstname(firstName);
            editableCoordinator.setLastname(lastName);
            editableCoordinator.setEmail(email);
            editableCoordinator.setPhoneNumber(phoneNumber);

            try {
                adminModel.updateCoordinator(editableCoordinator);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // Create new coordinator
            User newCoordinator = new User(photo, firstName, lastName, email, phoneNumber, 0);
            try {
                adminModel.addCoordinator(newCoordinator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void btnCancel(ActionEvent actionEvent) {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void btnAddPicture(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png"));
        File file = fileChooser.showOpenDialog(new Stage());

        if(file != null) {
            String userEventsDirectory = "src/main/resources/Photos";
            File photoDir = new File(userEventsDirectory);
            if(!photoDir.exists()) {
                photoDir.mkdir();
            }

            File destinationFile = new File(photoDir, file.getName());
            try {
                Files.copy(file.toPath(),destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                CreateUserController.setText("dk/easv/eventticketeasvbar/BE/User.java");
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }

        }
        
    }

    private static void setText(String saveChanges) {
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setAdminModel(UserModel adminModel) {
        this.adminModel = adminModel;
    }

}
