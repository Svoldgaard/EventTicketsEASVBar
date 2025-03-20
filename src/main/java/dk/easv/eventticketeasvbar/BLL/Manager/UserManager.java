package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.DLL.Database.UserDAO_DB;

import java.io.IOException;
import java.util.List;

public class UserManager {

    private UserDAO_DB adminDAO_DB;

    public UserManager() throws IOException {
        adminDAO_DB = new UserDAO_DB();
    }

    public List<User> getAllUsers() throws Exception {
        return adminDAO_DB.getAllUsers();
    }

    public User createUser(User user) throws Exception {
        return adminDAO_DB.createUser(user);
    }

    public User updateUser(User user) throws Exception {
        return adminDAO_DB.updateUser(user);
    }

    public void deleteUser(User user) throws Exception {
        adminDAO_DB.deleteUser(user);
    }
}
