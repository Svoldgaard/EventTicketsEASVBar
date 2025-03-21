package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.Parking;
import dk.easv.eventticketeasvbar.BLL.Manager.ParkingManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class ParkingModel {
    private final ObservableList<Parking> parkingList;
    private final ParkingManager parkingManager;

    public ParkingModel() throws IOException {
        parkingManager = new ParkingManager();
        parkingList = FXCollections.observableArrayList();
    }

    public void loadParkingData() throws Exception {
        List<Parking> allParking = parkingManager.getAllParking();
        parkingList.clear();
        parkingList.addAll(allParking);
    }

    public ObservableList<Parking> getParkingList() {
        return parkingList;
    }

    public Parking getParkingForEvent(int eventId) throws Exception {
        for (Parking parking : parkingList) {
            if (parking.getEventId() == eventId) { // Now eventId is valid
                return parking;
            }
        }
        return null;
    }
}
