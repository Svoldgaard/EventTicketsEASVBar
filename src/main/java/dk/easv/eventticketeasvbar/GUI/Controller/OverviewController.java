package dk.easv.eventticketeasvbar.GUI.Controller;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.GUI.Model.EventModel;
import dk.easv.eventticketeasvbar.GUI.Model.OverViewModel;
import dk.easv.eventticketeasvbar.Main;
//Other Imports
import io.github.palexdev.materialfx.controls.MFXButton;
// Java Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {

    @FXML
    private ChoiceBox DropMenu;
    @FXML
    private javafx.scene.layout.TilePane TilePane;
    @FXML
    private MFXButton btnLogin;

    @FXML
    private TilePane imageContainer;

    private OverViewModel overviewModel;

    private Stage infoStage;
    private EventInfoController eventInfoController;
    private EventModel eventModel;


    public OverviewController() throws Exception {
       this.overviewModel = new OverViewModel();
       eventModel = new EventModel();
       imageContainer = new TilePane();

    }



    private void displayError(Throwable e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            Main.setOverviewController(this);
            overviewModel = new OverViewModel();
            populateEvents();
        } catch (Exception e) {
            displayError(e);
        }


    }



    private VBox createVBox(Event event) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);

        if(event.getImagePath() != null && !event.getImagePath().isEmpty()) {
            imageView.setImage(new Image( new java.io.File(event.getImagePath()).toURI().toString()));
        } else if (event.getImage() != null) {
            imageView.setImage(event.getImage());

        }

        Label lblTitle = new Label(event.getEvent());


        VBox vBox = new VBox(imageView, lblTitle);
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.TOP_CENTER);

        vBox.setOnMouseClicked(event1 ->{
            try{
                loadInfoWindow(event);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        });
        return vBox;
    }

    public void populateEvents() throws Exception {
        overviewModel.refreshEvents();
        imageContainer.getChildren().clear();

        for(Event event : overviewModel.getEventsToBeViewed()){
            VBox vbox = createVBox(event);
            imageContainer.getChildren().add(vbox);

        }
    }



    @FXML
    private void handleLogin(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Login/LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/dk.easv/eventticketeasvbar/Icon/Skærmbillede 2025-03-27 142743.png"));
        stage.setTitle("Event Tickets EASV Bar");
        stage.setScene(scene);
        /*loginController = fxmlLoader.getController();
        loginController.setLoginStage(stage);*/
        stage.show();

    }


    private void loadInfoWindow(Event event) throws IOException {

        //Check if the window already open
        if(infoStage != null && infoStage.isShowing()) {
            // bring it to the front if it's already open
            infoStage.toFront();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/EventCoordinator/EventInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        infoStage = new Stage();
        infoStage.setTitle("Event Information");
        infoStage.setScene(scene);
        infoStage.getIcons().add(new Image("/dk.easv/eventticketeasvbar/Icon/Skærmbillede 2025-03-27 142743.png"));

        eventInfoController = fxmlLoader.getController();
        eventInfoController.setInfoStage(infoStage);
        eventInfoController.setEventDetails(event);

        infoStage.show();
    }

}