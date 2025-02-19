package dk.easv.eventticketeasvbar.BE;

public class EventCoordinator {
    private String name;
    private String email;
    private int phoneNumber;
    private int amountOffEvents;

    public EventCoordinator(String name, String email, int phoneNumber, int amountOffEvents) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amountOffEvents = amountOffEvents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
