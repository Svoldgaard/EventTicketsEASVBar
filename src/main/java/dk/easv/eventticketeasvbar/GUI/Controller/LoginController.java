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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
                switch (login.getAccess()) {
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
        loadScene(actionEvent, "/dk.easv/eventticketeasvbar/FXML/Admin.fxml", "Admin Dashboard");
    }

    private void loadCoordinatorWindow(ActionEvent actionEvent) throws IOException {
        loadScene(actionEvent, "/dk.easv/eventticketeasvbar/FXML/EventCoordinator.fxml", "Coordinator Dashboard");
    }

    private void loadScene(ActionEvent actionEvent, String fxmlPath, String title) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
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
