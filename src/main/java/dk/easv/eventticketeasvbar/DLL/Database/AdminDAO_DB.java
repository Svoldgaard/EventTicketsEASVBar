package dk.easv.eventticketeasvbar.DLL.Database;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import dk.easv.eventticketeasvbar.DLL.Interface.IAdmin;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO_DB implements IAdmin {

    private DBConnection dbConnection;

    public AdminDAO_DB() throws IOException {
        this.dbConnection = new DBConnection();
    }

    @Override
    public List<EventCoordinator> getAllEventCoordinators() throws Exception {
        List<EventCoordinator> coordinators = new ArrayList<>();
        String sql = "SELECT * FROM EventCoordinator";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                String email = rs.getString("email");
                int phoneNumber = rs.getInt("phoneNumber");
                int amountOfEvents = rs.getInt("amountOfEvents");

                EventCoordinator coordinator = new EventCoordinator(firstname, lastname, email, phoneNumber, amountOfEvents);
                coordinators.add(coordinator);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error fetching event coordinators from database.");
        }
        return coordinators;
    }

    @Override
    public EventCoordinator createEventCoordinator(EventCoordinator eventCoordinator) throws Exception {
        String sql = "INSERT INTO EventCoordinator (firstName, lastName, email, phoneNumber, amountOfEvents) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, eventCoordinator.getFirstname());
            stmt.setString(2, eventCoordinator.getLastname());
            stmt.setString(3, eventCoordinator.getEmail());
            stmt.setInt(4, eventCoordinator.getPhoneNumber());
            stmt.setInt(5, eventCoordinator.getAmountOfEvents());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating event coordinator failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new EventCoordinator(
                            eventCoordinator.getFirstname(),
                            eventCoordinator.getLastname(),
                            eventCoordinator.getEmail(),
                            eventCoordinator.getPhoneNumber(),
                            eventCoordinator.getAmountOfEvents()
                    );
                } else {
                    throw new SQLException("Creating event coordinator failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error inserting event coordinator into database.");
        }
    }

    @Override
    public EventCoordinator updateEventCoordinator(EventCoordinator eventCoordinator) throws Exception {
        String sql = "UPDATE EventCoordinator SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, amountOfEvents = ? WHERE email = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eventCoordinator.getFirstname());
            stmt.setString(2, eventCoordinator.getLastname());
            stmt.setString(3, eventCoordinator.getEmail());
            stmt.setInt(4, eventCoordinator.getPhoneNumber());
            stmt.setInt(5, eventCoordinator.getAmountOfEvents());
            stmt.setString(6, eventCoordinator.getEmail());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating event coordinator failed, no rows affected.");
            }

            return eventCoordinator;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error updating event coordinator in database.");
        }
    }

    @Override
    public void deleteEventCoordinator(EventCoordinator eventCoordinator) throws Exception {
        String sql = "DELETE FROM EventCoordinator WHERE email = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eventCoordinator.getEmail());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting event coordinator failed, no rows affected.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error deleting event coordinator from database.");
        }
    }
}
