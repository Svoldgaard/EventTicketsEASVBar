package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.Parking;
import dk.easv.eventticketeasvbar.DLL.Database.ParkingDAO_DB;

import java.io.IOException;
import java.util.List;

public class ParkingManager {

    private ParkingDAO_DB parkingDAO;

    public ParkingManager() throws IOException {
        parkingDAO = new ParkingDAO_DB();
    }

    public List<Parking> getAllParking() throws Exception {
        return parkingDAO.getAllParking();
    }

    public Parking createEvent(Parking parking) throws Exception {
        return parkingDAO.createEvent(parking);
    }

    public void updateEvent(Parking parking) throws Exception {
        parkingDAO.updateEvent(parking);
    }

    public void deleteEvent(Parking parking) throws Exception {
        parkingDAO.deleteEvent(parking);
    }
}
