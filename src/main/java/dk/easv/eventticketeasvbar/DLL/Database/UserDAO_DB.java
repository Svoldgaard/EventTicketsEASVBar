package dk.easv.eventticketeasvbar.DLL.Database;

import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import dk.easv.eventticketeasvbar.DLL.Interface.IAdmin;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_DB implements IAdmin {

    public DBConnection dbConnection;

    public UserDAO_DB() throws IOException {
        this.dbConnection = new DBConnection();
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "Select u.id,  u.firstName, u.lastName, u.email, u.phoneNumber, u.amountOfEvents, u.photo, u.userTypeID, t.userType " +
                "from [User] u " +
                "JOIN userType t on u.userTypeID = t.id";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                int amountOfEvents = rs.getInt("amountOfEvents");
                String photo = rs.getString("photo");
                String userType = rs.getString("userType");

                User user = new User(id, firstname, lastname, email, String.valueOf(phoneNumber), amountOfEvents, photo, userType);
                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error fetching event users from database.");
        }
        return users;
    }

    @Override
    public User createUser(User user) throws Exception {
        String sql = "INSERT INTO [User] (firstName, lastName, email, phoneNumber, amountOfEvents, photo, userTypeID) " +
                "VALUES (?, ?, ?, ?, ?, ?, 2)";


        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setInt(5, user.getAmountOfEvents());
            stmt.setString(6, user.getPhoto());
            //stmt.setInt(6, user.getUserTypeID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating event coordinator failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new User(
                            id,
                            user.getFirstname(),
                            user.getLastname(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getAmountOfEvents(),
                            user.getPhoto(),
                            2
                            //user.getUserTypeID()

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
    public User updateUser(User user) throws Exception {
        String updateUserSql = "UPDATE [User] SET  firstName = ?, lastName = ?, email = ?, phoneNumber = ?, amountOfEvents = ?, photo = ? " +
                " WHERE id = ?";


        try (Connection conn = dbConnection.getConnection();
             PreparedStatement updateUserStmt = conn.prepareStatement(updateUserSql)) {

            updateUserStmt.setString(1, user.getFirstname());
            updateUserStmt.setString(2, user.getLastname());
            updateUserStmt.setString(3, user.getEmail());
            updateUserStmt.setString(4, user.getPhoneNumber());
            updateUserStmt.setInt(5, user.getAmountOfEvents());
            updateUserStmt.setString(6, user.getPhoto());
            updateUserStmt.setInt(7, user.getId());

            int affectedRows = updateUserStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            }

            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error updating user in database.");
        }
    }

    // Helper method to get the current amount of events
    private int getCurrentAmountOfEvents(String email, Connection conn) throws SQLException {
        String sql = "SELECT amountOfEvents FROM EventCoordinator WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("amountOfEvents");
                }
            }
        }
        return 0; // Default if not found
    }


    @Override
    public void deleteUser(User user) throws Exception {
        String sql = "DELETE FROM [User] WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int userId = user.getId();
            System.out.println("Debug id: " + userId);

            stmt.setInt(1, user.getId());

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




