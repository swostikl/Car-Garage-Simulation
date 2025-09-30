package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import simu.model.Customer;
import simu.model.ServicePoint;

/**
 * Controller class for visualizing the state of various service points in a
 * simulation. Updates JavaFX UI elements such as labels and rectangles to
 * reflect the current queue lengths and customers being served.
 *
 * <p>This class should be used with a corresponding FXML file that defines
 * the UI layout, including labels and rectangles for queues and services.</p>
 */

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

    /**
     * Updates the label displaying the number of customers in the arrival queue.
     *
     * @param servicepoint the service point representing the arrival queue
     */

    public void setArrivalLabel(ServicePoint servicepoint) {
        Platform.runLater(() -> {
            arrivalLabel.setText(String.format("Queue: %d", servicepoint.onQueue()));
        });
    }


    /**
     * Updates the label displaying the number of customers waiting for maintenance.
     *
     * @param servicepoint the maintenance service point
     */
    public void setMaintenanceQueuelabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            maintenanceQueuelabel.setText(String.format("Queue maintenance : %d", servicepoint.onQueue()));
        });

    }
    /**
     * Updates the label displaying the number of customers waiting for inspection.
     *
     * @param servicepoint the inspection service point
     */

    public void setInspectionQueuelabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            inspectionQueuelabel.setText(String.format("inspection queue : %d", servicepoint.onQueue()));
        });
    }

    /**
     * Updates the label displaying the number of customers waiting for tire changes.
     *
     * @param servicepoint the tire change service point
     */
    public void setTireChangeQueuelabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            tireChangeQueuelabel.setText(String.format("TireChange queue : %d", servicepoint.onQueue()));
        });

    }

    /**
     * Updates the label displaying the number of customers waiting for oil changes.
     *
     * @param servicepoint the oil change service point
     */
    public void setOilChangeQueuelabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            oilChangeQueuelabel.setText(String.format("OilChange queue : %d", servicepoint.onQueue()));
        });
    }

    /**
     * Updates the label displaying the number of customers waiting for repair work.
     *
     * @param servicepoint the repair work service point
     */

    public void setRepairworkQueueLabel(ServicePoint servicepoint){
        Platform.runLater(()->{
            repairworkQueueLabel.setText(String.format("RepairWork queue : %d", servicepoint.onQueue()));
        });
    }


}
