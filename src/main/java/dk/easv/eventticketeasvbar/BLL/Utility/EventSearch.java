package dk.easv.eventticketeasvbar.BLL.Utility;

import dk.easv.eventticketeasvbar.BE.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventSearch {
    public List<Event> search(List<Event> searchBase, String query){
    List<Event> searchResult = new ArrayList<>();

    for(Event event : searchBase) {
        if (compareToEventName(query, event)) {
            searchResult.add(event);
        }
        }
    return searchResult;
    }

    private boolean compareToEventName(String query, Event event) {
        return event.getEvent().toLowerCase().contains(query.toLowerCase());
    }


}