package dk.easv.eventticketeasvbar.GUI.Controller;
// Other Imports
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.GUI.Model.EventModel;
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class AddEditEventController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField txtPrice;
    @FXML
    private MediaView MediaPictureEvent;
    @FXML
    private TextArea txtDescription;
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
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtAddress;
    @FXML
    private MFXComboBox pickCoordinator;
    @FXML
    private ImageView eventImg;

    private String imagePath;
    private File selectedImage;

    public Stage stage;
    private Event event;
    private EventModel eventModel;

    private boolean isEditMode = false;
    private Event eventToEdit;

    public AddEditEventController() throws Exception {
        eventModel = new EventModel();
    }

    @FXML
    public void btnCancel(ActionEvent actionEvent) throws IOException {
        if (stage != null) {
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

        if (file != null) {
            selectedImage = file;
            eventImg.setImage(new Image(file.toURI().toString()));

        }
    }

    private void showAlert(String error, String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(s);
        alert.setContentText(error);
        alert.showAndWait();

    }

    @FXML
    private void btnSaveEvent(ActionEvent actionEvent) throws Exception {

            String eventName = txtName.getText().trim();
            LocalDate date = txtDate.getValue();
            float time = Float.parseFloat(txtTime.getText().toString());
            float duration = Float.parseFloat(txtDuration.getText().toString());
            String location = txtLocation.getText().trim();
            //String coordinator = pickCoordinator.getText();
            float price = Float.parseFloat(txtPrice.getText().toString());
            String description = txtDescription.getText();


        if (selectedImage != null) {
            String userEventsDirectory = "src/main/resources/Photos";
            File photoDir = new File(userEventsDirectory);
            if (!photoDir.exists()) photoDir.mkdirs();

            File destinationFile = new File(photoDir, eventName + "_" + selectedImage.getName());
            Files.copy(selectedImage.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            imagePath = destinationFile.getPath();
        }



            if(imagePath == null || imagePath.isEmpty()) {
                showAlert("Missing an image", "Please add an image");
                return;
            }

            if(isEditMode) {
               eventToEdit.setEvent(eventName);
               eventToEdit.setLocation(location);
               eventToEdit.setDate(date);
               eventToEdit.setTime(time);
               eventToEdit.setDuration(duration);
               eventToEdit.setPrice(price);
               eventToEdit.setDescription(description);
               eventToEdit.setImagePath(imagePath);

               try {
                   eventModel.updateEvent(eventToEdit);
               }catch (Exception e) {
                   e.printStackTrace();
               }


            } else {

                Event newEvent = new Event(eventName, location, date, time, duration, price, imagePath, description);
                eventModel.addEvent(newEvent);
                eventModel.refreshEvents();
            }

                if (stage != null) {
                    stage.close();
            }

    }

    public void setEvent(Event event) {

        if(event != null) {
            this.event = event;
            this.eventToEdit = event;
            this.isEditMode = true;

            txtName.setText(event.getEvent());
            txtLocation.setText(event.getLocation());
            txtDate.setValue(event.getDate());
            txtTime.setText(String.valueOf(event.getTime()));
            txtDuration.setText(String.valueOf(event.getDuration()));
            txtPrice.setText(String.valueOf(event.getPrice()));
            txtDescription.setText(event.getDescription());

            imagePath = event.getImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                eventImg.setImage(new Image(new File(imagePath).toURI().toString()));
            }
        }
    }

    public void setText(String saveChanges) {

            if (btnSaveEvent != null) { // Prevents NullPointerException
                btnSaveEvent.setText(saveChanges);
            }
    }
    public void setEventModel(EventModel eventModel){
        this.eventModel = eventModel;
    }
}
