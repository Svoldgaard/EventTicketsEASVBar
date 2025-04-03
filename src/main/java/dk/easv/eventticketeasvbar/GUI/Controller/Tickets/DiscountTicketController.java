package dk.easv.eventticketeasvbar.GUI.Controller.Tickets;
// project import
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import dk.easv.eventticketeasvbar.BE.Event;

// JavaFX imports
import dk.easv.eventticketeasvbar.GUI.Model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// Java imports
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

// iText imports for PDF generation
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


public class DiscountTicketController implements Initializable {


    public Stage stage;

    @FXML
    private MFXTextField txtAmountOffDiscount;
    @FXML
    private Label lblBarName;

    private TicketModel ticketModel;


    public DiscountTicketController() {
        if (stage != null) {
            stage.close();
        }

        ticketModel = new TicketModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtAmountOffDiscount.selectableProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    @FXML
    private void btnPDF(ActionEvent actionEvent) throws IOException {
        String filePath = null;
        try {
            // Get values from UI
            String barName = lblBarName.getText();
            String discount = txtAmountOffDiscount.getText();
            String qrCode = "QR_" + System.currentTimeMillis(); // Generate unique QR code
            int drinkCount = Integer.parseInt(discount); // Assuming discount is an integer

            // **1. Save ticket using TicketModel (Database Layer)**
            ticketModel.saveBarDrinksTicket(barName, qrCode, drinkCount);
            System.out.println("Ticket saved to database!");

            // **2. Generate PDF**
            String folderPath = "src/main/resources/dk/easv/eventticketeasvbar/Ticket";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = "Discount_Ticket_" + barName.replace(" ", "_") + ".pdf";
            filePath = folderPath + "/" + fileName;

            // Create PDF matching the FXML dimensions
            Document document = new Document(new Rectangle(319, 206));
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();


            // Create a table layout with two columns
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 1}); // Adjust column width ratio

            // Add title and discount text to the left side
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph title = new Paragraph(barName + "\nDiscount: " + discount + "%", titleFont);
            PdfPCell textCell = new PdfPCell();
            textCell.addElement(title);
            textCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(textCell);

            // Add QR Code Image to the right side
            String qrCodePath = "src/main/resources/dk.easv/eventticketeasvbar/QRCode/ticketQRCode.png";
            File qrCodeFile = new File(qrCodePath);
            PdfPCell qrCell = new PdfPCell();
            if (qrCodeFile.exists()) {
                Image qrImage = Image.getInstance(qrCodeFile.getAbsolutePath());
                qrImage.scaleToFit(100, 100); // Adjust size as needed
                qrCell.addElement(qrImage);
            } else {
                System.err.println("QR Code image not found at: " + qrCodePath);
            }
            qrCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(qrCell);

            // Add the table to the document
            document.add(table);

            // Add footer
            Font footerFont = new Font(Font.FontFamily.HELVETICA, 6, Font.ITALIC);
            Paragraph footer = new Paragraph("Can be used on all drinks in this bar", footerFont);
            footer.setAlignment(Paragraph.ALIGN_CENTER);
            footer.setSpacingBefore(20);
            document.add(footer);

            document.close();
            System.out.println("PDF Created at: " + filePath);

            // **3. Update ticket with PDF path in Database**
            ticketModel.updateTicketPDFPath(qrCode, filePath);

            // Open the PDF file automatically
            assert filePath != null;
            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop is not supported. Cannot open the file.");
                }
            } else {
                System.err.println("PDF file not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setEvent(Event event) {
        lblBarName.setText(new StringBuilder().append(event.getEvent()).append(" Launge").toString());


    }
}


