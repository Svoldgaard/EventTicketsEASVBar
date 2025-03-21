package dk.easv.eventticketeasvbar.BE;

public class Parking {
    private int id;
    private int eventId;  // <-- Add this field
    private String address;
    private String city;
    private int postalCode;

    public Parking(int id, String address, String city, int postalCode) {
        this.id = id;
        this.eventId = eventId;  // <-- Assign event ID
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {  // <-- Getter for event ID
        return eventId;
    }

    public void setEventId(int eventId) {  // <-- Setter for event ID
        this.eventId = eventId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
