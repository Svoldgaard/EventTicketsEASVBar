package dk.easv.eventticketeasvbar.DLL.Database;

import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAO_DB_IntegrationTest {

    private UserDAO_DB userDAO;
    private DBConnection dbConnection;
    private int createdUserId;

    @BeforeEach
    void setUp() throws Exception {
        dbConnection = new DBConnection();
        userDAO = new UserDAO_DB();
        userDAO.dbConnection = dbConnection;
    }

    @Test
    void getAllUsers() throws Exception {
        List<User> users = userDAO.getAllUsers();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void createUser()throws Exception {
        User user = new User(0, "John", "Doe", "john.doe@example.com", "1234567890", 5, "photo1.jpg", "Admin");
        User createdUser = userDAO.createUser(user);
        assertNotNull(createdUser);
        assertTrue(createdUser.getId() > 0);
        createdUserId = createdUser.getId();
    }

    @Test
    void updateUser()throws Exception {
        User user = new User(114, "John", "Doe", "john.doe@example.com", "1234567890", 5, "photo1.jpg", "Admin");
        User updatedUser = userDAO.updateUser(user);
        assertNotNull(updatedUser);
        assertEquals("John", updatedUser.getFirstname());
    }

    @Test
    void deleteUser() throws Exception {
        User user = new User(114, "John", "Doe", "john.doe@example.com", "1234567890", 5, "photo1.jpg", "Admin");
        userDAO.deleteUser(user);
        // Verify user is deleted by trying to fetch it again
        List<User> users = userDAO.getAllUsers();
        assertFalse(users.stream().anyMatch(u -> u.getId() == user.getId()));
    }
}