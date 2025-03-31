package dk.easv.eventticketeasvbar.BE;

public abstract class BaseTicket {
    private String eventCode;
    private String qrCode;
    private String ticketType;

    public BaseTicket(String eventCode, String qrCode, String ticketType) {
        this.eventCode = eventCode;
        this.qrCode = qrCode;
        this.ticketType = ticketType;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getTicketType() {
        return ticketType;
    }
}
