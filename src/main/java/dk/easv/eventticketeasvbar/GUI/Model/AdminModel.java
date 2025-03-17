package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.BLL.Manager.AdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class AdminModel {
    private final ObservableList<Event> tblEvent;
    private final ObservableList<EventCoordinator> tblCoordinator;
    private final AdminManager adminManager;

    public AdminModel() throws IOException {
        adminManager = new AdminManager();
        tblCoordinator = FXCollections.observableArrayList();
        tblEvent = FXCollections.observableArrayList();
    }

    public void loadCoordinators() throws Exception {
        List<EventCoordinator> allCoordinators = adminManager.getAllEventCoordinators();
        tblCoordinator.clear();
        tblCoordinator.addAll(allCoordinators);
    }

    public void addCoordinator(EventCoordinator coordinator) throws Exception {
        EventCoordinator newCoordinator = adminManager.createEventCoordinator(coordinator);
        tblCoordinator.add(newCoordinator);
    }

    public void updateCoordinator(EventCoordinator coordinator) throws Exception {
        EventCoordinator updatedCoordinator = adminManager.updateEventCoordinator(coordinator);
        int index = tblCoordinator.indexOf(coordinator);
        if (index != -1) {
            tblCoordinator.set(index, updatedCoordinator);
        }
    }

    public void removeCoordinator(EventCoordinator coordinator) throws Exception {
        adminManager.deleteEventCoordinator(coordinator);
        tblCoordinator.remove(coordinator);
    }

    public ObservableList<EventCoordinator> getCoordinators() {
        return tblCoordinator;
    }

    public ObservableList<Event> getEvents() {
        return tblEvent;
    }
}
