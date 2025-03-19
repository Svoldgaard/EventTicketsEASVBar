package dk.easv.eventticketeasvbar.GUI.Controller;

// Other Imports
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.GUI.Model.AdminModel;
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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



    private AdminModel adminModel;
    private EventCoordinator editableCoordinator;
    private boolean isEditMode = false;

    private Stage stage;

    private boolean isUpdateMode;
    private EventCoordinator coordinatorToUpdate;

    public CreateUserController() throws IOException {
        adminModel = new AdminModel();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set coordinator for editing mode
     */
    public void setCoordinator(EventCoordinator coordinator) {
        if (coordinator != null) {
            this.editableCoordinator = coordinator;
            this.isEditMode = true;

            // Populate fields with existing coordinator data
            txtFirstName.setText(coordinator.getFirstname());
            txtLastName.setText(coordinator.getLastname());
            txtPhoneNumber.setText(String.valueOf(coordinator.getPhoneNumber()));
            txtEmail.setText(coordinator.getEmail());

            btnSave.setText("Save Changes"); // Change button text
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
            EventCoordinator newCoordinator = new EventCoordinator(firstName, lastName, email, phoneNumber, 0);
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

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
