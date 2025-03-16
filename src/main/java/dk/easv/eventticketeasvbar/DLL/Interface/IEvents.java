package dk.easv.eventticketeasvbar.DLL.Interface;
// Project Import
import dk.easv.eventticketeasvbar.BE.Event;
// Java Import
import java.util.List;

public interface IEvents {

    List<Event> getAllEvents() throws Exception;

    Event createEvent(Event event) throws Exception;

    Event updateEvent(Event event) throws Exception;

    void deleteEvent(Event event) throws Exception;

}
