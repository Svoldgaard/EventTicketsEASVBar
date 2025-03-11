package dk.easv.eventticketeasvbar.DLL.Interface;

// Project Import
import dk.easv.eventticketeasvbar.BE.Login;

// Java Import
import java.util.List;


public interface ILogin {


    List<Login> getAllLogin() throws Exception;
    Login createLogin(Login login) throws Exception;
    Login updateLogin(Login login) throws Exception;
    void deleteLogin(Login login) throws Exception;

}
