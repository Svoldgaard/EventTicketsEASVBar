package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BLL.Manager.EventManager;
import javafx.collections.ObservableList;

public class EventModel {

    private EventManager eventManager;
    private ObservableList<Event> tblEvent;

    public EventModel() throws Exception {
        eventManager = new EventManager();

    }

    public ObservableList<Event> getTblEvent() {
        return tblEvent;
    }

    public Event addEvent(Event event) throws Exception {
        Event createdEvent = eventManager.createEvent(event);
        tblEvent.add(createdEvent);
        return createdEvent;
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

    }


}
