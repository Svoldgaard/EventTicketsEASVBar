package dk.easv.eventticketeasvbar.BE;


public class BarDrinksTicket extends BaseTicket {
    private int drinkCount;

    public BarDrinksTicket(String eventCode, String qrCode, int drinkCount) {
        super(eventCode, qrCode, "BarDrinksTicket");
        this.drinkCount = drinkCount;
    }

    public int getDrinkCount() {
        return drinkCount;
    }







}
