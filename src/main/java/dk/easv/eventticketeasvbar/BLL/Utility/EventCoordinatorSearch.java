package dk.easv.eventticketeasvbar.BLL.Utility;


import dk.easv.eventticketeasvbar.BE.EventCoordinator;

import java.util.ArrayList;
import java.util.List;

public class EventCoordinatorSearch {
    public List<EventCoordinator> search(List<EventCoordinator> searchBase, String query){
        List<EventCoordinator> searchResult = new ArrayList<>();

        for(EventCoordinator eventCoodinator : searchBase) {
            if (compareToFirstName(query, eventCoodinator) || compareToLastName(query, eventCoodinator)) {
                searchResult.add(eventCoodinator);
            }
        }
        return searchResult;
    }

    private boolean compareToFirstName(String query, EventCoordinator eventCoodinator) {
        return eventCoodinator.getFirstname().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToLastName(String query, EventCoordinator eventCoodinator) {
        return eventCoodinator.getLastname().toLowerCase().contains(query.toLowerCase());
    }
}