package dk.easv.eventticketeasvbar.DLL.Database;
// Project Imports
import dk.easv.eventticketeasvbar.BE.Login;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import dk.easv.eventticketeasvbar.DLL.Interface.ILogin;
import org.mindrot.jbcrypt.BCrypt;
// Java Imports
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO_DB implements ILogin {
    private final DBConnection dbConnection;


    public LoginDAO_DB(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // table name Login
    // colum names username, password, access

    @Override
    public List<Login> getAllLogin() throws Exception {
        List<Login> logins = new ArrayList<>();
        String sql = "SELECT username FROM Login";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Login login = new Login(rs.getString("username"), null);
                logins.add(login);
            }
        }
        return logins;
    }


    @Override
    public Login createLogin(Login login) throws Exception {
        String sql = "INSERT INTO Login (username, password, access) VALUES (?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(login.getPassword(), BCrypt.gensalt());

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, login.getUsername());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, login.getAccess());
            stmt.executeUpdate();
        }
        return login;
    }



    @Override
    public Login updateLogin(Login login) throws Exception {
        String sql = "UPDATE Login SET password = ? WHERE username = ?";
        String hashedPassword = BCrypt.hashpw(login.getPassword(), BCrypt.gensalt());

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hashedPassword);
            stmt.setString(2, login.getUsername());
            stmt.executeUpdate();
        }
        return login;
    }


    @Override
    public void deleteLogin(Login login) throws Exception {
        String sql = "DELETE FROM Login WHERE username = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login.getUsername());
            stmt.executeUpdate();
        }
    }


    public Login verifyLogin(String username, String password) throws Exception {
        String sql = "SELECT username, password, access FROM Login WHERE username = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    return new Login(username, null, rs.getString("access"));
                }
            }
        }
        return null;
    }
}
