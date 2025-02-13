package dk.easv.eventticketeasvbar.GUI.Controller;

import dk.easv.eventticketeasvbar.GUI.Model.EventCoordinatorModel;
import dk.easv.eventticketeasvbar.Main;
import dk.easv.eventticketeasvbar.BE.Event;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordinatorController implements Initializable {


    public Label lblUsername;
    public TextField txtSearch;
    private AddEditEventController addEditEventController;

    @FXML
    private TableView<Event> tblEvent;
    @FXML
    private TableColumn<Event, String> eventColumn;
    @FXML
    private TableColumn<Event, String> locationColumn;
    @FXML
    private TableColumn<Event, String> dateColumn;
    @FXML
    private TableColumn<Event, Double> timeColumn;
    @FXML
    private TableColumn<Event, Double> durationColumn;
    @FXML
    private TableColumn<Event, Double> priceColumn;
    @FXML
    private TableColumn<Event, String> coordinatorColumn;
    @FXML
    private MFXButton handleLogoutCoordinator;
    @FXML
    private MFXButton btnCreateEvent;
    @FXML
    private MFXButton btnEditEvent;
    @FXML
    private MFXButton btnDeleteEvent;
    @FXML
    private MFXButton btnAddTicket;
    @FXML
    private MFXButton btnAssign;


    private EventCoordinatorModel EventCoordinatorModel;


    public EventCoordinatorController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventCoordinatorModel = new EventCoordinatorModel();

        // Set up TableView columns
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date")); // LocalDate will be formatted automatically
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        coordinatorColumn.setCellValueFactory(new PropertyValueFactory<>("coordinator"));

        // Bind data to TableView
        tblEvent.setItems(EventCoordinatorModel.getEvents());
    }

    @FXML
    private void handleLogoutCoordinator(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Screen");
        stage.show();
    }

    public void btnCreateEvent(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Add-Edit-Event.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());

        // Open the Add/Edit Event stage
        Stage stage = new Stage();
        stage.setTitle("Create");
        stage.setScene(scene);
        //reference to cancel button
        addEditEventController = fxmlLoader.getController();
        addEditEventController.setStage(stage);
        // Make the new stage modal, blocking interaction with the previous window
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public void btnEditEvent(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Add-Edit-Event.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        addEditEventController = fxmlLoader.getController();

        // Pass the new button name to the controller
        if (addEditEventController != null) {
            addEditEventController.setText("Save Changes");
        }

        // Open the Add/Edit Event stage
        Stage stage = new Stage();
        stage.setTitle("Edit");
        stage.setScene(scene);
        //reference to cancel button
        //addEditEventController = fxmlLoader.getController();
        addEditEventController.setStage(stage);
        // Make the new stage modal, blocking interaction with the previous window
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public void btnDeleteEvent(ActionEvent actionEvent) {
    }

    public void btnAddTicket(ActionEvent actionEvent) {
    }

    public void btnAssign(ActionEvent actionEvent) {

    }

    public void alertMessage() {}


}
