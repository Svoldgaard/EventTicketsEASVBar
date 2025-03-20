package dk.easv.eventticketeasvbar.GUI.Controller;
// Other Imports
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

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
    private static MFXButton btnSaveEvent;
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png"));
        File file = fileChooser.showOpenDialog(new Stage());

        if(file!=null){
            String userEventsDirectory = "src/main/resources/Photos";
            File photoDir =  new File(userEventsDirectory);
            if(!photoDir.exists()){
                photoDir.mkdirs();
            }

            File destinationFile = new File(photoDir, file.getName());
            try{
                Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                AddEditEventController.setText("dk/easv/eventticketeasvbar/BE/Event.java");
            }catch (IOException e){
                e.printStackTrace();
                showAlert("Error", "Failed  to copt the file");
            }
        }
    }

    private void showAlert(String error, String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(String.valueOf(title));
        alert.setHeaderText(null);
        String message = "";
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void btnSaveEvent(ActionEvent actionEvent) {
    }

    static void setText(String saveChanges) {
        if (btnSaveEvent != null) { // Prevents NullPointerException
            btnSaveEvent.setText(saveChanges);
        }
    }
}
