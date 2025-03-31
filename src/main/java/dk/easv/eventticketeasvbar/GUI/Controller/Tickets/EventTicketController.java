package dk.easv.eventticketeasvbar.GUI.Controller.Tickets;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.GUI.Model.TicketModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class EventTicketController implements Initializable {

    @FXML
    private Label lblEventCode;
    @FXML
    private Label lblSection;
    @FXML
    private Label lblRow;
    @FXML
    private Label lblSeat;
    @FXML
    private Label lblPrice;
    @FXML
    private ImageView QRCode;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblDate;

    private final TicketModel ticketModel;
    private final Random random;

    public EventTicketController() {
        this.ticketModel = new TicketModel();
        this.random = new Random();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int randomSection = random.nextInt(20) + 1;
        int randomRow = random.nextInt(20) + 1;
        int randomSeat = random.nextInt(20) + 1;

        lblSection.setText(String.valueOf(randomSection));
        lblRow.setText(String.valueOf(randomRow));
        lblSeat.setText(String.valueOf(randomSeat));
    }

    @FXML
    private void btnPDF(ActionEvent actionEvent) {
        try {
            // Generate ticket details
            String eventCode = lblEventCode.getText();
            String section = lblSection.getText();
            String row = lblRow.getText();
            String seat = lblSeat.getText();
            String qrCode = "QR_" + eventCode + "_" + section + "_" + row + "_" + seat;

            // **1. Save ticket to database**
            ticketModel.saveEventTicket(eventCode, qrCode, section, row, seat);
            System.out.println("Ticket saved to database!");

            // **2. Generate PDF**
            String folderPath = "src/main/resources/dk/easv/eventticketeasvbar/Ticket";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = "ticket_" + eventCode + ".pdf";
            String filePath = folderPath + "/" + fileName;

            Document document = new Document(PageSize.A4, 36, 36, 36, 36);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // **Add Ticket Info to PDF**
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph(lblEventName.getText(), titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Table headers
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            PdfPCell header1 = new PdfPCell(new Paragraph("Field", headerFont));
            PdfPCell header2 = new PdfPCell(new Paragraph("Value", headerFont));
            table.addCell(header1);
            table.addCell(header2);

            // Ticket details
            table.addCell("Event Name:");
            table.addCell(lblEventName.getText());
            table.addCell("Location:");
            table.addCell(lblLocation.getText());
            table.addCell("Date:");
            table.addCell(lblDate.getText());
            table.addCell("Section:");
            table.addCell(lblSection.getText());
            table.addCell("Row:");
            table.addCell(lblRow.getText());
            table.addCell("Seat:");
            table.addCell(lblSeat.getText());
            table.addCell("Price:");
            table.addCell(lblPrice.getText());

            document.add(table);

            // Footer
            Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
            Paragraph footer = new Paragraph("Thank you for purchasing your ticket!", footerFont);
            footer.setAlignment(Paragraph.ALIGN_CENTER);
            footer.setSpacingBefore(20);
            document.add(footer);

            document.close();

            System.out.println("PDF Created: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEvent(Event event) {
        lblEventName.setText(event.getEvent());
        lblLocation.setText(event.getLocation());
        lblDate.setText(String.valueOf(event.getDate()));
        lblPrice.setText(String.valueOf(event.getPrice()));
        lblEventCode.setText(event.getId() + event.getEvent());
    }
}
