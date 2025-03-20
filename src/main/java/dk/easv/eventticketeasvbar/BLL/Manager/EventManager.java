package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BLL.Utility.EventSearch;
import dk.easv.eventticketeasvbar.DLL.Database.EventDAO_DB;

import java.io.IOException;
import java.util.List;

public class EventManager {

    private EventDAO_DB eventDAO_DB;
    private EventSearch eventSearch;

    public EventManager() throws IOException {
        eventDAO_DB = new EventDAO_DB();
        eventSearch = new EventSearch();
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

    public List<Event> searchEvent(String query) throws Exception {
        List<Event> allEvents = getAllEvents();
        List<Event> searchResult = eventSearch.search(allEvents, query);
        return searchResult;
    }
}
