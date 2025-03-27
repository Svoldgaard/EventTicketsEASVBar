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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {

    @FXML
    private ChoiceBox DropMenu;
    @FXML
    private javafx.scene.layout.TilePane TilePane;
    @FXML
    private MFXButton btnLogin;

    @FXML
    private HBox imageContainer;

    private OverViewModel overviewModel;

    private Stage infoStage;
    private EventInfoController eventInfoController;


    public OverviewController() throws Exception {
       overviewModel = new OverViewModel();

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
            overviewModel = new OverViewModel();
            populateEvents();
        } catch (Exception e) {
            displayError(e);
        }


    }

    private VBox createVBox(Event event) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);

        if(event.getImagePath() != null && !event.getImagePath().isEmpty()) {
            imageView.setImage(new Image( new java.io.File(event.getImagePath()).toURI().toString()));
        } else if (event.getImage() != null) {
            imageView.setImage(event.getImage());

        }

        Label lblTitle = new Label(event.getEvent());


        VBox vBox = new VBox(imageView, lblTitle);
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);

        vBox.setOnMouseClicked(event1 ->{
            try{
                loadInfoWindow(event);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        });
        return vBox;
    }

    private void populateEvents() throws Exception {
        imageContainer.getChildren().clear();

        for(Event event : overviewModel.getEventsToBeViewed()){
            VBox vbox = createVBox(event);
            imageContainer.getChildren().add(vbox);
        }
    }





    @FXML
    private void handleLogin(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
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

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/EventInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        infoStage = new Stage();
        infoStage.setTitle("Event Information");
        infoStage.setScene(scene);

        eventInfoController = fxmlLoader.getController();
        eventInfoController.setInfoStage(infoStage);
        eventInfoController.setEventDetails(event);

        infoStage.show();
    }

}