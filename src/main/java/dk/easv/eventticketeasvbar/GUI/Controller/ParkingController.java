package dk.easv.eventticketeasvbar.GUI.Controller;

import dk.easv.eventticketeasvbar.BE.Event;
import dk.easv.eventticketeasvbar.BE.Parking;
import dk.easv.eventticketeasvbar.GUI.Model.ParkingModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ParkingController {

    @FXML
    private ImageView parkingImage;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblPostalCode;
    @FXML
    private Label lblCity;

    private ParkingModel parkingModel;

    public ParkingController() throws Exception {
        parkingModel = new ParkingModel();
    }

    public void setParkingInfo(Event event) throws Exception {
        Parking parking = parkingModel.getParkingForEvent(event.getId());

        if (parking != null) {
            lblAddress.setText(parking.getAddress());
            lblPostalCode.setText(String.valueOf(parking.getPostalCode()));
            lblCity.setText(parking.getCity());
        } else {
            lblAddress.setText("No Parking Info");
            lblPostalCode.setText("-");
            lblCity.setText("-");
        }
    }

    @FXML
    private void btnClose() {
        lblAddress.getScene().getWindow().hide();
    }
}
