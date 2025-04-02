package dk.easv.eventticketeasvbar;
// Java Imports
import dk.easv.eventticketeasvbar.GUI.Controller.OverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



public class Main extends Application {

    private static OverviewController overviewController;

    public static void setOverviewController(OverviewController controller) {
        overviewController = controller;
    }

    public static OverviewController getOverviewController() {
        return overviewController;
    }

    @Override
    public void start(Stage stage) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/dk.easv/eventticketeasvbar/FXML/Mainpage/Overview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        OverviewController controller = fxmlLoader.getController();
        setOverviewController(controller); // Set reference

        stage.getIcons().add(new Image("/dk.easv/eventticketeasvbar/Icon/Skærmbillede 2025-03-27 142743.png"));
        stage.setTitle("Event Tickets EASV Bar");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}