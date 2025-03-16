package dk.easv.eventticketeasvbar.GUI.Model;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
// Java Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class AdminModel {
    private final ObservableList<Event> tblEvent;
    private final ObservableList<EventCoordinator> tblCoordinator;

    public AdminModel(){
        tblCoordinator = FXCollections.observableArrayList();
        tblEvent = FXCollections.observableArrayList();

        loadMockDataCoordinator();
        loadMockDataEvent();
    }

    private void loadMockDataCoordinator(){
        tblCoordinator.addAll(
            new EventCoordinator("Steven","Hansen","StvHa@easv.dk",29292929,1),
            new EventCoordinator("Maria","Larsen", "Maria@easv.dk",88888888,2),
            new EventCoordinator("John","Doe", "Johndoe@easv.dk",29432943,5)
        );
    }

    private void loadMockDataEvent() {
        tblEvent.addAll(
            new Event("Bar Fight", "EASV", LocalDate.of(2024, 5, 20), 20.45, "Steven Hansen"),
            new Event("Music Night", "Copenhagen Arena", LocalDate.of(2024, 6, 15), 18.30, "Maria Larsen"),
            new Event("Tech Conference", "Aarhus Tech Hub", LocalDate.of(2024, 9, 10), 10.00, "John Doe")
        );
    }

    public ObservableList<Event> getEvents() {
        return tblEvent;
    }

    public ObservableList<EventCoordinator> getCoordinators() {
        return tblCoordinator;
    }
}
