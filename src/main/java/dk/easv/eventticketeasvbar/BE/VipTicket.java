package dk.easv.eventticketeasvbar.BE;

import com.itextpdf.text.Document;

import java.io.FileOutputStream;

public class VipTicket extends BaseTicket {
    private String section;
    private String row;
    private String seat;

    public VipTicket(String eventCode, String qrCode, String section, String row, String seat) {
        super(eventCode, qrCode, "VipTicket");
        this.section = section;
        this.row = row;
        this.seat = seat;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }





}
