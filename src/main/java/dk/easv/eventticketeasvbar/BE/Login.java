package dk.easv.eventticketeasvbar.BE;

public class Login {
    private String username;
    private String password;
    private String access;

    // Constructor with all fields
    public Login(String username, String password, String access) {
        this.username = username;
        this.password = password;
        this.access = access;
    }

    // Constructor without 'access' field (for cases where it's not needed)
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Constructor for username-only objects (for deletions)
    public Login(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", access='" + access + '\'' +
                '}';
    }
}
