package dk.easv.eventticketeasvbar.GUI.Controller;


import dk.easv.eventticketeasvbar.BE.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventInfoController {
    @FXML
    private Text desc;
    @FXML
    private Label eventName;
    @FXML
    private Stage infoStage;
    @FXML
    private ImageView imageView;

    public void setInfoStage(Stage infoStage) {
        this.infoStage = infoStage;
    }

    public void handleClose(ActionEvent event) {
        if(infoStage!=null){
            infoStage.close();
        }
    }



    // Set all event details at once
    public void setEventDetails(Event event) {
        imageView.setImage(event.getImage());
        eventName.setText(event.getEvent());
        desc.setText(event.getDescription());
    }

}
