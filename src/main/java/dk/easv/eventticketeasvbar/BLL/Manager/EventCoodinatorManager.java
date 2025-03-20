package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.BLL.Utility.EventCoordinatorSearch;
import dk.easv.eventticketeasvbar.DLL.Database.UserDAO_DB;


import java.io.IOException;
import java.util.List;

public class EventCoodinatorManager {
    private EventCoordinatorSearch eventCoordinatorSearch = new EventCoordinatorSearch();
    private UserDAO_DB adminDAO;

    public EventCoodinatorManager() throws IOException {
        eventCoordinatorSearch = new EventCoordinatorSearch();
        adminDAO = new UserDAO_DB();
    }

    public List<User> getAllEvents() throws Exception{
        return adminDAO.getAllUsers();
    }

    public List<User> searchEvents(String query) throws Exception{
        List<User> allEvents = getAllEvents();
        List<User> searchResult = eventCoordinatorSearch.search(allEvents, query);
        return searchResult;
    }
}
