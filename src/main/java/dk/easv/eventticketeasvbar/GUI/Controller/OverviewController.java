package dk.easv.eventticketeasvbar.GUI.Controller;

import dk.easv.eventticketeasvbar.Main;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    public LoginController loginController;
    private int i;

   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] imagePaths = {
                "Photos/BarFight.png",
                "Photos/Party.jpg",
                "Photos/TechConference.webp"
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
        }

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


    @FXML
    private void handleLogin(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Event Tickets EASV Bar");
        stage.setScene(scene);
        loginController = fxmlLoader.getController();
        loginController.setLoginStage(stage);
        stage.show();



    }
}
