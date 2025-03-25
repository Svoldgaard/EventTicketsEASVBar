package dk.easv.eventticketeasvbar.GUI.Controller;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Login;
import dk.easv.eventticketeasvbar.GUI.Model.LoginModel;
import dk.easv.eventticketeasvbar.Main;
// Other Imports
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class LoginController {





    @FXML
    private MFXTextField txtUsername;
    @FXML
    private MFXPasswordField txtPassword;
    @FXML
    private Label lblStatus;

    private final LoginModel loginModel;

    public LoginController() throws IOException {
        this.loginModel = new LoginModel();
    }

    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            Login login = loginModel.login(username, password);

            if (login != null) {
                switch (login.getUserType()) {
                    case "Admin":
                        loadAdminWindow(actionEvent);
                        break;
                    case "Event":
                        loadCoordinatorWindow(actionEvent);
                        break;
                    default:
                        lblStatus.setText("Unknown access level");
                }
            } else {
                lblStatus.setText("Wrong username or password");
            }
        } catch (Exception e) {
            lblStatus.setText("Login failed. Please try again.");
            e.printStackTrace();
        }
    }

    private void loadAdminWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/Admin.fxml"));
        Parent root = loader.load();


        AdminController adminController = loader.getController();
        adminController.setUsername(txtUsername.getText());


        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");


        centerStage(stage);

        stage.show();
    }

    private void loadCoordinatorWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/EventCoordinator.fxml"));
        Parent root = loader.load();


        EventCoordinatorController eventCoordinatorController = loader.getController();
        eventCoordinatorController.setUsername(txtUsername.getText());


        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Coordinator Dashboard");


        centerStage(stage);

        stage.show();
    }

    private void centerStage(Stage stage) {
        Scene scene = stage.getScene();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);
    }


    @FXML
    private void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleForgotPassword(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/ForgotPassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Forgot Password");
        stage.setScene(scene);
        stage.show();

    }


}
