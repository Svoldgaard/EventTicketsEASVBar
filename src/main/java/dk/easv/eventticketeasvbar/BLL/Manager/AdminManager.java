package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.DLL.Database.AdminDAO_DB;

import java.io.IOException;
import java.util.List;

public class AdminManager {

    private AdminDAO_DB adminDAO_DB;

    public AdminManager() throws IOException {
        adminDAO_DB = new AdminDAO_DB();
    }

    public List<EventCoordinator> getAllEventCoordinators() throws Exception {
        return adminDAO_DB.getAllEventCoordinators();
    }


}
