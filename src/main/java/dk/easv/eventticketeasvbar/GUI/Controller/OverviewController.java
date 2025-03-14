package dk.easv.eventticketeasvbar.GUI.Controller;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.Main;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    @FXML
    private TextField txtSearch;
    @FXML
    private ChoiceBox DropMenu;
    @FXML
    private javafx.scene.layout.TilePane TilePane;
    @FXML
    private MFXButton btnLogin;

    @FXML
    private HBox imageContainer;

    public LoginController loginController;
    private int i;

    ImageView img1 = new ImageView(new Image("Photos/BarFight.png"));
    ImageView img2 = new ImageView(new Image("Photos/Party.jpg"));
    ImageView img3 = new ImageView(new Image("Photos/TechConference.png"));

    private Stage infoStage;
    private EventInfoController eventInfoController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            openDescription();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //ImageView img4 = new ImageView(new Image("Photos/Metal.png"));

        // Description labels initially empty (or you can set them as not visible)
        Label desc1 = new Label("");
        Label desc2 = new Label("");
        Label desc3 = new Label("");

        // Set wrapping and maximum width for each description label
        desc1.setWrapText(true);
        desc2.setWrapText(true);
        desc3.setWrapText(true);

        // Optionally adjust max width to match image width or desired layout
        desc1.setPrefWidth(250);
        desc2.setPrefWidth(250);
        desc3.setPrefWidth(250);


        /*img1.setOnMouseClicked(event -> desc1.setText("Bar Fight it is an incredible event with gifts and some real men fights"));
        img2.setOnMouseClicked(event -> desc2.setText("It is really cool event"));
        img3.setOnMouseClicked(event -> desc3.setText("Just a Tech Conference, nothing much"));*/


        img1.setFitHeight(200);
        img1.setFitWidth(150);
        img2.setFitHeight(200);
        img2.setFitWidth(150);
        img3.setFitHeight(200);
        img3.setFitWidth(150);
        //img4.setFitHeight(200);
        //img4.setFitWidth(150);

        Label lbl1 = new Label("Bar Fight");
        Label lbl2 = new Label("Music Night");
        Label lbl3 = new Label("Tech Conference");
        Label lbl4 = new Label("Just a Metal concert");

        VBox vbox1 = new VBox(img1, lbl1, desc1);
        VBox vbox2 = new VBox(img2, lbl2, desc2);
        VBox vbox3 = new VBox(img3, lbl3, desc3);
        //VBox vbox4 = new VBox(img4, lbl4);

        vbox1.setAlignment(Pos.TOP_CENTER);
        vbox2.setAlignment(Pos.TOP_CENTER);
        vbox3.setAlignment(Pos.TOP_CENTER);
        //vbox4.setAlignment(Pos.TOP_CENTER);

        vbox1.setSpacing(5);
        vbox2.setSpacing(5);
        vbox3.setSpacing(5);
        //vbox4.setSpacing(5);

        // Add to HBox
        imageContainer.getChildren().addAll(vbox1, vbox2, vbox3);


        // LOGIN DOESN'T WORK
        /*String[] imagePaths = {
                "Photos/BarFight.png",
                "Photos/Party.jpg",
                "Photos/TechConference.png"
        };

        String[] descriptions = {
                "Bar Fight",
                "Music Night",
                "Tech Conference"
        };

        TilePane.setPrefTileHeight(100.0);
        TilePane.setPrefTileWidth(300.0);
        TilePane.setHgap(20); // Space between images
        TilePane.setVgap(20);
        TilePane.setPrefColumns(3); // Number of images per row

        for (int i = 0; i < imagePaths.length; i++) {
            ImageView imageview = new ImageView(new javafx.scene.image.Image(imagePaths[i]));
            imageview.setFitHeight(200);
            imageview.setFitWidth(150);

            Label lblDescription = new Label(descriptions[i]);

            VBox vbox = new VBox(imageview, lblDescription);
            vbox.setAlignment(javafx.geometry.Pos.CENTER); // Centers image and text
            vbox.setSpacing(10); // Adds space between image and label

            TilePane.getChildren().add(vbox);
        }*/


        // SHOWS ONLY ONE PHOTO (WORKS)
        /*Label lblDescription = new Label("Bar Fight");
        ImageView imageview = new ImageView("Photos/BarFight.png");
        imageview.setFitHeight(200);
        imageview.setFitWidth(130);

        VBox vbox = new VBox();
        vbox.getChildren().add(imageview);
        vbox.getChildren().add(lblDescription);
        vbox.setAlignment(javafx.geometry.Pos.CENTER); // Centers image and text
        vbox.setSpacing(10); // Adds space between image and label

        TilePane.setPrefTileHeight(350.0);
        TilePane.setPrefTileWidth(40.0);

        TilePane.getChildren().add(vbox);

        VBox.setVgrow(imageview, Priority.ALWAYS);*/

    }



    private void openDescription() throws IOException {
        img1.setOnMouseClicked(event -> {

            try {
                // Create an EventDetails object for the clicked event
                Event eventDetails = new Event(
                        ((ImageView) event.getSource()).getImage(),
                        "Bar Fight",
                        "Bar Fight is an incredible event with gifts and some real man fights."
                );


                loadInfoWindow(eventDetails);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        img2.setOnMouseClicked(event -> {

            try {
                // Create an EventDetails object for the clicked event
                Event eventDetails = new Event(
                        ((ImageView) event.getSource()).getImage(),
                        "Music Night",
                        "It is really cool event."
                );


                loadInfoWindow(eventDetails);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        img3.setOnMouseClicked(event -> {

            try {
                // Create an EventDetails object for the clicked event
                Event eventDetails = new Event(
                        ((ImageView) event.getSource()).getImage(),
                        "Tech Conference",
                        "Just a Tech Conference, nothing much."
                );


                loadInfoWindow(eventDetails);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });



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

        // Pass the clicked image to the EventInfoController
        eventInfoController.setEventDetails(event);

        // When the window is closed, set infoStage back to null so it can be reopened later
        //infoStage.setOnCloseRequest(event -> infoStage = null);

        infoStage.show();
    }

}