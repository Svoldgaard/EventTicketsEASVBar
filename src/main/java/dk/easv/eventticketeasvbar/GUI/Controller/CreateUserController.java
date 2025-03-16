package dk.easv.eventticketeasvbar.GUI.Controller;
// Other Imports
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateUserController {
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtName;
    @FXML
    private MFXButton btnSave;
    @FXML
    private MFXButton btnCancel;



    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleSave(ActionEvent actionEvent) {
    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) {
        if(stage!=null){
            stage.close();

        }

    }

    public void setText(String text) {
        btnSave.setText(text);
    }

}
