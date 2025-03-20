package dk.easv.eventticketeasvbar.BE;
//Java Imports
import javafx.scene.image.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    private String event;
    private String location;
    private LocalDate date;
    private Float time;
    private Float duration;
    private Float price;
    private List<EventCoordinator> coordinators;
    private Image image;
    private String description;




    public Event(Image image, String event, String description) {
        this.image = image;
        this.event = event;
        this.description = description;
    }

    public Event(String event, String location, LocalDate date, Float time, Float duration, Float price, List<EventCoordinator> coordinators) {
        this.event = event;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.coordinators = new ArrayList<>();  // Initialize the list
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

    public void addCoordinator(EventCoordinator coordinator) {
        if (!coordinators.contains(coordinator)) {
            coordinators.add(coordinator);
            coordinator.setAmountOfEvents(coordinator.getAmountOfEvents() + 1);
        }
    }

    public List<EventCoordinator> getCoordinators() {
        return coordinators;
    }

    public String getCoordinatorsAsString() {
        if (coordinators.isEmpty()) return "None";
        return String.join(", ", coordinators.stream().map(EventCoordinator::getFirstname).toList());
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


    public String getTitle() {
        String Date = "";
        return Date;
    }
}
