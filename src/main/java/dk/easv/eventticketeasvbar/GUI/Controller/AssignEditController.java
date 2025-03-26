package dk.easv.eventticketeasvbar.GUI.Controller;

// Imports
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.GUI.Model.UserModel;
import dk.easv.eventticketeasvbar.GUI.Model.EventModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignEditController implements Initializable {
    @FXML
    private Label lblEventname;
    @FXML
    private ListView<User> lstCoordinator;
    @FXML
    private MFXButton btnSave;
    @FXML
    private MFXButton btnCancel;

    private UserModel adminModel;
    private EventModel eventModel;
    private Event event; // Store selected event
    private Stage stage;



    public AssignEditController() throws Exception {
        adminModel = new UserModel();
        eventModel = new EventModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminModel.loadCoordinators();
            lstCoordinator.setItems(adminModel.getCoordinators());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnCancel(ActionEvent actionEvent) {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void btnSave(ActionEvent actionEvent) {
        User selectedCoordinator = lstCoordinator.getSelectionModel().getSelectedItem();
        if (selectedCoordinator == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a coordinator.", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if (event != null) {
            event.addCoordinator(selectedCoordinator);
            try {
                eventModel.updateEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (stage != null) {
            stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setEvent(Event event) {
        this.event = event;
        lblEventname.setText(event.getEvent());
    }
}
