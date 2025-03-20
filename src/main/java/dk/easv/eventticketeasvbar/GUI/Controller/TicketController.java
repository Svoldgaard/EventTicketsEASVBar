package dk.easv.eventticketeasvbar.GUI.Controller;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TicketController implements Initializable {

    @FXML
    private Label lblEventCode;
    @FXML
    private Label lblSection;
    @FXML
    private Label lblRow;
    @FXML
    private Label lblSeat;
    @FXML
    private Label lblPrice;
    @FXML
    private ImageView QRCode;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblDate;

    public Stage stage;



    public TicketController() {
        if (stage != null) {
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String imagePath = "file:src/main/resources/Photos/BarFight.png";
        Image image = new Image(imagePath);
        QRCode.setImage(image);
    }

    @FXML
    private void btnPDF(ActionEvent actionEvent) {
    }

    @FXML
    private void btnEmail(ActionEvent actionEvent) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
