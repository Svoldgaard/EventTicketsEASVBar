package dk.easv.eventticketeasvbar.GUI.Controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class AssignEditController {
    @FXML
    private Label lblEventname;
    @FXML
    private ListView lstCoordinator;
    @FXML
    private MFXButton btnSave;
    @FXML
    private MFXButton btnCancel;

    private Stage stage;

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
    private void btnSave(ActionEvent actionEvent) {
    }


}
