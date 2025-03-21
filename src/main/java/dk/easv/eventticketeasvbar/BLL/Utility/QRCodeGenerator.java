package dk.easv.eventticketeasvbar.BLL.Utility;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

    // Method to generate a QR code
    public static void generateQRCode(String data, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;  // Width of the QR code
        int height = 300; // Height of the QR code

        // Generate the QR code
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);

        // Save the QR code to the specified path
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("QR Code generated and saved at: " + filePath);
    }

    public static void main(String[] args) {
        try {
            // Path where the QR code will be saved
            String qrCodePath = "src/main/resources/dk.easv/eventticketeasvbar/QRCode/ticketQRCode.png";

            // Ensure the directory exists
            Path path = FileSystems.getDefault().getPath("src/main/resources/dk.easv/eventticketeasvbar/QRCode");
            if (!path.toFile().exists()) {
                path.toFile().mkdirs();
            }

            // Data to be encoded in the QR code
            String qrData = "https://www.easv.dk/event/ticket";

            // Generate and save the QR code
            generateQRCode(qrData, qrCodePath);
        } catch (WriterException | IOException e) {
            System.err.println("An error occurred while generating the QR code: " + e.getMessage());
        }
    }
}
