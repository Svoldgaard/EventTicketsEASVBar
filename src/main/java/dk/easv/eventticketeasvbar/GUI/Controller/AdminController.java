package dk.easv.eventticketeasvbar.GUI.Controller;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.GUI.Model.EventCoordinatorModel;
import dk.easv.eventticketeasvbar.GUI.Model.UserModel;
import dk.easv.eventticketeasvbar.GUI.Model.EventModel;
import dk.easv.eventticketeasvbar.GUI.Model.LoginModel;
import dk.easv.eventticketeasvbar.Main;
// Other Imports
// JavaFX Imports
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

// Java import
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminController implements Initializable {

    @FXML
    private Label lblUsername;
    @FXML
    private TextField SearchCoordinators;
    @FXML
    private TextField searchEvent;
    @FXML
    private TableView<User> tblCoordinator;
    @FXML
    private TableColumn<User, String> colPhoto;
    @FXML
    private TableColumn<User,String> colFName;
    @FXML
    private TableColumn<User,String> colLName;
    @FXML
    private TableColumn<User,String> colEmail;
    @FXML
    private TableColumn<User,Integer> colPhoneNumber;
    @FXML
    private TableColumn<User,Integer> colAmountOfEvents;

    @FXML
    private TableView<Event> tblEvent;
    @FXML
    private TableColumn<Event,String> colEvent;
    @FXML
    private TableColumn<Event,String> colLocation;
    @FXML
    private TableColumn<Event,String> colDate;
    @FXML
    private TableColumn<Event,Double> colTime;
    @FXML
    private TableColumn<Event, String> colCoordinator;


    private UserModel adminModel;
    private EventModel eventModel;
    private EventCoordinatorModel eventCoordinatorModel;

    private CreateUserController createUserController;

    private final ImageView imageView = new ImageView();

    public AdminController() throws Exception {
        adminModel = new UserModel();
        eventModel = new EventModel();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminModel = new UserModel();
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        colFName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colAmountOfEvents.setCellValueFactory(new PropertyValueFactory<>("amountOfEvents"));
        colPhoto.setCellFactory(column -> new TableCell<User, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);
                if (empty || imagePath == null || imagePath.isEmpty()) {
                    setGraphic(null);
                } else {
                    try {
                        // Forvent at imagePath er en sti til et billede i resources
                        Image image = new Image(getClass().getResourceAsStream("/" + imagePath), 50, 50, true, true);
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });

        tblCoordinator.setItems(adminModel.getCoordinators());
        try {
            adminModel.loadCoordinators();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        colEvent.setCellValueFactory(new PropertyValueFactory<>("event"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCoordinator.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCoordinatorsAsString()));

        searchEvent.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                eventModel.searchEvent(newValue);
            }catch (Exception e){
                displayError(e);
                e.printStackTrace();
            }
        });

       SearchCoordinators.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                eventCoordinatorModel.searchEventCoordinator(newValue);
            }catch (Exception t){
                displayError(t);
                t.printStackTrace();
            }
        });

        tblCoordinator.setItems(adminModel.getCoordinators());
        tblEvent.setItems(eventModel.getTblEvent());


        setupDragAndDrop();
    }



    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }


    @FXML
    private void btnLogoutAdmin(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Screen");
        stage.show();
    }

    @FXML
    private void btnRemoveEvent(ActionEvent actionEvent) throws Exception {
        Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Event");
            alert.setHeaderText("Are you sure you want to delete this event?");
            alert.setContentText(null);

            ButtonType result = alert.showAndWait().get();

            if (result == ButtonType.OK) {
                eventModel.deleteEvent(selectedEvent);
                tblEvent.getItems().remove(selectedEvent);
            }
        }
    }


    @FXML
    private void btnCreateUser(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/CreateUser.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        createUserController = fxmlLoader.getController();
        // Inject the AdminModel you already have
        createUserController.setAdminModel(this.adminModel);

        // Open the Create User window
        Stage stage = new Stage();
        stage.setTitle("Create User");
        stage.setScene(scene);
        createUserController.setStage(stage); // Set the stage for closing

        stage.showAndWait();
        tblCoordinator.refresh(); // Refresh table after creation
        adminModel.loadCoordinators();
        System.out.println("Coordinator created and table refreshed");
    }

    @FXML
    private void btnEditUser(ActionEvent actionEvent) throws Exception {
        User selectedCoordinator = tblCoordinator.getSelectionModel().getSelectedItem();

        if (selectedCoordinator == null) {
            showAlert("No Selection", "Please select a coordinator to edit.");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/CreateUser.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        createUserController = fxmlLoader.getController();

        // Set coordinator for editing
        createUserController.setCoordinator(selectedCoordinator);

        // Open the Edit User window
        Stage stage = new Stage();
        stage.setTitle("Edit User");
        stage.setScene(scene);
        createUserController.setStage(stage);

        stage.showAndWait();
        tblCoordinator.refresh(); // Refresh table after editing
        adminModel.loadCoordinators();
        System.out.println("Coordinator edited and table refreshed");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void btnRemoveUser(ActionEvent actionEvent) {
        User selectedCoordinator = tblCoordinator.getSelectionModel().getSelectedItem();
        if (selectedCoordinator != null) {
            try {
                adminModel.removeCoordinator(selectedCoordinator);
                tblCoordinator.getItems().remove(selectedCoordinator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void btnCreateLogIn(ActionEvent actionEvent) {
        // Get selected coordinator from TableView
        User selectedCoordinator = tblCoordinator.getSelectionModel().getSelectedItem();

        if (selectedCoordinator == null) {
            System.out.println("No coordinator selected!");
            return;
        }


        // Get first 5 letters of the firstname
        String firstname = selectedCoordinator.getFirstname();
        String cleanName = firstname.replaceAll("\\s", "");
        String username = (cleanName.length() >= 5) ? cleanName.substring(0, 5) : cleanName;
        username = username.toLowerCase();
        String password = username;


        try {
            // Call the LoginModel to create a login
            LoginModel loginModel = new LoginModel();
            loginModel.createLogin(selectedCoordinator);

            System.out.println("Login created for: " + username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setupDragAndDrop() {
        tblCoordinator.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(row.getItem().getFirstname());  // Store user info
                    db.setContent(content);
                    event.consume();
                }
            });
            return row;
        });

        tblEvent.setRowFactory(tv -> {
            TableRow<Event> row = new TableRow<>();
            row.setOnDragOver(event -> {
                if (event.getGestureSource() != row && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    User selectedCoordinator = findUserByName(db.getString());  // Fetch user object
                    Event selectedEvent = row.getItem();

                    if (selectedCoordinator != null && selectedEvent != null) {
                        selectedEvent.addCoordinator(selectedCoordinator);

                        try {
                            eventModel.updateEvent(selectedEvent); // Update DB
                            refreshEvents();  // Refresh event list after update
                        } catch (Exception e) {
                            displayError(e);
                        }

                        success = true;
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            });


            return row;
        });
    }


    private User findUserByName(String firstname) {
        for (User user : adminModel.getCoordinators()) {
            if (user.getFirstname().equals(firstname)) {
                return user;
            }
        }
        return null;
    }

    private void refreshEvents() {
        try {
            eventModel.refreshEvents(); // Reload events from DB
            tblEvent.refresh();
        } catch (Exception e) {
            displayError(e);
        }
    }



    public void setUsername(String username) {
        lblUsername.setText(username);
    }


}
