package dk.easv.eventticketeasvbar.GUI.Model;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Event;
// Java Imports
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class EventCoordinatorModel {
    private final ObservableList<Event> tblEvent;
    private final ObservableList<EventCoordinator> tblCoordinator;

    public EventCoordinatorModel() {
        tblEvent = FXCollections.observableArrayList();
        tblCoordinator = FXCollections.observableArrayList();
        //loadMockData();
    }

    public ObservableList<Event> getEvents() {
        return tblEvent;
    }

    public void deleteEvent(Event event) {
        tblEvent.remove(event);
    }

    public void refreshEvent() {

    }

}
