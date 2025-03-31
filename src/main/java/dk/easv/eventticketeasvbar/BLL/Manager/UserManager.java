package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.User;
import dk.easv.eventticketeasvbar.BLL.Utility.EventCoordinatorSearch;
import dk.easv.eventticketeasvbar.DLL.Database.UserDAO_DB;

import java.io.IOException;
import java.util.List;

public class UserManager {

    private UserDAO_DB UserDAO_DB;
    private static EventCoordinatorSearch eventCoordinatorSearch;

    public UserManager() throws IOException {
        UserDAO_DB = new UserDAO_DB();
        eventCoordinatorSearch = new EventCoordinatorSearch();
    }

    public List<User> getAllUsers() throws Exception {
        return UserDAO_DB.getAllUsers();
    }

    public User createUser(User user) throws Exception {
        return UserDAO_DB.createUser(user);
    }

    public User updateUser(User user) throws Exception {
        return UserDAO_DB.updateUser(user);
    }

    public void deleteUser(User user) throws Exception {
        UserDAO_DB.deleteUser(user);
    }

    public List<User> searchUsers(String query) throws Exception{
        List<User> allEvents = getAllUsers();
        List<User> searchResult = eventCoordinatorSearch.search(allEvents, query);
        return searchResult;
    }
}
