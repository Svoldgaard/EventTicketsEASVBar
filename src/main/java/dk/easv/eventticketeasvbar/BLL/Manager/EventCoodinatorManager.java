package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.BLL.Utility.EventCoordinatorSearch;
import dk.easv.eventticketeasvbar.DLL.Database.AdminDAO_DB;


import java.io.IOException;
import java.util.List;

public class EventCoodinatorManager {
    private EventCoordinatorSearch eventCoordinatorSearch = new EventCoordinatorSearch();
    private AdminDAO_DB adminDAO;

    public EventCoodinatorManager() throws IOException {
        eventCoordinatorSearch = new EventCoordinatorSearch();
        adminDAO = new AdminDAO_DB();
    }

    public List<EventCoordinator> getAllEvents() throws Exception{
        return adminDAO.getAllEventCoordinators();
    }

    public List<EventCoordinator> searchEvents(String query) throws Exception{
        List<EventCoordinator> allEvents = getAllEvents();
        List<EventCoordinator> searchResult = eventCoordinatorSearch.search(allEvents, query);
        return searchResult;
    }
}
