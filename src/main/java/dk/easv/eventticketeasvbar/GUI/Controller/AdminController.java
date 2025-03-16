package dk.easv.eventticketeasvbar.GUI.Controller;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.GUI.Model.AdminModel;
import dk.easv.eventticketeasvbar.GUI.Model.EventCoordinatorModel;
import dk.easv.eventticketeasvbar.GUI.Model.LoginModel;
import dk.easv.eventticketeasvbar.Main;
// Other Imports
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class AdminController implements Initializable {

    @FXML
    private Label lblUsername;
    @FXML
    private TextField SearchCoordinators;
    @FXML
    private TextField searchEvent;
    @FXML
    private TableView<EventCoordinator> tblCoordinator;
    @FXML
    private TableColumn<EventCoordinator,String> colFName;
    @FXML
    private TableColumn<EventCoordinator,String> colLName;
    @FXML
    private TableColumn<EventCoordinator,String> colEmail;
    @FXML
    private TableColumn<EventCoordinator,Integer> colPhoneNumber;
    @FXML
    private TableColumn<EventCoordinator,Integer> colAmountOfEvents;

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
    @FXML
    private MFXButton btnLogoutAdmin;
    @FXML
    private MFXButton btnCreateUser;
    @FXML
    private MFXButton btnEditUser;
    @FXML
    private MFXButton btnRemoveUser;
    @FXML
    private MFXButton btnAssignCoordinator;
    @FXML
    private MFXButton btnRemoveEvent;

    private AdminModel adminModel;

    private AssignEditController assignEditController;
    private CreateUserController createUserController;

    public AdminController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminModel = new AdminModel();

        colFName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colAmountOfEvents.setCellValueFactory(new PropertyValueFactory<>("amountOfEvents"));

        colEvent.setCellValueFactory(new PropertyValueFactory<>("event"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCoordinator.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCoordinatorsAsString()));


        tblCoordinator.setItems(adminModel.getCoordinators());
        tblEvent.setItems(adminModel.getEvents());

        setupDragAndDrop();
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
    private void btnRemoveEvent(ActionEvent actionEvent) {
    }

    @FXML
    private void btnAssignCoordinator(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Assign-edit Window.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        assignEditController = fxmlLoader.getController();

        // Open the assign Event stage
        Stage stage = new Stage();
        stage.setTitle("Assign Coordinator");
        stage.setScene(scene);
        //reference to cancel button
        assignEditController = fxmlLoader.getController();
        assignEditController.setStage(stage);

        stage.show();

    }

    @FXML
    private void btnCreateUser(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/CreateUser.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        createUserController = fxmlLoader.getController();

        // Open the assign Event stage
        Stage stage = new Stage();
        stage.setTitle("Create User");
        stage.setScene(scene);
        //reference to cancel button
        createUserController = fxmlLoader.getController();
        createUserController.setStage(stage);

        stage.show();

    }

    @FXML
    private void btnEditUser(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/CreateUser.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        createUserController = fxmlLoader.getController();

        // Open the assign Event stage
        Stage stage = new Stage();
        stage.setTitle("Edit User");
        stage.setScene(scene);
        //changes buttons text
        createUserController.setText("Save changes");
        //reference to cancel button
        createUserController = fxmlLoader.getController();
        createUserController.setStage(stage);

        stage.show();

    }

    @FXML
    private void btnRemoveUser(ActionEvent actionEvent) {
    }

    @FXML
    private void btnCreateLogIn(ActionEvent actionEvent) {
        // Get selected coordinator from TableView
        EventCoordinator selectedCoordinator = tblCoordinator.getSelectionModel().getSelectedItem();

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
        // Enable dragging from Coordinator Table
        tblCoordinator.setRowFactory(tv -> {
            TableRow<EventCoordinator> row = new TableRow<>();
            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(row.getItem().getEmail()); // Use email as unique identifier
                    db.setContent(content);
                    event.consume();
                }
            });
            return row;
        });

        // Allow dropping on the Event Table
        tblEvent.setOnDragOver(event -> {
            if (event.getGestureSource() != tblEvent && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Handle drop event
        tblEvent.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                String coordinatorEmail = db.getString();
                Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();

                if (selectedEvent != null) {
                    EventCoordinator coordinator = findCoordinatorByEmail(coordinatorEmail);
                    if (coordinator != null) {
                        selectedEvent.addCoordinator(coordinator); // Use the method from Event class
                        tblEvent.refresh();
                        tblCoordinator.refresh();
                        success = true;
                    }
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }


    private EventCoordinator findCoordinatorByEmail(String email) {
        for (EventCoordinator ec : tblCoordinator.getItems()) {
            if (ec.getEmail().equals(email)) {
                return ec;
            }
        }
        return null;
    }



}
