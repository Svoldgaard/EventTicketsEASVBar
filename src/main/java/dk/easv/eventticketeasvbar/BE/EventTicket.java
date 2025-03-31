package dk.easv.eventticketeasvbar.BE;

public class EventTicket extends BaseTicket {
    private String section;
    private String row;
    private String seat;

    public EventTicket(String eventCode, String qrCode, String section, String row, String seat) {
        super(eventCode, qrCode, "EventTicket");
        this.section = section;
        this.row = row;
        this.seat = seat;
    }

    public String getSection() {
        return section;
    }

    public String getRow() {
        return row;
    }

    public String getSeat() {
        return seat;
    }
}
