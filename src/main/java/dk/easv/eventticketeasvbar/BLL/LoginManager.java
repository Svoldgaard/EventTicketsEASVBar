package dk.easv.eventticketeasvbar.BLL;

import dk.easv.eventticketeasvbar.BE.Login;
import dk.easv.eventticketeasvbar.DLL.Database.LoginDAO_DB;
import dk.easv.eventticketeasvbar.DLL.DBConnection.DBConnection;

import java.io.IOException;
import java.util.List;

public class LoginManager {
    private final LoginDAO_DB loginDAO;

    public LoginManager() throws IOException {
        this.loginDAO = new LoginDAO_DB(new DBConnection()); // Inject DB connection
    }


    public boolean authenticate(String username, String password) throws Exception {
        return loginDAO.verifyLogin(username, password);
    }


    public List<Login> getAllLogins() throws Exception {
        return loginDAO.getAllLogin();
    }


    public void createLogin(String username, String password) throws Exception {
        Login newLogin = new Login(username, password);
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
