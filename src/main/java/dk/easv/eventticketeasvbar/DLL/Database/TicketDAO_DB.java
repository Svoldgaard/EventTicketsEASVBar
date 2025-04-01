package dk.easv.eventticketeasvbar.DLL.Database;

import dk.easv.eventticketeasvbar.BE.BaseTicket;
import dk.easv.eventticketeasvbar.BE.EventTicket;
import dk.easv.eventticketeasvbar.BE.VipTicket;
import dk.easv.eventticketeasvbar.BE.BarDrinksTicket;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO_DB {

    public void saveTicket(BaseTicket ticket) throws SQLException, IOException {
        DBConnection dbConnection = new DBConnection();
        try (Connection connection = dbConnection.getConnection()) {
            String sql;

            if (ticket instanceof EventTicket) {
                sql = "INSERT INTO Tickets (eventCode, photo, ticketTypeID, section, row, seat) VALUES (?, ?, ?, ?, ?, ?)";
                EventTicket eventTicket = (EventTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, eventTicket.getEventCode());
                    statement.setString(2, eventTicket.getPhoto());
                    statement.setInt(3, 1); // EventTicket type ID
                    statement.setString(4, eventTicket.getSection());
                    statement.setString(5, eventTicket.getRow());
                    statement.setString(6, eventTicket.getSeat());
                    statement.executeUpdate();
                }
            } else if (ticket instanceof VipTicket) {
                sql = "INSERT INTO Tickets (eventCode, photo, ticketTypeID, section, row, seat) VALUES (?, ?, ?, ?, ?, ?)";
                VipTicket vipTicket = (VipTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, vipTicket.getEventCode());
                    statement.setString(2, vipTicket.getPhoto());
                    statement.setInt(3, 2); // VipTicket type ID
                    statement.setString(4, vipTicket.getSection());
                    statement.setString(5, vipTicket.getRow());
                    statement.setString(6, vipTicket.getSeat());
                    statement.executeUpdate();
                }
            } else if (ticket instanceof BarDrinksTicket) {
                sql = "INSERT INTO Tickets (eventCode, photo, ticketTypeID, discount) VALUES (?, ?, ?, ?)";
                BarDrinksTicket barDrinksTicket = (BarDrinksTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, barDrinksTicket.getEventCode());
                    statement.setString(2, barDrinksTicket.getPhoto());
                    statement.setInt(3, 3); // BarDrinksTicket type ID
                    statement.setDouble(4, barDrinksTicket.getDiscount());
                    statement.executeUpdate();
                }
            }
        }
    }

    public List<BaseTicket> getAllTickets() throws SQLException, IOException {
        DBConnection dbConnection = new DBConnection();
        List<BaseTicket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Tickets";

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int ticketTypeID = resultSet.getInt("ticketTypeID");
                String eventCode = resultSet.getString("eventCode");
                String photo = resultSet.getString("photo");

                if (ticketTypeID == 1) { // EventTicket
                    String section = resultSet.getString("section");
                    String row = resultSet.getString("row");
                    String seat = resultSet.getString("seat");
                    tickets.add(new EventTicket(eventCode, photo, section, row, seat));
                } else if (ticketTypeID == 2) { // VipTicket
                    String section = resultSet.getString("section");
                    String row = resultSet.getString("row");
                    String seat = resultSet.getString("seat");
                    tickets.add(new VipTicket(eventCode, photo, section, row, seat));
                } else if (ticketTypeID == 3) { // BarDrinksTicket
                    Double discount = resultSet.getDouble("discount");
                    tickets.add(new BarDrinksTicket(eventCode, photo, discount));
                }
            }
        }

        return tickets;
    }

    public void updateTicket(BaseTicket ticket) throws SQLException, IOException {
        DBConnection dbConnection = new DBConnection();
        try (Connection connection = dbConnection.getConnection()) {
            String sql;

            if (ticket instanceof EventTicket) {
                sql = "UPDATE Tickets SET section = ?, row = ?, seat = ? WHERE eventCode = ? AND photo = ?";
                EventTicket eventTicket = (EventTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, eventTicket.getSection());
                    statement.setString(2, eventTicket.getRow());
                    statement.setString(3, eventTicket.getSeat());
                    statement.setString(4, eventTicket.getEventCode());
                    statement.setString(5, eventTicket.getPhoto());
                    statement.executeUpdate();
                }
            } else if (ticket instanceof VipTicket) {
                sql = "UPDATE Tickets SET section = ?, row = ?, seat = ? WHERE eventCode = ? AND photo = ?";
                VipTicket vipTicket = (VipTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, vipTicket.getSection());
                    statement.setString(2, vipTicket.getRow());
                    statement.setString(3, vipTicket.getSeat());
                    statement.setString(4, vipTicket.getEventCode());
                    statement.setString(5, vipTicket.getPhoto());
                    statement.executeUpdate();
                }
            } else if (ticket instanceof BarDrinksTicket) {
                sql = "UPDATE Tickets SET drinkCount = ? WHERE eventCode = ? AND photo = ?";
                BarDrinksTicket barDrinksTicket = (BarDrinksTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setDouble(1, barDrinksTicket.getDiscount());
                    statement.setString(2, barDrinksTicket.getEventCode());
                    statement.setString(3, barDrinksTicket.getPhoto());
                    statement.executeUpdate();
                }
            }
        }
    }

    public void deleteTicket(String photo) throws SQLException, IOException {
        DBConnection dbConnection = new DBConnection();
        String sql = "DELETE FROM Tickets WHERE photo = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, photo);
            statement.executeUpdate();
        }
    }

    public void updateTicketPDFPath(String qrCode, String pdfPath) throws SQLException, IOException {
        DBConnection dbConnection = new DBConnection();
        String sql = "UPDATE Tickets SET pdfPath = ? WHERE qrCode = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pdfPath);
            stmt.setString(2, qrCode);
            stmt.executeUpdate();
        }
    }

}
