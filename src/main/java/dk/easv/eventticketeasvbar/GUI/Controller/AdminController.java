package dk.easv.eventticketeasvbar.GUI.Controller;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.GUI.Model.EventCoordinatorModel;
import dk.easv.eventticketeasvbar.GUI.Model.UserModel;
import dk.easv.eventticketeasvbar.GUI.Model.EventModel;
import dk.easv.eventticketeasvbar.GUI.Model.LoginModel;
import dk.easv.eventticketeasvbar.Main;

// JavaFX Imports
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;
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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
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


    private UserModel userModel;
    private EventModel eventModel;
    private EventCoordinatorModel eventCoordinatorModel;

    private CreateUserController createUserController;

    private ImageView imageView = new ImageView();
    @FXML
    private MFXButton btnCreateUser;


    public AdminController() throws Exception {
        userModel = new UserModel();
        eventModel = new EventModel();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        setButtonIcon(btnCreateUser, "/dk.easv/eventticketeasvbar/Icon/—Pngtree—edit icon_4479680.png");

        try {
            userModel = new UserModel();
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        colPhoto.setCellValueFactory(new PropertyValueFactory<>("photo"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colFName.prefWidthProperty().bind(tblCoordinator.widthProperty().multiply(0.2));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colLName.prefWidthProperty().bind(tblCoordinator.widthProperty().multiply(0.2));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.prefWidthProperty().bind(tblCoordinator.widthProperty().multiply(0.2));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colAmountOfEvents.setCellValueFactory(new PropertyValueFactory<>("amountOfEvents"));

        colPhoto.setCellFactory(column -> new TableCell<User, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String photo, boolean empty) {
                super.updateItem(photo, empty);
                if (empty || photo == null || photo.isEmpty()) {
                    setGraphic(null);
                } else {
                    try {
                        // Forvent at imagePath er en sti til et billede i resources
                        Image image = new Image(getClass().getResourceAsStream("/" + photo), 50, 50, true, true);
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });

        tblCoordinator.setItems(userModel.getCoordinators());
        tblCoordinator.setStyle("-fx-alternative-row-fill-visible: #009FDA");

        try {
            userModel.loadCoordinators();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        colEvent.setCellValueFactory(new PropertyValueFactory<>("event"));
        colEvent.prefWidthProperty().bind(tblEvent.widthProperty().multiply(0.35));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colLocation.prefWidthProperty().bind(tblEvent.widthProperty().multiply(0.3));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDate.prefWidthProperty().bind(tblEvent.widthProperty().multiply(0.2));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTime.prefWidthProperty().bind(tblEvent.widthProperty().multiply(0.1));
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
                userModel.searchEventCoordinator(newValue);
            }catch (Exception t){
                displayError(t);
                t.printStackTrace();
            }
        });

        tblCoordinator.setItems(userModel.getCoordinators());
        tblEvent.setItems(eventModel.getTblEvent());



        setupDragAndDrop();



        ContextMenu contextMenuCoordinator = new ContextMenu();
        ContextMenu contextMenuEvent = new ContextMenu();

        MenuItem editUser = new MenuItem("Edit User");
        editUser.setOnAction(event -> {
            User selectedEvent = tblCoordinator.getSelectionModel().getSelectedItem();
            if(selectedEvent != null) {
                try{
                    editUser();
                } catch (Exception e) {
                    displayError(e);
                }
            }
        });

        MenuItem deleteUser = new MenuItem("Delete User");
        deleteUser.setOnAction(event -> {
            User selectedEvent = tblCoordinator.getSelectionModel().getSelectedItem();
            if(selectedEvent != null) {
                try{
                    removeUser();
                } catch (Exception e) {
                    displayError(e);
                }
            }
        });

        MenuItem createLogin = new MenuItem("Create Login");
        createLogin.setOnAction(event -> {
            User selectedEvent = tblCoordinator.getSelectionModel().getSelectedItem();
            if(selectedEvent != null) {
                try{
                    createLogin();
                } catch (Exception e) {
                    displayError(e);
                }
            }
        });

        MenuItem removeEvent = new MenuItem("Remove Event");
        removeEvent.setOnAction(event -> {
            Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();
            if(selectedEvent != null) {
                try{
                    removeEvent();
                } catch (Exception e) {
                    displayError(e);
                }
            }
        });

        contextMenuCoordinator.getItems().addAll(editUser, deleteUser, createLogin);
        contextMenuEvent.getItems().addAll(removeEvent);

        tblEvent.setContextMenu(contextMenuEvent);
        tblCoordinator.setContextMenu(contextMenuCoordinator);
    }

    private void setButtonIcon(Button button, String iconPath) {
        URL iconUrl = getClass().getResource(iconPath);

        if (iconUrl == null) {
            System.out.println("Error loading icon: " + iconPath);
            return;
        }

        Image icon = new Image(iconUrl.toExternalForm());
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        button.setGraphic(imageView);
    }


    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }


    @FXML
    private void btnLogoutAdmin(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/Login/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Screen");
        stage.show();
    }

    private void removeEvent() throws Exception {
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Admin/CreateUser.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        createUserController = fxmlLoader.getController();
        // Inject the AdminModel you already have
        createUserController.setAdminModel(this.userModel);

        // Open the Create User window
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/dk.easv/eventticketeasvbar/Icon/Skærmbillede 2025-03-27 142743.png"));
        stage.setTitle("Create User");
        stage.setScene(scene);
        createUserController.setStage(stage); // Set the stage for closing

        stage.showAndWait();
        tblCoordinator.refresh(); // Refresh table after creation
        userModel.loadCoordinators();
        System.out.println("Coordinator created and table refreshed");
    }

    private void editUser() throws Exception {
        User selectedCoordinator = tblCoordinator.getSelectionModel().getSelectedItem();

        if (selectedCoordinator == null) {
            showAlert("No Selection", "Please select a coordinator to edit.");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Admin/CreateUser.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        createUserController = fxmlLoader.getController();

        // Set coordinator for editing
        createUserController.setCoordinator(selectedCoordinator);

        // Open the Edit User window
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/dk.easv/eventticketeasvbar/Icon/Skærmbillede 2025-03-27 142743.png"));
        stage.setTitle("Edit User");
        stage.setScene(scene);
        createUserController.setStage(stage);

        stage.showAndWait();
        tblCoordinator.refresh(); // Refresh table after editing
        userModel.loadCoordinators();
        System.out.println("Coordinator edited and table refreshed");
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
        stage.getIcons().add(new Image("/dk.easv/eventticketeasvbar/Icon/Skærmbillede 2025-03-27 142743.png"));
        stage.setTitle("Edit User");
        stage.setScene(scene);
        createUserController.setStage(stage);

        stage.showAndWait();
        tblCoordinator.refresh(); // Refresh table after editing
        userModel.loadCoordinators();
        System.out.println("Coordinator edited and table refreshed");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void removeUser(){
        User selectedCoordinator = tblCoordinator.getSelectionModel().getSelectedItem();
        if (selectedCoordinator != null) {
            try {
                userModel.removeCoordinator(selectedCoordinator);
                tblCoordinator.getItems().remove(selectedCoordinator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void createLogin(){
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

                    // Store the user's first name as the drag content
                    content.putString(row.getItem().getFirstname());
                    db.setContent(content);

                    // Get the image path from the user's photo property
                    String photoPath = row.getItem().getPhoto();
                    if (photoPath != null && !photoPath.isEmpty()) {
                        try {
                            // Load the image for drag view
                            Image image = new Image(getClass().getResourceAsStream("/" + photoPath), 200, 200, true, true);
                            ImageView imageView = new ImageView(image);

                            imageView.setFitWidth(50);
                            imageView.setFitHeight(50);

                            db.setDragView(imageView.snapshot(null, null));
                        } catch (Exception e) {
                            System.err.println("Error loading image for drag view: " + e.getMessage());
                        }
                    }

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
                    row.setStyle("-fx-background-color: #009FDA");
                }
                event.consume();
            });

            row.setOnDragExited(event -> {
                row.setStyle(""); // Reset to default style
                event.consume();
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {
                    User selectedCoordinator = findUserByName(db.getString());
                    Event selectedEvent = row.getItem();

                        // Ensure the coordinator isn't already assigned
                        if (!selectedEvent.getCoordinators().contains(selectedCoordinator)) {
                            selectedEvent.addCoordinator(selectedCoordinator);

                            try {
                                // Update the database for event assignment
                                eventModel.updateEvent(selectedEvent);
                                // Increment amountOfEvents only if not already counted
                                int previousCount = selectedCoordinator.getAmountOfEvents();
                                selectedCoordinator.setAmountOfEvents(previousCount);
                                userModel.updateCoordinator(selectedCoordinator);
                                refreshEvents(); // Refresh UI
                            } catch (Exception e) {
                                displayError(e);
                            }
                            success = true;
                        }
                }

                event.setDropCompleted(success);
                row.setStyle("");
                event.consume();
            });


            return row;
        });
    }


    private User findUserByName(String firstname) {
        for (User user : userModel.getCoordinators()) {
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


    private ImageView getImageView() {
        return imageView;
    }

    private void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
