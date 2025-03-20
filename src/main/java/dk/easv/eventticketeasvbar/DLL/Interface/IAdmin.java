package dk.easv.eventticketeasvbar.DLL.Interface;

import dk.easv.eventticketeasvbar.BE.User;

import java.util.List;

public interface IAdmin {

    List<User> getAllEventCoordinators() throws Exception;

    User createEventCoordinator(User eventCoordinator) throws Exception;

    User updateEventCoordinator(User eventCoordinator) throws Exception;

    void deleteEventCoordinator(User eventCoordinator) throws Exception;


}
