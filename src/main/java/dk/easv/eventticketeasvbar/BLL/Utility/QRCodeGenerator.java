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


    public static void generateQRCode(String data, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;


        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);


        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("QR Code generated and saved at: " + filePath);
    }

    public static void main(String[] args) {
        try {

            String qrCodePath = "src/main/resources/dk.easv/eventticketeasvbar/QRCode/ticketQRCode.png";


            Path path = FileSystems.getDefault().getPath("src/main/resources/dk.easv/eventticketeasvbar/QRCode");
            if (!path.toFile().exists()) {
                path.toFile().mkdirs();
            }


            String qrData = "https://www.easv.dk/event/ticket";


            generateQRCode(qrData, qrCodePath);
        } catch (WriterException | IOException e) {
            System.err.println("An error occurred while generating the QR code: " + e.getMessage());
        }
    }
}
