package dk.easv.eventticketeasvbar.GUI.Controller;

// Project Imports
import dk.easv.eventticketeasvbar.GUI.Model.EventCoordinatorModel;
import dk.easv.eventticketeasvbar.GUI.Model.EventModel;
import dk.easv.eventticketeasvbar.GUI.Model.ParkingModel;
import dk.easv.eventticketeasvbar.GUI.Model.TicketModel;
import dk.easv.eventticketeasvbar.Main;
import dk.easv.eventticketeasvbar.BE.Event;
// javafx Imports
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Java Imports

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Object.*;

public class EventCoordinatorController implements Initializable {


    public Label lblUsername;
    public TextField txtSearch;

    private Stage eventInfoStage = null;
    private AddEditEventController addEditEventController;
    private AssignEditController assignEditController;
    private TicketController ticketController;
    private EventInfoController eventInfoController;
    private ParkingController parkingController;


    private Event currentPopupEvent = null;
    private boolean isPopupActive = false;


    @FXML
    private TableView<Event> tblEvent;
    @FXML
    private TableColumn<Event, String> eventColumn;
    @FXML
    private TableColumn<Event, String> locationColumn;
    @FXML
    private TableColumn<Event, String> dateColumn;
    @FXML
    private TableColumn<Event, Float> timeColumn;
    @FXML
    private TableColumn<Event, Float> durationColumn;
    @FXML
    private TableColumn<Event, Float> priceColumn;
    @FXML
    private TableColumn<Event, String> coordinatorColumn;


    private EventCoordinatorModel EventCoordinatorModel;
    private EventModel eventModel;
    private TicketModel ticketModel;
    private ParkingModel parkingModel;
    @FXML
    private MFXButton btnEditEvent;
    @FXML
    private MFXButton btnCreate;

    public EventCoordinatorController() throws Exception {
        eventModel = new EventModel();
        eventInfoController = new EventInfoController();
        parkingController = new ParkingController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setButtonIcon(btnEditEvent, "/dk.easv/eventticketeasvbar/Icon/edit.png");
        setButtonIcon1(btnCreate, "/dk.easv/eventticketeasvbar/Icon/create-task-icon.png");

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
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        coordinatorColumn.setCellValueFactory(new PropertyValueFactory<>("coordinators"));

        // Custom cell factory to add currency symbol to the price column
        priceColumn.setCellFactory(column -> new TableCell<Event, Float>() {
            @Override
            protected void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f.-DKK", price)); // Format what is after the price after the %.2f. if you add space after there is space after the number
                }
            }
        });


        // Bind data to TableView
        tblEvent.setItems(eventModel.getTblEvent());


        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                eventModel.searchEvent(newValue);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }
        });


        // Context menu

        ContextMenu contextMenu = new ContextMenu();

        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction((ActionEvent event) -> {
            Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();
            if(selectedEvent != null) {
                try{
                    btnEditEvent(null);
                } catch (IOException e) {
                    displayError(e);
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an event to edit.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction((ActionEvent event) -> {

        });

        MenuItem assignEventCoordinator = new MenuItem("Assign Event Coordinator");
        assignEventCoordinator.setOnAction((ActionEvent event) -> {

        });

        // More info
        Menu moreInfo = new Menu("More info");

        MenuItem eventInfoItem = new MenuItem("Event Info");
        eventInfoItem.setOnAction((ActionEvent event) -> {

        });

        MenuItem parking = new MenuItem("Parking");
        parking.setOnAction((ActionEvent event) -> {

        });


        // menu for tickets

        Menu ticket = new Menu("Ticket");

        MenuItem eventTicket = new MenuItem("Event Ticket");
        eventTicket.setOnAction((ActionEvent event) -> {

        });

        MenuItem discountTicket = new MenuItem("Discount Ticket");
        discountTicket.setOnAction((ActionEvent event) -> {

        });

        MenuItem freeBeerTicket = new MenuItem("Free Beer Ticket");
        freeBeerTicket.setOnAction((ActionEvent event) -> {

        });

        // add submenu item for the extra info

        moreInfo.getItems().addAll(eventInfoItem,parking);

        // add submenu item for the ticket menu
        ticket.getItems().addAll(eventTicket, discountTicket, freeBeerTicket);

        // add the menu option to the context menu
        contextMenu.getItems().addAll(editItem,deleteItem, ticket,moreInfo);

        // attaches the context menu to the tableView
        tblEvent.setContextMenu(contextMenu);
    }


    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void handleLogoutCoordinator(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Screen");
        stage.show();
    }


    public void btnCreateEvent(ActionEvent actionEvent) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Add-Edit-Event.fxml"));

        // Load FXML and get the controller
        Scene scene = new Scene(fxmlLoader.load());

        // Open the Add/Edit Event stage
        Stage stage = new Stage();
        stage.setTitle("Create");
        stage.setScene(scene);
        //reference to cancel button
        addEditEventController = fxmlLoader.getController();
        addEditEventController.setEventModel(eventModel);
        addEditEventController.populateComboBox();
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

            if (result == ButtonType.OK) {
                eventModel.deleteEvent(selectedEvent);
                tblEvent.getItems().remove(selectedEvent);
            }
        }
    }


    public void btnAddTicket(ActionEvent actionEvent) throws IOException {
        Event selectedEvent = tblEvent.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Ticket.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ticketController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Edit");
        stage.setScene(scene);

        ticketController.setEvent(selectedEvent);
        ticketController.setStage(stage);

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

    private void setButtonIcon1(Button button, String iconPath) {
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
}

