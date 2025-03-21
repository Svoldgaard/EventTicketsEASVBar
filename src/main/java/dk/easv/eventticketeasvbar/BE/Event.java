package dk.easv.eventticketeasvbar.BE;
//Java Imports
import javafx.scene.image.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private int id;
    private String event;
    private String location;
    private String address;
    private int postalCode;
    private String city;
    private String eventName;

    private LocalDate date;
    private Float time;
    private Float duration;
    private Float price;
    private List<User> coordinators;
    private Image image;
    private String description;


    public Event(int id, String event, String address, int postalCode, String city, LocalDate date, Float time, Float duration, Float price, List<User> coordinators) {
        this.id = id;
        this.event = event;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinators = new ArrayList<>();
    }

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
        this.coordinators = new ArrayList<>();
    }

    public Event(String event, String location, LocalDate date, Float time, Float duration, Float price) {
        this.event = event;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinators = new ArrayList<>();  // Ensure coordinators list is initialized
    }

    public Event(String eventName, String address, int postalCode, String city, LocalDate date, float time,
                 float duration, float price) {
        this.event = eventName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }

    public Event(String eventName, String location, String address, int postalCode, String city, LocalDate date, float time, float duration, float price) {
        this.event = eventName;
        this.location = location;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinators = new ArrayList<>();
    }

    public Event(int id, String eventName, String location, String address, int postalCode, String city, LocalDate date, float time, float duration, float price, List<User> coordinators) {
        this.id = id;
        this.event = eventName;
        this.location = location;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinators = new ArrayList<>();

    }

    public Event(int id, String eventName, String location, LocalDate date, float time, float duration, float price, List<User> coordinators) {
        this.id = id;
        this.event = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinators = coordinators;
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
        if (coordinators == null || coordinators.isEmpty()) return "None";
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

    public String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event='" + event + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }


    public String getTitle() {
        String Date = "";
        return Date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return eventName;
    }
}
