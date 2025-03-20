package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.BLL.Manager.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class UserModel {
    private final ObservableList<Event> tblEvent;
    private final ObservableList<User> tblCoordinator;
    private final UserManager adminManager;


    public UserModel() throws IOException {
        adminManager = new UserManager();
        tblCoordinator = FXCollections.observableArrayList();
        tblEvent = FXCollections.observableArrayList();
    }

    public void loadCoordinators() throws Exception {
        List<User> allCoordinators = adminManager.getAllEventCoordinators();
        tblCoordinator.clear();
        tblCoordinator.addAll(allCoordinators);
    }

    public void addCoordinator(User coordinator) throws Exception {
        User newCoordinator = adminManager.createEventCoordinator(coordinator);
        tblCoordinator.add(newCoordinator);




    }

    public void updateCoordinator(User coordinator) throws Exception {
        User updatedCoordinator = adminManager.updateEventCoordinator(coordinator);

        int index = tblCoordinator.indexOf(coordinator);
        if (index != -1) {
            tblCoordinator.set(index, updatedCoordinator);
        }

        adminManager.updateEventCoordinator(coordinator);
    }


    public void removeCoordinator(User coordinator) throws Exception {
        adminManager.deleteEventCoordinator(coordinator);
        tblCoordinator.remove(coordinator);
    }

    public ObservableList<User> getCoordinators() {
        return tblCoordinator;
    }

    public ObservableList<Event> getEvents() {
        return tblEvent;
    }
}
