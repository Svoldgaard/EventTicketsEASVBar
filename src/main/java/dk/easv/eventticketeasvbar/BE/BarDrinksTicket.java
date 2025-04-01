package dk.easv.eventticketeasvbar.BE;


public class BarDrinksTicket extends BaseTicket {
    private Double Discount;

    public BarDrinksTicket(String eventCode, String photo, Double discount) {
        super(eventCode, photo, "BarDrinksTicket");
        this.Discount = discount;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }
}
