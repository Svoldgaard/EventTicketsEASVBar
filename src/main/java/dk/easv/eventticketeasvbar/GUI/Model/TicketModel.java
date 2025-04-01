package dk.easv.eventticketeasvbar.GUI.Model;

import dk.easv.eventticketeasvbar.BE.BaseTicket;
import dk.easv.eventticketeasvbar.BE.EventTicket;
import dk.easv.eventticketeasvbar.BE.BarDrinksTicket;
import dk.easv.eventticketeasvbar.BLL.Manager.TicketManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketModel {
    private final TicketManager ticketManager;

    public TicketModel() {
        ticketManager = new TicketManager();

    }

    public void saveEventTicket(String eventCode, String photo, String section, String row, String seat) throws SQLException, IOException {
        EventTicket eventTicket = new EventTicket(eventCode, photo, section, row, seat);
        ticketManager.saveTicket(eventTicket);
    }


    public void saveBarDrinksTicket(String eventCode, String qrCode, double discount) throws SQLException, IOException {
        BarDrinksTicket barDrinksTicket = new BarDrinksTicket(eventCode, qrCode, discount);
        ticketManager.saveTicket(barDrinksTicket);
    }

    public List<BaseTicket> getAllTickets() throws SQLException, IOException {
        return ticketManager.getAllTickets();
    }

    public void updateEventTicket(EventTicket ticket) throws SQLException, IOException {
        ticketManager.updateTicket(ticket);
    }

    public void updateBarDrinksTicket(BarDrinksTicket ticket) throws SQLException, IOException {
        ticketManager.updateTicket(ticket);
    }

    public void deleteTicket(String qrCode) throws SQLException, IOException {
        ticketManager.deleteTicket(qrCode);
    }


    public void saveEventTicket() {
    }

    public void updateTicketPDFPath(String qrCode, String pdfPath) throws SQLException, IOException {
        ticketManager.updateTicketPDFPath(qrCode, pdfPath);
    }

}
