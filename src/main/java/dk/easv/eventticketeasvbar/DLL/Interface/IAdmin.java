package dk.easv.eventticketeasvbar.DLL.Interface;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;

import java.util.List;

public interface IAdmin {

    List<EventCoordinator> getAllEventCoordinators() throws Exception;

    EventCoordinator createEventCoordinator(EventCoordinator eventCoordinator) throws Exception;

    EventCoordinator updateEventCoordinator(EventCoordinator eventCoordinator) throws Exception;

    void deleteEventCoordinator(EventCoordinator eventCoordinator) throws Exception;


}
