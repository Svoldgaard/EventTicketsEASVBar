package dk.easv.eventticketeasvbar.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;

public class AddEditEventController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtSetLocation;
    @FXML
    private TextField txtPrice;
    @FXML
    private MediaView MediaPictureEvent;
    @FXML
    private TextArea txtAddDescription;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtTime;

    @FXML
    private void btnAddPicture(ActionEvent actionEvent) {
    }

    @FXML
    private void btnSaveEvent(ActionEvent actionEvent) {
    }

    @FXML
    private void btnCancel(ActionEvent actionEvent) {
    }
}
