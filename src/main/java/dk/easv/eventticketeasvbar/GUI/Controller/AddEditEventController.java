package dk.easv.eventticketeasvbar.GUI.Controller;
// Other Imports
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.GUI.Model.EventModel;
import dk.easv.eventticketeasvbar.GUI.Model.UserModel;
import dk.easv.eventticketeasvbar.Main;
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXCheckTreeView;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private MFXCheckListView<User> pickCoordinator;
    @FXML
    private ImageView eventImg;
    @FXML
    private Label lblDescCount;

    private static final int DESCRIPTION_CHAR_LIMIT = 254;

    private String imagePath;
    private File selectedImage;

    public Stage stage;
    private Event event;
    private EventModel eventModel;
    private UserModel userModel;

    private boolean isEditMode = false;
    private Event eventToEdit;

    public AddEditEventController() throws Exception {
        eventModel = new EventModel();
        userModel = new UserModel();
    }

    @FXML
    public void initialize() throws Exception {
        userModel.loadCoordinators();
        pickCoordinator.setItems(userModel.getCoordinators());
    }

    @FXML
    public void btnCancel(ActionEvent actionEvent) throws IOException {
        if (stage != null) {
            stage.close();
        }

    }

    public void setStage(Stage stage) throws IOException {
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

    //handles text limit
    @FXML
    private void onDescTyped(KeyEvent keyEvent) {
        String text = txtDescription.getText();

        if(text.length() > DESCRIPTION_CHAR_LIMIT) {
            txtDescription.setText(text.substring(0, DESCRIPTION_CHAR_LIMIT));
            txtDescription.positionCaret(DESCRIPTION_CHAR_LIMIT);

        }
        int len = txtDescription.getText().length();
        lblDescCount.setStyle(len >= 200 ? "-fx-text-fill: red;" : "-fx-text-fill: black;");
        lblDescCount.setText(txtDescription.getText().length() + "/" + DESCRIPTION_CHAR_LIMIT);
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
        String timeText = txtTime.getText().trim();
        String durationText = txtDuration.getText().trim();
        String location = txtLocation.getText().trim();
        String priceText = txtPrice.getText().trim();
        String description = txtDescription.getText().trim();

        ObservableMap<Integer, User> selectionMap = pickCoordinator.getSelectionModel().getSelection();
        List<User> selectedCoordinator = new ArrayList<>(selectionMap.values());

        if (eventName.isEmpty() || date == null || timeText.isEmpty() ||
                durationText.isEmpty() || location.isEmpty() || priceText.isEmpty() || description.isEmpty() || selectedCoordinator.isEmpty()) {
            showAlert("Missing Fields", "Please fill in all required fields.");
            return;
        }
        float time, duration, price;

        try{
            time = Float.parseFloat(timeText);
            duration = Float.parseFloat(durationText);
            price = Float.parseFloat(priceText);
        } catch (NumberFormatException e) {
            showAlert("Invalid Number", "Please enter valid numbers for time, duration, and price.");
            return;
        }


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

               eventToEdit.setCoordinators(selectedCoordinator);

               eventModel.updateEvent(eventToEdit);

               eventToEdit.setCoordinators(selectedCoordinator);



            } else {

                Event newEvent = new Event(eventName, location, date, time, duration, price, imagePath, description);
                newEvent.setCoordinators(selectedCoordinator);
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

            // For each coordinator, ensure it's in the items. If not, add it.
            if (event.getCoordinators() != null && !event.getCoordinators().isEmpty()) {
                for (User coord : event.getCoordinators()) {
                    // This check uses equals(...) in User to see if the item is in the combo
                    if (!pickCoordinator.getItems().contains(coord)) {
                        pickCoordinator.getItems().add(coord);
                    }
                    pickCoordinator.getSelectionModel().selectItem(coord);
                }
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

    public void populateComboBox() throws Exception {
        userModel.loadCoordinators();
        pickCoordinator.setItems(userModel.getCoordinators());
    }


}
