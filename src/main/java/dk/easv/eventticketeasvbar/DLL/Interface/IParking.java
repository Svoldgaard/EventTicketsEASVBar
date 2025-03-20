package dk.easv.eventticketeasvbar.DLL.Interface;

import dk.easv.eventticketeasvbar.BE.Parking;

import java.util.List;

public interface IParking {

    List<Parking> getAllParking() throws Exception;

    Parking createEvent(Parking parking) throws Exception;

    Parking updateEvent(Parking parking) throws Exception;

    void deleteEvent(Parking parking) throws Exception;
}
