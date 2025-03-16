package dk.easv.eventticketeasvbar.DLL.Database;
// Project Import
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import dk.easv.eventticketeasvbar.DLL.Interface.IEvents;
// Java Import
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAO_DB  implements IEvents {

    private final DBConnection dbConnection;

    public EventDAO_DB (DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Event> getAllEvents() throws Exception {
        ArrayList<Event> events = new ArrayList<>();

        String sql = "SELECT * FROM Event";

        try(Connection conn = dbConnection.getConnection();
            Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String eventName = rs.getString("EventName");
                String location = rs.getString("Location");
                Date date = rs.getDate("Date");
                double time = rs.getFloat("Time");
                double duration = rs.getFloat("Duration");
                double price = rs.getFloat("Price");
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return events;
    }

    @Override
    public Event createEvent(Event event) throws Exception {

        String sql = "INSERT INTO Event (eventName, location, date, time, duration, price) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, event.getEvent());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, java.sql.Date.valueOf(event.getDate()));
            stmt.setDouble(4, event.getTime());
            stmt.setDouble(5, event.getDuration());
            stmt.setDouble(6, event.getPrice());

            stmt.executeUpdate();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return event;

    }

    @Override
    public Event updateEvent(Event event) throws Exception {

        String sql = "UPDATE dbo.Event SET event = ?, location = ?, date = ?, time = ?, duration = ?, price = ? " +
                "WHERE eventName = ?";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getEvent());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, java.sql.Date.valueOf(event.getDate()));
            stmt.setDouble(4, event.getTime());
            stmt.setDouble(5, event.getDuration());
            stmt.setDouble(6, event.getPrice());

            stmt.executeUpdate();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return event;
    }

    @Override
    public void deleteEvent(Event event) throws Exception {

        String sql = "DELETE FROM Event WHERE eventName = ?";

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
