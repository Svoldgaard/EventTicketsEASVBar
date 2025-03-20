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

        String sql = "SELECT e.eventName, e.location, e.date, e.time, e.duration, e.price, p.address, p.postalCode, p.city " +
                "FROM Events e JOIN ParkingInfo p ON e.id = p.id";

        try(Connection conn = dbConnection.getConnection();
            Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String eventName = rs.getString("EventName");
                String location = rs.getString("Location");
                String address = rs.getString("Address");
                int postalCode = rs.getInt("PostalCode");
                String city = rs.getString("City");
                LocalDate date = rs.getDate("Date").toLocalDate();
                float time = rs.getFloat("Time");
                float duration = rs.getFloat("Duration");
                float price = rs.getFloat("Price");


                Event event = new Event(eventName, location, address, postalCode, city, date, time, duration, price);
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
        String sql = "INSERT INTO Events (eventName, location, address, postalCode, city, date, time, duration, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, event.getEvent());
            stmt.setString(2, event.getLocation());
            stmt.setString(3, event.getAddress());
            stmt.setInt(4, event.getPostalCode());
            stmt.setString(5, event.getCity());
            stmt.setDate(6, java.sql.Date.valueOf(event.getDate()));
            stmt.setFloat(7, event.getTime());
            stmt.setFloat(8, event.getDuration());
            stmt.setFloat(9, event.getPrice());

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
        String sql = "UPDATE Events SET eventName = ?, location =?, address =?, postalCode =?, city =?, date = ?, time = ?, duration = ?, price = ? " +
                "WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEvent());
            stmt.setString(2, event.getLocation());
            stmt.setString(3, event.getAddress());
            stmt.setInt(4, event.getPostalCode());
            stmt.setString(5, event.getCity());
            stmt.setDate(6, java.sql.Date.valueOf(event.getDate()));
            stmt.setFloat(7, event.getTime());
            stmt.setFloat(8, event.getDuration());
            stmt.setFloat(9, event.getPrice());
            stmt.setString(10, event.getEvent());

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
