package dk.easv.eventticketeasvbar.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

import static java.awt.SystemColor.text;

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
    private Button saveBtn;

    public Stage stage;


    @FXML
    public void btnCancel(ActionEvent actionEvent) throws IOException {
        if(stage!=null){
            stage.close();
        }

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void btnAddPicture(ActionEvent actionEvent) {
    }

    @FXML
    private void btnSaveEvent(ActionEvent actionEvent) {
    }



    public void setText(String text) {
       saveBtn.setText(text);
    }

}
