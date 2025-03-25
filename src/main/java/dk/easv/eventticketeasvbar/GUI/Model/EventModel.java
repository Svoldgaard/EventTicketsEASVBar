package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BLL.Manager.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class EventModel {

    private EventManager eventManager;
    private ObservableList<Event> tblEvent;

    public EventModel() throws Exception {
        eventManager = new EventManager();
        tblEvent = FXCollections.observableArrayList();

        loadEvents();
    }

    public ObservableList<Event> getTblEvent() {
        return tblEvent;
    }

    public void addEvent(Event event) throws Exception {
        Event newEvent = eventManager.createEvent(event);
        tblEvent.add(newEvent);
    }

    public void createdEvent(Event event) throws Exception {
        eventManager.saveEvent(event);
        tblEvent.remove(event);
    }

    public void deleteEvent(Event event) throws Exception {
        eventManager.deleteEvent(event);
        tblEvent.remove(event);
    }

    public void updateEvent(Event event) throws Exception {
        eventManager.updateEvent(event);

        int index = tblEvent.indexOf(event);
        if (index >= 0) {
            tblEvent.set(index, event);
        }
    }


    public void loadEvents() throws Exception {
        List<Event> events = eventManager.getAllEvents();
        tblEvent.addAll(events);
    }

    public void refreshEvents() throws Exception {
        tblEvent.clear();
        tblEvent.addAll(eventManager.getAllEvents());
    }


    public void searchEvent(String query) throws Exception {
        List<Event> searchResults = eventManager.searchEvent(query);
        tblEvent.clear();
        tblEvent.addAll(searchResults);
    }


    /*public void searchCoordinators(String query) throws Exception {
        List<Event> searchResults = searchCoordinators();
    }*/


}

