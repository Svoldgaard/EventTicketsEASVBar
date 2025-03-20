package dk.easv.eventticketeasvbar.DLL.Database;
// Project Import
import dk.easv.eventticketeasvbar.BE.Event;
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
        ArrayList<Event> events = new ArrayList<>();

        String sql = "SELECT * FROM Events";

        try(Connection conn = dbConnection.getConnection();
            Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                String eventName = rs.getString("EventName");
                String location = rs.getString("Location");
                LocalDate date = rs.getDate("Date").toLocalDate();
                float time = rs.getFloat("Time");
                float duration = rs.getFloat("Duration");
                float price = rs.getFloat("Price");
                String coordinator = rs.getString("Coordinator");
                String picture = rs.getString("Picture");
                String description = rs.getString("Description");
                int parkingInfoID = rs.getInt("ParkingInfoID");
                int ticketID = rs.getInt("TicketID");
                String category = rs.getString("Category");

                Event event = new Event(id, eventName, location, date, time, duration, price, coordinator, picture,
                        description, parkingInfoID, ticketID, category);
                events.add(event);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to retrieve events", ex);
        }
        return events;
    }

    @Override
    public Event createEvent(Event event) throws Exception {
        DBConnection dbConnection = new DBConnection();
        String sql = "INSERT INTO Events (eventName, location, date, time, duration, price, coordinator, picture, " +
                "description, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, event.getEvent());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, java.sql.Date.valueOf(event.getDate()));
            stmt.setFloat(4, event.getTime());
            stmt.setFloat(5, event.getDuration());
            stmt.setFloat(6, event.getPrice());
            stmt.setString(7, event.getCoordinator());
            stmt.setString(8, event.getPicture());
            stmt.setString(9, event.getDescription());
            stmt.setString(10, event.getCategory());

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
        String sql = "UPDATE Events SET eventName = ?, location = ?, date = ?, time = ?, duration = ?, price = ?, " +
                "coordinator = ?, picture = ?, description = ?, category = ?" +
                "WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEvent());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, java.sql.Date.valueOf(event.getDate()));
            stmt.setFloat(4, event.getTime());
            stmt.setFloat(5, event.getDuration());
            stmt.setFloat(6, event.getPrice());
            stmt.setString(7, event.getCoordinator());
            stmt.setString(8, event.getPicture());
            stmt.setString(9, event.getDescription());
            stmt.setString(10, event.getCategory());


            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error updating event in the database.");
        }
        return event;
    }


    @Override
    public void deleteEvent(Event event) throws Exception {
        DBConnection dbConnection = new DBConnection();
        String sql = "DELETE FROM Events WHERE event = ?";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEvent());

            stmt.executeUpdate();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
