package dk.easv.eventticketeasvbar.GUI.Controller;

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

public class EventCoordinatorController {
    public TableView tblEvent;
    public Label lblUsername;
    public TextField txtSearch;

    @FXML
    private void handleLogoutCoordinator(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Screen");
        stage.show();
    }

    public void btnCreateEvent(ActionEvent actionEvent) {
    }

    public void btnEditEvent(ActionEvent actionEvent) {
    }

    public void btnDeleteEvent(ActionEvent actionEvent) {
    }

    public void btnAddTicket(ActionEvent actionEvent) {
    }

    public void btnAssign(ActionEvent actionEvent) {

    }

    public void alertMessage() {}
}
