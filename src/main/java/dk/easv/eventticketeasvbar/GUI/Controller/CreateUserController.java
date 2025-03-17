package dk.easv.eventticketeasvbar.GUI.Controller;
// Other Imports
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.GUI.Model.AdminModel;
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private MFXButton btnSave;

    private AdminModel adminModel;


    private Stage stage;

    public CreateUserController() throws IOException {
        adminModel = new AdminModel();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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


        EventCoordinator newCoordinator = new EventCoordinator(firstName, lastName, email, phoneNumber, 0);


        adminModel.getCoordinators().add(newCoordinator);

        if (stage != null) {
            stage.close();
        }
    }


    @FXML
    private void btnCancel(ActionEvent actionEvent) {
        if(stage!=null){
            stage.close();

        }
    }

    public void setText(String text) {
        btnSave.setText(text);
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
