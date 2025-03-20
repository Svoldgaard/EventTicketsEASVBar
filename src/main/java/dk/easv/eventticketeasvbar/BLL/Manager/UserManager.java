package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.DLL.Database.UserDAO_DB;

import java.io.IOException;
import java.util.List;

public class UserManager {

    private UserDAO_DB adminDAO_DB;

    public UserManager() throws IOException {
        adminDAO_DB = new UserDAO_DB();
    }

    public List<User> getAllEventCoordinators() throws Exception {
        return adminDAO_DB.getAllEventCoordinators();
    }

    public User createEventCoordinator(User eventCoordinator) throws Exception {
        return adminDAO_DB.createEventCoordinator(eventCoordinator);
    }

    public User updateEventCoordinator(User eventCoordinator) throws Exception {
        return adminDAO_DB.updateEventCoordinator(eventCoordinator);
    }

    public void deleteEventCoordinator(User eventCoordinator) throws Exception {
        adminDAO_DB.deleteEventCoordinator(eventCoordinator);
    }
}
