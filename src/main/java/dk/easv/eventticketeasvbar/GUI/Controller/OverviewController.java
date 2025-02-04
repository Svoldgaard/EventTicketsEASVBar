package dk.easv.eventticketeasvbar.GUI.Controller;

import dk.easv.eventticketeasvbar.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class OverviewController {
    @FXML
    private TextField txtSearch;
    @FXML
    private ChoiceBox DropMenu;
    @FXML
    private javafx.scene.layout.TilePane TilePane;


    @FXML
    private void handleLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Event Tickets EASV Bar");
        stage.setScene(scene);
        stage.show();
    }
}
