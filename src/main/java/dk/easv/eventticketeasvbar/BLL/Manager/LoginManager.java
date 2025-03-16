package dk.easv.eventticketeasvbar.BLL.Manager;
// Project Imports
import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.BE.Login;
import dk.easv.eventticketeasvbar.DLL.Database.LoginDAO_DB;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;
//Java Imports
import java.io.IOException;
import java.util.List;

public class LoginManager {
    private final LoginDAO_DB loginDAO;

    public LoginManager() throws IOException {
        this.loginDAO = new LoginDAO_DB(new DBConnection()); // Inject DB connection
    }


    public Login authenticate(String username, String password) throws Exception {
        return loginDAO.verifyLogin(username, password);
    }


    public List<Login> getAllLogins() throws Exception {
        return loginDAO.getAllLogin();
    }


    public void createLogin(EventCoordinator coordinator) throws Exception {
        String firstname = coordinator.getFirstname().replaceAll("\\s", "").toLowerCase();
        String username = (firstname.length() >= 5) ? firstname.substring(0, 5) : firstname;
        String password = username; // Default password same as username
        String access = "Event";

        Login newLogin = new Login(username, password, access);
        loginDAO.createLogin(newLogin);
    }



    public void updateLogin(String username, String newPassword) throws Exception {
        Login updatedLogin = new Login(username, newPassword);
        loginDAO.updateLogin(updatedLogin);
    }


    public void deleteLogin(String username) throws Exception {
        Login loginToDelete = new Login(username, null);
        loginDAO.deleteLogin(loginToDelete);
    }
}
