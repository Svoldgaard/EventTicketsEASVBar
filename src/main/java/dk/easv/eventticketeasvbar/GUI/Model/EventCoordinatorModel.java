package dk.easv.eventticketeasvbar.GUI.Model;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Event;
// Java Imports
import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.BLL.Manager.EventCoodinatorManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class EventCoordinatorModel {
    private ObservableList<Event> tblEvent;
    private ObservableList<EventCoordinatorModel> tblCoordinator;

    private EventCoodinatorManager eventCoodinatorManager;

    public EventCoordinatorModel() throws IOException {
        tblEvent = FXCollections.observableArrayList();
        tblCoordinator = FXCollections.observableArrayList();
        //loadMockData();
        eventCoodinatorManager = new EventCoodinatorManager();
    }

   /* private void loadMockData() {
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
    }*/


    public ObservableList<Event> getEvents() {
        return tblEvent;
    }

    public ObservableList<EventCoordinatorModel> getCoordinators() {
        return tblCoordinator;
    }

    public void deleteEvent(Event event) {
        tblEvent.remove(event);
    }

    public void refreshEvent() {

    }

    public void searchEventCoordinator(String query) throws Exception {
        List<User> searchResults = eventCoodinatorManager.searchEvents(query);
        tblCoordinator.clear();
        tblCoordinator.addAll((EventCoordinatorModel) searchResults);
    }

}
