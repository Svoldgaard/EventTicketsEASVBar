package dk.easv.eventticketeasvbar.GUI.Controller.Tickets;

import com.itextpdf.text.pdf.PdfPCell;
import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.GUI.Model.TicketModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;

import static dk.easv.eventticketeasvbar.BLL.Utility.QRCodeGenerator.generateQRCode;

public class EventTicketController implements Initializable {

    @FXML
    private Label lblEventCode, lblSection, lblRow, lblSeat, lblPrice, lblEventName, lblLocation, lblDate;
    @FXML
    private ImageView QRCode;

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
            // Retrieve ticket details
            String eventCode = lblEventCode.getText();
            String qrText = "QR_" + eventCode;

            // Save ticket to database
            ticketModel.saveEventTicket(eventCode, qrText, lblSection.getText(), lblRow.getText(), lblSeat.getText());

            // PDF generation setup
            String folderPath = "src/main/resources/dk/easv/eventticketeasvbar/Ticket";
            new File(folderPath).mkdirs();
            String filePath = folderPath + "/ticket_" + eventCode + ".pdf";
            Document document = new Document(new Rectangle(300, 230)); // Adjusted size
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 1});

            // Left column: Ticket details
            PdfPCell textCell = new PdfPCell();
            textCell.setBorder(Rectangle.NO_BORDER);
            textCell.addElement(new Paragraph(lblEventName.getText(), new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            textCell.addElement(new Paragraph("Location: " + lblLocation.getText()));
            textCell.addElement(new Paragraph("Date: " + lblDate.getText()));
            textCell.addElement(new Paragraph("Section: " + lblSection.getText()));
            textCell.addElement(new Paragraph("Row: " + lblRow.getText()));
            textCell.addElement(new Paragraph("Seat: " + lblSeat.getText()));
            textCell.addElement(new Paragraph("Price: " + lblPrice.getText()));
            table.addCell(textCell);

            // Right column: QR Code
            String qrCodePath = "/Users/majkensvoldgaard/Desktop/EventTicketEASVBar/src/main/resources/dk.easv/eventticketeasvbar/QRCode/ticketQRCode.png";
            File qrCodeFile = new File(qrCodePath);
            PdfPCell qrCell = new PdfPCell();
            if (qrCodeFile.exists()) {
                com.itextpdf.text.Image qrImage = Image.getInstance(qrCodeFile.getAbsolutePath());
                qrImage.scaleToFit(100, 100); // Adjust size as needed
                qrCell.addElement(qrImage);
            } else {
                System.err.println("QR Code image not found at: " + qrCodePath);
            }
            qrCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(qrCell);


            document.add(table);
            document.close();

            // Update ticket with PDF path
            ticketModel.updateTicketPDFPath(qrText, filePath);
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
