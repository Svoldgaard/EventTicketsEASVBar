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
                sql = "INSERT INTO Tickets (eventCode, qrCode, ticketTypeID, section, row, seat) VALUES (?, ?, ?, ?, ?, ?)";
                EventTicket eventTicket = (EventTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, eventTicket.getEventCode());
                    statement.setString(2, eventTicket.getQrCode());
                    statement.setInt(3, 1); // EventTicket type ID
                    statement.setString(4, eventTicket.getSection());
                    statement.setString(5, eventTicket.getRow());
                    statement.setString(6, eventTicket.getSeat());
                    statement.executeUpdate();
                }
            } else if (ticket instanceof VipTicket) {
                sql = "INSERT INTO Tickets (eventCode, qrCode, ticketTypeID, section, row, seat) VALUES (?, ?, ?, ?, ?, ?)";
                VipTicket vipTicket = (VipTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, vipTicket.getEventCode());
                    statement.setString(2, vipTicket.getQrCode());
                    statement.setInt(3, 2); // VipTicket type ID
                    statement.setString(4, vipTicket.getSection());
                    statement.setString(5, vipTicket.getRow());
                    statement.setString(6, vipTicket.getSeat());
                    statement.executeUpdate();
                }
            } else if (ticket instanceof BarDrinksTicket) {
                sql = "INSERT INTO Tickets (eventCode, qrCode, ticketTypeID, drinkCount) VALUES (?, ?, ?, ?)";
                BarDrinksTicket barDrinksTicket = (BarDrinksTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, barDrinksTicket.getEventCode());
                    statement.setString(2, barDrinksTicket.getQrCode());
                    statement.setInt(3, 3); // BarDrinksTicket type ID
                    statement.setInt(4, barDrinksTicket.getDrinkCount());
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
                String qrCode = resultSet.getString("qrCode");

                if (ticketTypeID == 1) { // EventTicket
                    String section = resultSet.getString("section");
                    String row = resultSet.getString("row");
                    String seat = resultSet.getString("seat");
                    tickets.add(new EventTicket(eventCode, qrCode, section, row, seat));
                } else if (ticketTypeID == 2) { // VipTicket
                    String section = resultSet.getString("section");
                    String row = resultSet.getString("row");
                    String seat = resultSet.getString("seat");
                    tickets.add(new VipTicket(eventCode, qrCode, section, row, seat));
                } else if (ticketTypeID == 3) { // BarDrinksTicket
                    int drinkCount = resultSet.getInt("drinkCount");
                    tickets.add(new BarDrinksTicket(eventCode, qrCode, drinkCount));
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
                sql = "UPDATE Tickets SET section = ?, row = ?, seat = ? WHERE eventCode = ? AND qrCode = ?";
                EventTicket eventTicket = (EventTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, eventTicket.getSection());
                    statement.setString(2, eventTicket.getRow());
                    statement.setString(3, eventTicket.getSeat());
                    statement.setString(4, eventTicket.getEventCode());
                    statement.setString(5, eventTicket.getQrCode());
                    statement.executeUpdate();
                }
            } else if (ticket instanceof VipTicket) {
                sql = "UPDATE Tickets SET section = ?, row = ?, seat = ? WHERE eventCode = ? AND qrCode = ?";
                VipTicket vipTicket = (VipTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, vipTicket.getSection());
                    statement.setString(2, vipTicket.getRow());
                    statement.setString(3, vipTicket.getSeat());
                    statement.setString(4, vipTicket.getEventCode());
                    statement.setString(5, vipTicket.getQrCode());
                    statement.executeUpdate();
                }
            } else if (ticket instanceof BarDrinksTicket) {
                sql = "UPDATE Tickets SET drinkCount = ? WHERE eventCode = ? AND qrCode = ?";
                BarDrinksTicket barDrinksTicket = (BarDrinksTicket) ticket;
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, barDrinksTicket.getDrinkCount());
                    statement.setString(2, barDrinksTicket.getEventCode());
                    statement.setString(3, barDrinksTicket.getQrCode());
                    statement.executeUpdate();
                }
            }
        }
    }

    public void deleteTicket(String qrCode) throws SQLException, IOException {
        DBConnection dbConnection = new DBConnection();
        String sql = "DELETE FROM Tickets WHERE qrCode = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, qrCode);
            statement.executeUpdate();
        }
    }
}
