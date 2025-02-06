package dk.easv.eventticketeasvbar.GUI.Controller;

import dk.easv.eventticketeasvbar.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EventCoordinatorController {
    public TableView tblEvent;
    public Label lblUsername;
    public TextField txtSearch;
    private AddEditEventController addEditEventController;


    @FXML
    private void handleLogoutCoordinator(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Screen");
        stage.show();
    }

    public void btnCreateEvent(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Add-Edit-Event.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());

        // Open the Add/Edit Event stage
        Stage stage = new Stage();
        stage.setTitle("Create");
        stage.setScene(scene);
        //reference to cancel button
        addEditEventController = fxmlLoader.getController();
        addEditEventController.setStage(stage);
        // Make the new stage modal, blocking interaction with the previous window
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public void btnEditEvent(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Add-Edit-Event.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        addEditEventController = fxmlLoader.getController();

        // Pass the new button name to the controller
        addEditEventController.setText("Save Changes");

        // Open the Add/Edit Event stage
        Stage stage = new Stage();
        stage.setTitle("Edit");
        stage.setScene(scene);
        //reference to cancel button
        addEditEventController = fxmlLoader.getController();
        addEditEventController.setStage(stage);
        // Make the new stage modal, blocking interaction with the previous window
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public void btnDeleteEvent(ActionEvent actionEvent) {
    }

    public void btnAddTicket(ActionEvent actionEvent) {
    }

    public void btnAssign(ActionEvent actionEvent) {

    }

    public void alertMessage() {}
}
