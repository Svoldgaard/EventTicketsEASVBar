package dk.easv.eventticketeasvbar.BLL.Manager;

import dk.easv.eventticketeasvbar.BE.BaseTicket;
import dk.easv.eventticketeasvbar.DLL.Database.TicketDAO_DB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketManager {
    private TicketDAO_DB ticketDAO;

    public TicketManager() {
        ticketDAO = new TicketDAO_DB();
    }

    public void saveTicket(BaseTicket ticket) throws SQLException, IOException {
        ticketDAO.saveTicket(ticket);
    }

    public List<BaseTicket> getAllTickets() throws SQLException, IOException {
        return ticketDAO.getAllTickets();
    }

    public void updateTicket(BaseTicket ticket) throws SQLException, IOException {
        ticketDAO.updateTicket(ticket);
    }

    public void deleteTicket(String qrCode) throws SQLException, IOException {
        ticketDAO.deleteTicket(qrCode);
    }

    public void updateTicketPDFPath(String qrCode, String pdfPath) throws SQLException, IOException {
        ticketDAO.updateTicketPDFPath(qrCode, pdfPath);
    }



}
