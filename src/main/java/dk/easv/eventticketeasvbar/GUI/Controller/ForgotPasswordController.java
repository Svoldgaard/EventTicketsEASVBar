package dk.easv.eventticketeasvbar.GUI.Controller;

import dk.easv.eventticketeasvbar.GUI.Model.LoginModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ForgotPasswordController {
    @FXML
    private MFXTextField lblUsername;
    @FXML
    private MFXPasswordField lblPassword;


    private final LoginModel loginModel;

    public ForgotPasswordController() throws Exception {
        this.loginModel = new LoginModel();
    }


    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void btnSave(ActionEvent actionEvent) {
        String username = lblUsername.getText();
        String newPassword = lblPassword.getText();

        if (username.isEmpty() || newPassword.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please enter both username and new password.");
            return;
        }

        try {
            // Check if user exists
            boolean userExists = loginModel.getAllLogins().stream()
                    .anyMatch(login -> login.getUsername().equals(username));

            if (!userExists) {
                showAlert(AlertType.ERROR, "Error", "Username does not exist.");
                return;
            }

            // Update password
            loginModel.updateLogin(username, newPassword);
            showAlert(AlertType.INFORMATION, "Success", "Password updated successfully.");

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "An error occurred while updating the password.");
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
