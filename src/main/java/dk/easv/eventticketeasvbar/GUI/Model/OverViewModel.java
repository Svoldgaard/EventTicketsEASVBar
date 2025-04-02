package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BLL.Manager.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class OverViewModel {
           private static ObservableList<Event> eventsToBeViewed;

           private static EventManager eventManager;

           public OverViewModel() throws Exception{
               eventManager = new EventManager();
               eventsToBeViewed = FXCollections.observableArrayList();
               eventsToBeViewed.addAll(eventManager.getAllEvents());
           }
           
           public ObservableList<Event> getEventsToBeViewed() {
               return eventsToBeViewed;
           }
           
           public static void searchEvent(String query) throws Exception {
               List<Event> searchResults = eventManager.searchEvent(query);
               eventsToBeViewed.clear();
               eventsToBeViewed.addAll(searchResults);
           }

    public void refreshEvents() throws Exception {
        eventsToBeViewed.clear();
        eventsToBeViewed.addAll(eventManager.getAllEvents());
    }


}
