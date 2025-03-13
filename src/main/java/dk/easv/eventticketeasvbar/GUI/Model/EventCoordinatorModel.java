package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class EventCoordinatorModel {
    private final ObservableList<Event> tblEvent;

    public EventCoordinatorModel() {
        tblEvent = FXCollections.observableArrayList();
        loadMockData();
    }

    private void loadMockData() {
        tblEvent.addAll(
                new Event("Bar Fight", "EASV", LocalDate.of(2024, 5, 20), 20.45, 3.00, 245.00, "Steven Hansen"),
                new Event("Music Night", "Copenhagen Arena", LocalDate.of(2024, 6, 15), 18.30, 4.00, 500.00, "Maria Larsen"),
                new Event("Tech Conference", "Aarhus Tech Hub", LocalDate.of(2024, 9, 10), 10.00, 6.00, 1200.00, "John Doe")
        );
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
