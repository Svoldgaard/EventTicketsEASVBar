package dk.easv.eventticketeasvbar.DLL.Database;
// Project Import
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import dk.easv.eventticketeasvbar.DLL.Interface.IEvents;
// Java Import
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDAO_DB  implements IEvents {


    @Override
    public List<Event> getAllEvents() throws Exception {
        DBConnection dbConnection = new DBConnection();
        List<Event> events = new ArrayList<>();

        String sql = "SELECT id, eventName, location, date, time, duration, price, coordinator, image, description FROM Events";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String eventName = rs.getString("eventName");
                String location = rs.getString("location");
                LocalDate date = rs.getDate("date").toLocalDate();
                float time = rs.getFloat("time");
                float duration = rs.getFloat("duration");
                float price = rs.getFloat("price");
                String coordinatorStr = rs.getString("coordinator");
                String image = rs.getString("image");
                String description = rs.getString("description");

                List<User> coordinators = new ArrayList<>();
                if (coordinatorStr != null && !coordinatorStr.isEmpty()) {
                    String[] coordinatorNames = coordinatorStr.split(",\\s*");
                    for (String name : coordinatorNames) {
                        coordinators.add(new User(name));
                    }
                }

                Event event = new Event(id, eventName, location, date, time, duration, price, coordinators, image, description);
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public Event createEvent(Event event) throws Exception {
        DBConnection dbConnection = new DBConnection();
        String sql = "INSERT INTO Events (eventName, location, date, time, duration, price, image, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, event.getEvent());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, java.sql.Date.valueOf(event.getDate()));
            stmt.setFloat(4, event.getTime());
            stmt.setFloat(5, event.getDuration());
            stmt.setFloat(6, event.getPrice());
            stmt.setString(7, event.getImagePath());
            stmt.setString(8, event.getDescription());


            stmt.executeUpdate();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return event;

    }


    @Override
    public Event updateEvent(Event event) throws Exception {
        DBConnection dbConnection = new DBConnection();
        String sql = "UPDATE Events SET eventName = ?, location = ?, date = ?, time = ?, duration = ?, price = ?, coordinator = ?, image=?, description=? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEvent());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, java.sql.Date.valueOf(event.getDate()));
            stmt.setFloat(4, event.getTime());
            stmt.setFloat(5, event.getDuration());
            stmt.setFloat(6, event.getPrice());
            stmt.setString(7, event.getCoordinatorsAsString());  // Save coordinator names as CSV
            stmt.setString(8, event.getImagePath());
            stmt.setString(9, event.getDescription());
            stmt.setInt(10, event.getId());

            stmt.executeUpdate();
        }
        return event;
    }




    @Override
    public void deleteEvent(Event event) throws Exception {
        DBConnection dbConnection = new DBConnection();
        String sql = "DELETE FROM Events WHERE id= ?";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, event.getId());

            stmt.executeUpdate();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
