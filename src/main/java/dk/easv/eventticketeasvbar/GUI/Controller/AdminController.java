package dk.easv.eventticketeasvbar.GUI.Controller;

import dk.easv.eventticketeasvbar.GUI.util.TicketException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    @FXML
    private Label lblUsername;
    @FXML
    private TextField SearchCoordinators;
    @FXML
    private TextField searchEvent;
    @FXML
    private TableView tblCoordinator;
    @FXML
    private TableView tblEvent;

    @FXML
    private void handleLogoutAdmin(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Screen");
        stage.show();
    }

    @FXML
    private void btnRemoveEvent(ActionEvent actionEvent) {
    }

    @FXML
    private void btnAssignCoordinator(ActionEvent actionEvent) {
    }

    @FXML
    private void btnCreateUser(ActionEvent actionEvent) {
    }

    @FXML
    private void btnEditUser(ActionEvent actionEvent) {
    }

    @FXML
    private void btnRemoveUser(ActionEvent actionEvent) {
    }
}
