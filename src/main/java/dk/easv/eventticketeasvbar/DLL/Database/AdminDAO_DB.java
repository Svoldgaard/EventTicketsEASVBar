package dk.easv.eventticketeasvbar.DLL.Database;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import dk.easv.eventticketeasvbar.DLL.Interface.IAdmin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDAO_DB implements IAdmin {

    private DBConnection dbConnection;

    public AdminDAO_DB () throws IOException {
        this.dbConnection = new DBConnection();
    }

    @Override
    public List<EventCoordinator> getAllEventCoordinators() throws Exception {
        ArrayList<EventCoordinator> coordinators = new ArrayList<>();

        String sql = "SELECT * FROM EventCoordinator";

        try(Connection conn = dbConnection.getConnection();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String  firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                String email = rs.getString("email");
                int phoneNumber = rs.getInt("phoneNumber");
                int amountOfEvents = rs.getInt("amountOfEvents");

                EventCoordinator coordinator = new EventCoordinator(firstname, lastname, email, phoneNumber, amountOfEvents);
                coordinators.add(coordinator);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return coordinators;
    }

    @Override
    public Event createEventCoordinator(EventCoordinator eventCoordinator) throws Exception {
        return null;
    }

    @Override
    public Event updateEventCoordinator(EventCoordinator eventCoordinator) throws Exception {
        return null;
    }

    @Override
    public void deleteEventCoordinator(EventCoordinator eventCoordinator) throws Exception {

    }
}
