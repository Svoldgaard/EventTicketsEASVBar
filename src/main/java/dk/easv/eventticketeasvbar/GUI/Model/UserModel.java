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
    private final UserManager userManager;

    public UserModel() throws IOException {
        userManager = new UserManager();
        tblCoordinator = FXCollections.observableArrayList();
        tblEvent = FXCollections.observableArrayList();
    }

    public void loadCoordinators() throws Exception {
        List<User> allCoordinators = userManager.getAllUsers();
        tblCoordinator.clear();
        tblCoordinator.addAll(allCoordinators);
    }

    public void addCoordinator(User user) throws Exception {
        User newCoordinator = userManager.createUser(user);
        tblCoordinator.add(newCoordinator);
    }

    public void updateCoordinator(User user) throws Exception {
        User updatedCoordinator = userManager.updateUser(user);

        int index = tblCoordinator.indexOf(user);
        if (index != 0) {
            tblCoordinator.set(index, updatedCoordinator);
        }

        userManager.updateUser(user);
        loadCoordinators();

    }

    public void refreshUsers() throws Exception {
        tblCoordinator.clear();
        tblCoordinator.addAll(userManager.getAllUsers());

    }




    public void removeCoordinator(User user) throws Exception {
        userManager.deleteUser(user);
        tblCoordinator.remove(user);
    }

    public ObservableList<User> getCoordinators() {
        return tblCoordinator;
    }

    public ObservableList<Event> getEvents() {
        return tblEvent;
    }

    public void searchEventCoordinator(String query) throws Exception {
        List<User> searchResults = userManager.searchUsers(query);
        tblCoordinator.clear();
        tblCoordinator.addAll(searchResults);
    }
}
