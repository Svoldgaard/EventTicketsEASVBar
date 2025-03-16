package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.DLL.Database.EventDAO_DB;

import java.util.List;

public class EventManager {

    private EventDAO_DB eventDAO_DB;

    public EventManager() {
        eventDAO_DB = new EventDAO_DB();
    }

    public List<Event> getAllEvents() throws Exception {
        return eventDAO_DB.getAllEvents();
    }

    public Event createEvent(Event event) throws Exception {
        return eventDAO_DB.createEvent(event);
    }

    public void updateEvent(Event event) throws Exception {
        eventDAO_DB.updateEvent(event);
    }

    public void deleteEvent(Event event) throws Exception {
        eventDAO_DB.deleteEvent(event);
    }

    public Event saveEvent(Event event) throws Exception {
        return event;
    }

}
