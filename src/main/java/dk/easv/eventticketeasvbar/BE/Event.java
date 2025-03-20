package dk.easv.eventticketeasvbar.BE;
//Java Imports
import javafx.scene.image.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {


    private int id;
    private String event;
    private String eventName;
    private String location;
    private LocalDate date;
    private Float time;
    private Float duration;
    private Float price;
    private List<User> coordinators;
    private Image image;
    private String description;
    private String coordinator;
    private String picture;
    private int parkingInfoID;
    private int ticketID;
    private String category;

    public Event(Image image, String event, String description) {
        this.image = image;
        this.event = event;
        this.description = description;
    }

    public Event(String event, String location, LocalDate date, Float time, Float duration, Float price, List<User> coordinators) {
        this.event = event;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinators = new ArrayList<>();  // Initialize the list
    }

    public Event(String eventName, String location, LocalDate date, Float time, Float duration, Float price) {
        this.event = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinators = new ArrayList<>();  // Ensure coordinators list is initialized
    }

    public Event(int id, String eventName, String location, LocalDate date, float time, float duration, float price,
                 String coordinator, String picture, String description, int parkingInfoID, int ticketID, String category) {
        this.id = id;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinator = coordinator;
        this.picture = picture;
        this.description = description;
        this.parkingInfoID = parkingInfoID;
        this.ticketID = ticketID;
        this.category = category;
    }

    public void addCoordinator(User coordinator) {
        if (!coordinators.contains(coordinator)) {
            coordinators.add(coordinator);
            coordinator.setAmountOfEvents(coordinator.getAmountOfEvents() + 1);
        }
    }

    public List<User> getCoordinators() {
        return coordinators;
    }

    public String getCoordinatorsAsString() {
        if (coordinators.isEmpty()) return "None";
        return String.join(", ", coordinators.stream().map(User::getFirstname).toList());
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Event(int i, String eventTile) {

    }

    public Image getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getTime() {
        return time;
    }

    public void setTime(Float time) {
        this.time = time;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setCoordinators(List<User> coordinators) {
        this.coordinators = coordinators;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {return id;}

    public void setId(int id) { this.id = id;}

    public String getEventName() {return eventName;}

    public void setEventName(String eventName) {this.eventName = eventName;}

    public String getCoordinator() {return coordinator;}

    public void setCoordinator(String coordinator) {this.coordinator = coordinator;}

    public String getPicture() {return picture;}

    public void setPicture(String picture) {this.picture = picture;}

    public int getParkingInfoID() {return parkingInfoID;}

    public void setParkingInfoID(int parkingInfoID) {this.parkingInfoID = parkingInfoID;}

    public int getTicketID() {return ticketID;}

    public void setTicketID(int ticketID) {this.ticketID = ticketID;}

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public String getTitle() {
        String Date = "";
        return Date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event='" + event + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }

}
