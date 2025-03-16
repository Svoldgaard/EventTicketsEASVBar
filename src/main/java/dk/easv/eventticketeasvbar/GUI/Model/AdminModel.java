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

        loadMockData();
    }

    private void loadMockData() {
        // Create mock coordinators
        EventCoordinator stevenHansen = new EventCoordinator("Steven", "Hansen", "steven.hansen@example.com", 12345678, 0);
        EventCoordinator mariaLarsen = new EventCoordinator("Maria", "Larsen", "maria.larsen@example.com", 87654321, 0);
        EventCoordinator johnDoe = new EventCoordinator("John", "Doe", "john.doe@example.com", 55555555, 0);

        // Create mock events
        Event event1 = new Event("Bar Fight", "EASV", LocalDate.of(2024, 5, 20), 20.45, 3.00, 245.00);
        event1.addCoordinator(stevenHansen); // Assign coordinator

        Event event2 = new Event("Music Night", "Copenhagen Arena", LocalDate.of(2024, 6, 15), 18.30, 4.00, 500.00);
        event2.addCoordinator(mariaLarsen);

        Event event3 = new Event("Tech Conference", "Aarhus Tech Hub", LocalDate.of(2024, 9, 10), 10.00, 6.00, 1200.00);
        event3.addCoordinator(johnDoe);

        // Add mock data to tables
        tblEvent.addAll(event1, event2, event3);
        tblCoordinator.addAll(stevenHansen, mariaLarsen, johnDoe);
    }

    public ObservableList<Event> getEvents() {
        return tblEvent;
    }

    public ObservableList<EventCoordinator> getCoordinators() {
        return tblCoordinator;
    }
}
