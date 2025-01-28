module dk.easv.eventticketeasvbar {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.easv.eventticketeasvbar to javafx.fxml;
    exports dk.easv.eventticketeasvbar;
}