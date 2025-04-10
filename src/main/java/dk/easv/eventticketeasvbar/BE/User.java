package dk.easv.eventticketeasvbar.BE;

import javafx.scene.image.Image;

import java.util.Objects;

public class User {
    private int id;
    private String photo;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private int amountOfEvents;
    private String userType;
    private int userTypeID;
    private Image image;

    // Constructor for btnSave in CreateUserController
    public User(String firstname, String lastname, String email, String phoneNumber, int amountOfEvents, String imagePath) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOfEvents = amountOfEvents;
        this.photo = imagePath;
    }

    // Used in UserDAO_DB in createUser
    public User(int id, String firstname, String lastname, String email, String phoneNumber, int amountOfEvents,  String imagePath, int i) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOfEvents = amountOfEvents;
        this.photo = imagePath;
        this.userTypeID = i;
    }

    // Used in EventDAO_DB in getAllEvents
    public User(String name) {
        this.firstname = name;
    }

    // Used in UserDAO_DB in getAllUsers
    public User(int id, String firstname, String lastname, String email, String phoneNumber, int amountOfEvents,  String imagePath, String userType) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOfEvents = amountOfEvents;
        this.photo = imagePath;
        this.userType = userType;

    }

   //public User(String firstName, String lastName, String email, String phoneNumber, int amountOfEvents, String imagePath) {}

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAmountOfEvents() {
        return amountOfEvents;
    }

    public void setAmountOfEvents(int amountOfEvents) {
        this.amountOfEvents = amountOfEvents;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return firstname;
    }

}
