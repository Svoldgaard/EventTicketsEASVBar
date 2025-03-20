package dk.easv.eventticketeasvbar.GUI.Controller;

// Project Imports
import dk.easv.eventticketeasvbar.BE.Parking;
import dk.easv.eventticketeasvbar.GUI.Model.EventCoordinatorModel;
import dk.easv.eventticketeasvbar.GUI.Model.EventModel;
import dk.easv.eventticketeasvbar.GUI.Model.ParkingModel;
import dk.easv.eventticketeasvbar.GUI.Model.TicketModel;
import dk.easv.eventticketeasvbar.Main;
import dk.easv.eventticketeasvbar.BE.Event;
// Other Imports
import io.github.palexdev.materialfx.controls.MFXButton;
//Java Imports
import javafx.application.Platform;
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
    private AssignEditController assignEditController;
    private TicketController ticketController;
    private EventInfoController eventInfoController;
    private ParkingController parkingController;

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



    private EventCoordinatorModel EventCoordinatorModel;
    private EventModel eventModel;
    private TicketModel ticketModel;
    private ParkingModel parkingModel;

    public EventCoordinatorController() throws Exception {
        eventModel = new EventModel();
        eventInfoController = new EventInfoController();
        parkingController = new ParkingController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            EventCoordinatorModel = new EventCoordinatorModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Set up TableView columns
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        coordinatorColumn.setCellValueFactory(new PropertyValueFactory<>("coordinators"));

        // Bind data to TableView
        tblEvent.setItems(eventModel.getTblEvent());

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

        Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Add-Edit-Event.fxml"));
        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        addEditEventController = fxmlLoader.getController();

        addEditEventController.setEvent(selectedEvent);
        addEditEventController.setEventModel(eventModel);

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
        stage.showAndWait();
        tblEvent.setItems(eventModel.getTblEvent());

        tblEvent.refresh();

    }

    public void btnDeleteEvent(ActionEvent actionEvent) throws Exception {
        Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Event");
            alert.setHeaderText("Are you sure you want to delete this event?");
            alert.setContentText(null);

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if(result == ButtonType.OK) {
                eventModel.deleteEvent(selectedEvent);
                tblEvent.getItems().remove(selectedEvent);
            }
        }
    }

    private void refreshEvent() {
        Platform.runLater(() -> {
            try{
                EventCoordinatorModel.refreshEvent();
                tblEvent.setItems(EventCoordinatorModel.getEvents());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    public void btnAddTicket(ActionEvent actionEvent) throws IOException {
        Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Ticket.fxml"));
        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());
        ticketController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Edit");
        stage.setScene(scene);
        //reference to cancel button
        //addEditEventController = fxmlLoader.getController();
        ticketController.setStage(stage);
        // Make the new stage modal, blocking interaction with the previous window
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void handleAssign(ActionEvent actionEvent) throws IOException {
        Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an event first.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Assign-edit Window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        assignEditController = fxmlLoader.getController();
        assignEditController.setStage(new Stage());
        assignEditController.setEvent(selectedEvent); // Pass selected event

        Stage stage = new Stage();
        stage.setTitle("Assign Coordinator");
        stage.setScene(scene);
        assignEditController.setStage(stage);
        stage.show();
    }


    public void alertMessage() {}


    public void setUsername(String username) {
        lblUsername.setText(username);
    }

    public void btnMoreEventInfo(ActionEvent actionEvent) throws IOException {
        Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();

        if (selectedEvent == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an event first.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/EventInfo.fxml"));
        Parent root = fxmlLoader.load();
        EventInfoController eventInfoController = fxmlLoader.getController();

        // Pass the selected event details to the EventInfoController
        eventInfoController.setEventDetails(selectedEvent);

        Stage stage = new Stage();
        stage.setTitle("Event Info");
        stage.setScene(new Scene(root, 600, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }


    public void btnParkingInfo(ActionEvent actionEvent) throws IOException {
        Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();

        if (selectedEvent == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an event first.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Parking.fxml"));
        Parent root = fxmlLoader.load();
        ParkingController parkingController = fxmlLoader.getController();

        // Fetch parking info related to the event
        /*Parking parking = parkingModel.getParkingForEvent(selectedEvent.getId());
        if (parking != null) {
            parkingController.setParkingInfo(parking);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No parking info available for this event.", ButtonType.OK);
            alert.showAndWait();
        }*/

        Stage stage = new Stage();
        stage.setTitle("Parking Info");
        stage.setScene(new Scene(root, 600, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
