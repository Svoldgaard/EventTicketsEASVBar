package dk.easv.eventticketeasvbar.DLL.Database;

import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.*;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserDAO_DB_UnitTest {

    private UserDAO_DB userDAO;
    private DBConnection dbConnectionMock;
    private Connection connectionMock;
    private PreparedStatement preparedStatementMock;
    private Statement statementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void setUp() throws IOException {
        dbConnectionMock = mock(DBConnection.class);
        connectionMock = mock(Connection.class);
        preparedStatementMock = mock(PreparedStatement.class);
        statementMock = mock(Statement.class);
        resultSetMock = mock(ResultSet.class);

        userDAO = new UserDAO_DB();
        userDAO.dbConnection = dbConnectionMock;
    }


    @Test
    void getAllUsers() throws Exception {
        when(dbConnectionMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);

        when(resultSetMock.next()).thenReturn(true, true, false);
        when(resultSetMock.getInt("id")).thenReturn(1, 2);
        when(resultSetMock.getString("firstName")).thenReturn("John", "Jane");
        when(resultSetMock.getString("lastName")).thenReturn("Doe", "Smith");
        when(resultSetMock.getString("email")).thenReturn("john.doe@example.com", "jane.smith@example.com");
        when(resultSetMock.getString("phoneNumber")).thenReturn("1234567890", "0987654321");
        when(resultSetMock.getInt("amountOfEvents")).thenReturn(5, 3);
        when(resultSetMock.getString("photo")).thenReturn("photo1.jpg", "photo2.jpg");
        when(resultSetMock.getString("userType")).thenReturn("Admin", "User");

        List<User> users = userDAO.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getFirstname());
        assertEquals("Jane", users.get(1).getFirstname());
    }

    @Test
    void createUser() throws Exception {
        User user = new User(0, "John", "Doe", "john.doe@example.com", "1234567890", 5, "photo1.jpg", "Admin");

        when(dbConnectionMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(preparedStatementMock.getGeneratedKeys()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt(1)).thenReturn(1);

        User createdUser = userDAO.createUser(user);

        assertEquals(1, createdUser.getId());
        assertEquals("John", createdUser.getFirstname());
    }

    @Test
    void updateUser() throws Exception {
        User user = new User(1, "John", "Doe", "john.doe@example.com", "1234567890", 5, "photo1.jpg", "Admin");

        when(dbConnectionMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        User updatedUser = userDAO.updateUser(user);

        assertEquals(1, updatedUser.getId());
        assertEquals("John", updatedUser.getFirstname());
    }

    @Test
    void deleteUser() throws Exception {
        User user = new User(1, "John", "Doe", "john.doe@example.com", "1234567890", 5, "photo1.jpg", "Admin");

        when(dbConnectionMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        userDAO.deleteUser(user);

        verify(preparedStatementMock, times(1)).executeUpdate();
    }
}