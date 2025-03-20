package dk.easv.eventticketeasvbar.BLL.Utility;


import dk.easv.eventticketeasvbar.BE.User;

import java.util.ArrayList;
import java.util.List;

public class EventCoordinatorSearch {
    public List<User> search(List<User> searchBase, String query){
        List<User> searchResult = new ArrayList<>();

        for(User eventCoodinator : searchBase) {
            if (compareToFirstName(query, eventCoodinator) || compareToLastName(query, eventCoodinator)) {
                searchResult.add(eventCoodinator);
            }
        }
        return searchResult;
    }

    private boolean compareToFirstName(String query, User eventCoodinator) {
        return eventCoodinator.getFirstname().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToLastName(String query, User eventCoodinator) {
        return eventCoodinator.getLastname().toLowerCase().contains(query.toLowerCase());
    }
}