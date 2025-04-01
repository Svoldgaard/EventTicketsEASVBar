package dk.easv.eventticketeasvbar.BE;

public abstract class BaseTicket {
    private String eventCode;
    private String photo;
    private String ticketType;

    public BaseTicket(String eventCode, String photo, String ticketType) {
        this.eventCode = eventCode;
        this.photo = photo;
        this.ticketType = ticketType;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTicketType() {
        return ticketType;
    }
}
