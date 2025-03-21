package dk.easv.eventticketeasvbar.DLL.Database;

import dk.easv.eventticketeasvbar.BE.Parking;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import dk.easv.eventticketeasvbar.DLL.Interface.IParking;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingDAO_DB implements IParking {

    private final DBConnection dbConnection;

    public ParkingDAO_DB() throws IOException {
        dbConnection = new DBConnection();
    }

    @Override
    public List<Parking> getAllParking() throws Exception {
        List<Parking> parkingList = new ArrayList<>();
        String sql = "SELECT id, address, postalCode, city FROM ParkingInfo";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                int postalCode = rs.getInt("postalCode");
                String city = rs.getString("city");

                parkingList.add(new Parking(id, address, city, postalCode));
            }
        }
        return parkingList;
    }

    @Override
    public Parking createEvent(Parking parking) throws Exception {
        String sql = "INSERT INTO ParkingInfo (address, postalCode, city) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, parking.getAddress());
            stmt.setInt(2, parking.getPostalCode());
            stmt.setString(3, parking.getCity());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating parking failed, no rows affected.");
            }

            // Retrieve generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    parking.setId(generatedKeys.getInt(1));
                }
            }
        }
        return parking;
    }

    @Override
    public Parking updateEvent(Parking parking) throws Exception {
        String sql = "UPDATE ParkingInfo SET address = ?, postalCode = ?, city = ? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, parking.getAddress());
            stmt.setInt(2, parking.getPostalCode());
            stmt.setString(3, parking.getCity());
            stmt.setInt(4, parking.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating parking failed, no rows affected.");
            }
        }
        return parking;
    }

    @Override
    public void deleteEvent(Parking parking) throws Exception {
        String sql = "DELETE FROM ParkingInfo WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, parking.getId());
            stmt.executeUpdate();
        }
    }
}
