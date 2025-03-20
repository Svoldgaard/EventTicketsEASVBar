package dk.easv.eventticketeasvbar.DLL.Interface;

import dk.easv.eventticketeasvbar.BE.User;

import java.util.List;

public interface IAdmin {

    List<User> getAllUsers() throws Exception;

    User createUser(User user) throws Exception;

    User updateUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;


}
