package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.EventCoordinator;
import dk.easv.eventticketeasvbar.BLL.LoginManager;
import dk.easv.eventticketeasvbar.BE.Login;

import java.io.IOException;
import java.util.List;

public class LoginModel {
    private final LoginManager loginManager;

    public LoginModel() throws IOException {
        this.loginManager = new LoginManager();
    }


    public Login login(String username, String password) throws Exception {
        return loginManager.authenticate(username, password);
    }


    public List<Login> getAllLogins() throws Exception {
        return loginManager.getAllLogins();
    }


    public void createLogin(EventCoordinator coordinator) throws Exception {
        loginManager.createLogin(coordinator);
    }



    public void updateLogin(String username, String newPassword) throws Exception {
        loginManager.updateLogin(username, newPassword);
    }


    public void deleteLogin(String username) throws Exception {
        loginManager.deleteLogin(username);
    }
}
