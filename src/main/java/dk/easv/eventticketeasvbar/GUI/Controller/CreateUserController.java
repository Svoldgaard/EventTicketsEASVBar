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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Hashtable;

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

    private UserModel adminModel;
    private User editableCoordinator;
    private boolean isEditMode = false;

    private String imagePath;
    private Stage stage;
    private File selectedImage;

    private boolean isUpdateMode;
    private User coordinatorToUpdate;
    @FXML
    private MediaView MediaPictureEmployee;
    @FXML
    private ImageView imageEmployee;

    private String phoneNumber;


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

            imagePath = coordinator.getPhoto();
            if(imagePath != null && !imagePath.isEmpty()){
                imageEmployee.setImage(new Image(new File(imagePath).toURI().toString()));
            }

        }
    }

    @FXML
    private void btnSave(ActionEvent actionEvent) throws IOException {


        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String phoneNumberStr = txtPhoneNumber.getText().trim();
        String email = txtEmail.getText().trim();


        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumberStr.isEmpty() || email.isEmpty()) {
            showErrorAlert("Field is empty", "All fields must be filled.");
            return;
        }

        if (!isValidEmail(email)){
            showErrorAlert("Email address is invalid", "Email address is invalid.");
            return;
        }

        if (!isValidPhoneNumber(phoneNumberStr)){
            showErrorAlert("Phone number is invalid", "Phone number is invalid.");
            return;
        }

        phoneNumber = phoneNumberStr;


        if (selectedImage != null) {
            String usersPhotoDirectory = "src/main/resources/UserPhotos";
            File photoDirectory = new File(usersPhotoDirectory);
            if (!photoDirectory.exists()) photoDirectory.mkdir();

            if(isEditMode && editableCoordinator != null){
                File oldImage = new File(editableCoordinator.getPhoto());
                if (oldImage.exists()) oldImage.delete();
            }

            File destinationFile = new File(photoDirectory,  txtFirstName + "_" + selectedImage.getName());
            Files.copy(selectedImage.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            imagePath = destinationFile.getPath();
        }


        if (isEditMode) {
            // Update existing coordinator
            editableCoordinator.setPhoto(imagePath);
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
            User newCoordinator = new User(firstName, lastName, email, phoneNumber, 0, imagePath);
            try {
                adminModel.addCoordinator(newCoordinator);
                adminModel.refreshUsers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (stage != null) {
            stage.close();
        }



    }

    private boolean isValidPhoneNumber(String phoneNumberStr) {
        String PHONE_PATTERN = "^\\+\\d{1,3}\\d{7,14}$";
        return phoneNumberStr.matches(PHONE_PATTERN);
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
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
            selectedImage = file;
            imageEmployee.setImage(new Image(file.toURI().toString()));

            }
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
