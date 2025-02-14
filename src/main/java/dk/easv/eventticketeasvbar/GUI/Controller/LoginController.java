package dk.easv.eventticketeasvbar.GUI.Controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

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
    private MFXButton handleLogin;
    @FXML
    private MFXButton handleCancel;

    private Stage loginStage;


    @FXML
    private void handleLogin(ActionEvent actionEvent) throws Exception {


        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();

        if (username.equals("0") && password.equals("0"))  {
            loadAdminWindow(actionEvent);

        }else if (username.equals("1") && password.equals("1")) {
            System.out.println("Coordinator login successful!");
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


    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }
    public void handleCancel(ActionEvent actionEvent) {
        if (loginStage != null){
            loginStage.close();
        }

    }

    @FXML
    private void handleForgotPassword(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/ForgotPassword.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Forgot Password");
        stage.show();
    }


}