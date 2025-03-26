package dk.easv.eventticketeasvbar.BE;

public class User {
    private int id;
    private String photo;
    private String firstname;
    private String lastname;
    private String email;
    private int phoneNumber;
    private int amountOfEvents;
    private String userType;
    private int userTypeID;

    public User(int id, String firstname, String lastname, String email, int phoneNumber, int amountOfEvents, String userType) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOfEvents = amountOfEvents;
    }

    public User(String photo, String firstname, String lastname, String email, int phoneNumber, int amountOfEvents) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOfEvents = amountOfEvents;
    }

    /*public User(int id, String firstname, String lastname, String email, int phoneNumber, int amountOfEvents, int userTypeID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOfEvents = amountOfEvents;
        this.userTypeID = userTypeID;
    }*/

    public User(int id, String photo, String firstname, String lastname, String email, int phoneNumber, int amountOfEvents, int i) {
        this.id = id;
        this.photo = photo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOfEvents = amountOfEvents;
        this.userTypeID = i;
    }

    public User(String name) {
        this.firstname = name;
    }

    public User(int id, String photo, String firstname, String lastname, String email, int phoneNumber, int amountOfEvents, String userType) {
        this.id = id;
        this.photo = photo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOfEvents = amountOfEvents;
        this.userType = userType;

    }


    public int getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() { return photo;}

    public void setPhoto(String photo) { this.photo = photo;}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAmountOfEvents() {
        return amountOfEvents;
    }

    public void setAmountOfEvents(int amountOfEvents) {
        this.amountOfEvents = amountOfEvents;
    }

    @Override
    public String toString() {
        return firstname;
    }



}
