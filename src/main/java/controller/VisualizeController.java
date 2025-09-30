package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import simu.model.Customer;
import simu.model.ServicePoint;



public class VisualizeController {

    @FXML
    private Label arrivalLabel;

    @FXML
    private Rectangle arrivalQueue;

    @FXML
    private Rectangle customerServed;

    @FXML
    private Label customerServedlabel;

    @FXML
    private Group customerService;

    @FXML
    private Label customerServicelabel;

    @FXML
    private Group inspectionQueue;

    @FXML
    private Label inspectionQueuelabel;

    @FXML
    private Rectangle inspectionService;

    @FXML
    private Label inspectionServicelabel;

    @FXML
    private Group maintenanceQueue;

    @FXML
    private Label maintenanceQueuelabel;

    @FXML
    private Rectangle maintenanceService;

    @FXML
    private Label maintenancelabel;

    @FXML
    private Group oilChangeQueue;

    @FXML
    private Label oilChangeQueuelabel;

    @FXML
    private Rectangle oilChangeService;

    @FXML
    private Label oilChangeServicelabel;

    @FXML
    private Group repairWorkQueue;

    @FXML
    private Rectangle repairWorkService;

    @FXML
    private Label repairWorklabel;

    @FXML
    private Label repairworkQueueLabel;

    @FXML
    private Group tireChangeQueue;

    @FXML
    private Label tireChangeQueuelabel;

    @FXML
    private Rectangle tireChangeService;

    @FXML
    private Label tireChangeServicelabel;

    /**
     * This does this does that
     * @param servicepoint this is the service point bla blah blah
     */


    public void setArrivalLabel(ServicePoint servicepoint) {
        Platform.runLater(() -> {
            arrivalLabel.setText(String.format("Queue: %d", servicepoint.onQueue()));
        });
    }

// think how to get id from servicepoint
    public void setCustomerServicelabel(ServicePoint servicepoint) {
        Platform.runLater(() -> {
            Customer c = servicepoint.getCurrentCustomer();
        if (c != null) {
            customerServicelabel.setText("Serving customer #" + c.getId());
        } else {
            customerServicelabel.setText("No customer currently served");
        }
    });
}


    public void setMaintenanceQueuelabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            maintenanceQueuelabel.setText(String.format("Queue maintenance : %d", servicepoint.onQueue()));
        });

    }

    public void setInspectionQueuelabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            inspectionQueuelabel.setText(String.format("inspection queue : %d", servicepoint.onQueue()));
        });
    }

    public void setTireChangeQueuelabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            tireChangeQueuelabel.setText(String.format("TireChange queue : %d", servicepoint.onQueue()));
        });

    }

    public void setOilChangeQueuelabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            oilChangeQueuelabel.setText(String.format("OilChange queue : %d", servicepoint.onQueue()));
        });
    }

    public void setRepairworkQueueLabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            repairworkQueueLabel.setText(String.format("RepairWork queue : %d", servicepoint.onQueue()));
        });
    }


}
