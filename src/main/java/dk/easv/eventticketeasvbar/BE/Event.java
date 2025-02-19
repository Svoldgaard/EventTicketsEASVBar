package dk.easv.eventticketeasvbar.BE;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class Event {

    private String event;
    private String location;
    private LocalDate date;
    private Double time;
    private Double duration;
    private Double price;
    private String Coordinator;
    private Image image;
    private String description;



    public Event(String event, String location, LocalDate date, Double time, Double duration, Double price , String Coordinator) {
        this.event = event;
        this.location = location;
        this.date = date; // Use the provided LocalDate object
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.Coordinator = Coordinator; // Use the provided Coordinator

    }

    public Event(String event, String location, LocalDate date, Double time, String Coordinator) {
        this.event = event;
        this.location = location;
        this.date = date;
        this.time = time;
        this.Coordinator = Coordinator;

    }

    public Event(Image image, String event, String description) {
        this.image = image;
        this.event = event;
        this.description = description;
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCoordinator() {
        return Coordinator;
    }

    public void setCoordinator(String coordinator) {
        Coordinator = coordinator;
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
                ", Coordinator='" + Coordinator + '\'' +
                '}';
    }
}
