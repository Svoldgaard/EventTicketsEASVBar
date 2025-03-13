package dk.easv.eventticketeasvbar.BLL;

import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class HashExistingPasswords {

    // this class is used to change mock data to Hash password

    public static void main(String[] args) {
        try {
            // Use the existing DBConnection class
            DBConnection dbConnection = new DBConnection();
            Connection conn = dbConnection.getConnection();

            // Get users with plain text passwords
            String selectSQL = "SELECT username, password FROM Login";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {

                while (rs.next()) {
                    String username = rs.getString("username");
                    String plainTextPassword = rs.getString("password");

                    // Check if password is already hashed (assumes bcrypt hashes start with "$2a$")
                    if (plainTextPassword.startsWith("$2a$")) {
                        System.out.println("Skipping already hashed password for user: " + username);
                        continue;
                    }

                    // Hash the password
                    String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

                    // Update the database with the hashed password
                    String updateSQL = "UPDATE Login SET password = ? WHERE username = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                        updateStmt.setString(1, hashedPassword);
                        updateStmt.setString(2, username);
                        updateStmt.executeUpdate();
                        System.out.println("Updated password for user: " + username);
                    }
                }
            }
            System.out.println("All passwords have been hashed successfully!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
