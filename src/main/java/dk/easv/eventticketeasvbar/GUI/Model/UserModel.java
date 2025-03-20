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
        List<User> allCoordinators = adminManager.getAllUsers();
        tblCoordinator.clear();
        tblCoordinator.addAll(allCoordinators);
    }

    public void addCoordinator(User user) throws Exception {
        User newCoordinator = adminManager.createUser(user);
        tblCoordinator.add(newCoordinator);




    }

    public void updateCoordinator(User user) throws Exception {
        User updatedCoordinator = adminManager.updateUser(user);

        int index = tblCoordinator.indexOf(user);
        if (index != -1) {
            tblCoordinator.set(index, updatedCoordinator);
        }

        adminManager.updateUser(user);
    }


    public void removeCoordinator(User user) throws Exception {
        adminManager.deleteUser(user);
        tblCoordinator.remove(user);
    }

    public ObservableList<User> getCoordinators() {
        return tblCoordinator;
    }

    public ObservableList<Event> getEvents() {
        return tblEvent;
    }
}
