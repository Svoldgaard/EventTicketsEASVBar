package dk.easv.eventticketeasvbar.GUI.Controller;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Event;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class EventInfoController {
    @FXML
    public Label lblEventName;
    @FXML
    public Text lblDescription;
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
        String imagePath = event.getImagePath();
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            Image image = new Image(imageFile.toURI().toString());
            imageView.setImage(image);
        } else {
            System.out.println("Image file not found at: " + imagePath);
        }
        lblEventName.setText(event.getEvent());
        lblDescription.setText(event.getDescription());
    }

}
