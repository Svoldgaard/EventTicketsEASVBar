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


    private Stage stage;

    private boolean isUpdateMode;
    private EventCoordinator coordinatorToUpdate;

    public CreateUserController() throws IOException {
        //adminModel = new AdminModel();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void btnSave(ActionEvent actionEvent) throws Exception {

        try{
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String phoneNumberStr = txtPhoneNumber.getText().trim();


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

        // Check if the user is updating
        if (isUpdateMode) {
            // Update existing
            updateEventCoordinator(firstName, lastName, email, phoneNumber);

        } else {

            //Creates
            EventCoordinator newCoordinator = new EventCoordinator(firstName, lastName, email, phoneNumber, 0);


            adminModel.getCoordinators().add(newCoordinator);
            adminModel.addCoordinator(newCoordinator);



        }
            if (stage != null) {
                stage.close();
            }
            
        } catch (Exception e) {
            throw new Exception("Save button issue!!", e);
        }
    }

    public void setMode(boolean isUpdateMode, EventCoordinator coordinatorToUpdate) {
        this.isUpdateMode = isUpdateMode;
        this.coordinatorToUpdate = coordinatorToUpdate;

        if (isUpdateMode) {

            txtFirstName.setText(coordinatorToUpdate.getFirstname());
            txtLastName.setText(coordinatorToUpdate.getLastname());
            txtEmail.setText(coordinatorToUpdate.getEmail());
            txtPhoneNumber.setText(String.valueOf(coordinatorToUpdate.getPhoneNumber()));
        }

    }

    private void updateEventCoordinator(String firstName, String lastName, String email, int phoneNumber) throws Exception {

        try {
            // Update coordinator properties with the new values
            coordinatorToUpdate.setFirstname(firstName);
            coordinatorToUpdate.setLastname(lastName);
            coordinatorToUpdate.setEmail(email);
            coordinatorToUpdate.setPhoneNumber(phoneNumber);
            //using method with param. from model
            adminModel.updateCoordinator(coordinatorToUpdate);
        } catch (Exception e) {
            throw new Exception("Failed to update coordinator in update Event Coordinator method", e);
        }
    }


    public void setAdminModel(AdminModel adminModel) {
        this.adminModel = adminModel;
    }


    @FXML
    private void btnCancel(ActionEvent actionEvent) {
        if(stage!=null){
            stage.close();

        }
    }

    public void setText(String text) {
        saveBtn.setText(text);
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}
