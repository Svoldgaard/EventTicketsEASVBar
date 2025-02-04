package dk.easv.eventticketeasvbar.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label lblForgotPassword;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblStatus;

    @FXML
    private void handleLogin(ActionEvent actionEvent) throws Exception {

        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();

        if (username.equals("username") && password.equals("password"))  {
            loadAdminWindow(actionEvent);

        }else if (username.equals("username1") && password.equals("password1")) {
            loadCoordinatorWindow(actionEvent);

        } else {
            lblStatus.setText("Wrong username or password");
        }

    }

    private void loadAdminWindow(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/Admin.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin Dashboard");
        stage.show();
    }

    private void loadCoordinatorWindow(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/EventCoordinator.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Coordinator Dashboard");
        stage.show();
    }

    @FXML
    private void btnCancel(ActionEvent actionEvent) {
    }
}