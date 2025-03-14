package dk.easv.eventticketeasvbar.BE;

public class EventCoordinator {
    private String firstname;
    private String lastname;
    private String email;
    private int phoneNumber;
    private int amountOffEvents;

    public EventCoordinator(String firstname,String lastname, String email, int phoneNumber, int amountOffEvents) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOffEvents = amountOffEvents;
    }

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

    public int getAmountOffEvents() {
        return amountOffEvents;
    }

    public void setAmountOffEvents(int amountOffEvents) {
        this.amountOffEvents = amountOffEvents;
    }

    @Override
    public String toString() {
        return "EventCoordinator{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", amountOffEvents=" + amountOffEvents +
                '}';
    }
}
