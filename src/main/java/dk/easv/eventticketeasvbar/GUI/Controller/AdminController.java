package dk.easv.eventticketeasvbar.GUI.Controller;


import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.GUI.Model.AdminModel;
import dk.easv.eventticketeasvbar.GUI.Model.EventCoordinatorModel;
import dk.easv.eventticketeasvbar.GUI.Model.LoginModel;
import dk.easv.eventticketeasvbar.Main;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableView<EventCoordinator> tblCoordinator;
    @FXML
    private TableColumn<EventCoordinator,String> name;
    @FXML
    private TableColumn<EventCoordinator,String> email;
    @FXML
    private TableColumn<EventCoordinator,Integer> phoneNumber;
    @FXML
    private TableColumn<EventCoordinator,Integer> amountOffEvents;

    @FXML
    private TableView<Event> tblEvent;
    @FXML
    private TableColumn<Event,String> event;
    @FXML
    private TableColumn<Event,String> locationColumn;
    @FXML
    private TableColumn<Event,String> date;
    @FXML
    private TableColumn<Event,Double> time;
    @FXML
    private TableColumn<Event,String> coordinator;
    @FXML
    private MFXButton handleLogoutAdmin;
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

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        amountOffEvents.setCellValueFactory(new PropertyValueFactory<>("amountOffEvents"));

        event.setCellValueFactory(new PropertyValueFactory<>("event"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        coordinator.setCellValueFactory(new PropertyValueFactory<>("coordinator"));


        tblCoordinator.setItems(adminModel.getCoordinators());
        tblEvent.setItems(adminModel.getEvents());
    }

    @FXML
    private void handleLogoutAdmin(ActionEvent actionEvent) throws Exception {
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

}
