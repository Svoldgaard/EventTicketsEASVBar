package dk.easv.eventticketeasvbar.GUI.Controller;
// Other Imports
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.io.IOException;

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
    private MFXButton btnAddPicture;
    @FXML
    private MFXButton btnSaveEvent;
    @FXML
    private MFXButton btnCancel;


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

    void setText(String saveChanges) {
        if (btnSaveEvent != null) { // Prevents NullPointerException
            btnSaveEvent.setText(saveChanges);
        }
    }
}
