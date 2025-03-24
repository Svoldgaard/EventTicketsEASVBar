package dk.easv.eventticketeasvbar.GUI.Controller;

// project import
import dk.easv.eventticketeasvbar.BE.Event;

// JavaFX imports
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

// Java imports
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

// iText imports for PDF generation
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

public class TicketController implements Initializable {

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

    public Stage stage;

    private Event event;

    private Random random;

    public TicketController() {
        if (stage != null) {
            stage.close();
        }

        event = new Event();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String imagePath = "/dk.easv/eventticketeasvbar/QRCode/ticketQRCode.png";
        Image image = new Image(imagePath);
        QRCode.setImage(image);

        random = new Random();

        int randomSection = random.nextInt(20) +1;
        int randomRow = random.nextInt(20) +1;
        int randomSeat = random.nextInt(20) +1;
        int randomEventCode1 = random.nextInt(20) +1;
        int randomEventCode2 = random.nextInt(20) +1;

        lblSection.setText(String.valueOf(randomSection));
        lblRow.setText(String.valueOf(randomRow));
        lblSeat.setText(String.valueOf(randomSeat));
        //lblEventCode.setText(new StringBuilder().append(randomEventCode1).append("Easv").append(randomEventCode2).toString());
    }

    @FXML
    private void btnPDF(ActionEvent actionEvent) {
        try {
            // Define the folder and file path
            String folderPath = "src/main/resources/dk/easv/eventticketeasvbar/Ticket";
            File folder = new File(folderPath);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = "ticket_" + lblEventCode.getText() + ".pdf";
            String filePath = folderPath + "/" + fileName;

            // Create a new PDF document with margins
            Document document = new Document(com.itextpdf.text.PageSize.A4, 36, 36, 36, 36);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Add a logo
            // Add logo (if available)
            com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src/main/resources/dk.easv/eventticketeasvbar/Photos/BarFight.png");
            logo.scaleToFit(100, 100);
            logo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(logo);


            // Add a title
            com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
            Paragraph title = new Paragraph(lblEventName.getText(), titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Add a table for ticket details
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);
            PdfPCell headerCell1 = new PdfPCell(new Paragraph("Field", headerFont));
            headerCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(headerCell1);
            PdfPCell headerCell2 = new PdfPCell(new Paragraph("Value", headerFont));
            headerCell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(headerCell2);

            // Add ticket details
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

            // Add QR Code (if available)
            com.itextpdf.text.Image qrCode = com.itextpdf.text.Image.getInstance("src/main/resources/dk.easv/eventticketeasvbar/QRCode/ticketQRCode.png");
            qrCode.scaleToFit(100, 100);
            qrCode.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(qrCode);


            // Add a footer
            com.itextpdf.text.Font footerFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.ITALIC);
            Paragraph footer = new Paragraph("Thank you for purchasing your ticket with us!", footerFont);
            footer.setAlignment(Paragraph.ALIGN_CENTER);
            footer.setSpacingBefore(20);
            document.add(footer);

            // Close the document
            document.close();
            System.out.println("PDF Created and saved to: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void btnEmail(ActionEvent actionEvent) {
        String to = "recipient@example.com"; // Replace with recipient's email
        String from = "Jessvo01@easv365.dk"; // Replace with your email
        String host = "smtp.office365.com"; // Replace with your SMTP host

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Ticket PDF");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find your ticket attached.");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = (DataSource) new FileDataSource("ticket.pdf");
            attachmentPart.setDataHandler(new DataHandler((javax.activation.DataSource) source));
            attachmentPart.setFileName("ticket.pdf");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email Sent Successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setEvent(Event event) {
        lblEventName.setText(event.getEvent());
        lblLocation.setText(event.getLocation());
        lblDate.setText(String.valueOf(event.getDate()));
        lblPrice.setText(String.valueOf(event.getPrice()));
        lblEventCode.setText((event.getId() + event.getEvent()));

    }
}
