package simu.model;

import simu.controller.VisualizeController;
import simu.model.servicePoints.ServicePointTypes;

import java.util.Map;

/**
 * Thread responsible for updating the graphical user interface (GUI) with the current state of all service points in the simulation.
 */

public class GuiUpdateThread extends Thread {

    private final VisualizeController vc;
    private final Map<ServicePointTypes, ServicePoint> servicePoints;

    /**
     *  Constructs a new {@code GuiUpdateThread} with the given controller and service points.
     * @param vc the visualization controller responsible for updating the GUI.
     * @param servicePoints a mapping of service points to their corresponding {@link ServicePoint} instances
     */
    public GuiUpdateThread(VisualizeController vc, Map<ServicePointTypes, ServicePoint> servicePoints) {
        this.vc = vc;
        this.servicePoints = servicePoints;
    }

    /**
     * Executes the GUI update routine
     * <p>
     * This method updates the queue labels, service status labels, total number of customers served, and occupancy
     * indicators for each service point type. It should be run in a separate thread to keep the user interface
     * responsive during simulation updates.
     * </p>
     *
     */
    @Override
    public void run() {
        vc.setArrivalLabel(servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE));          // Customer Service queue
        vc.setMaintenanceQueuelabel(servicePoints.get(ServicePointTypes.MAINTENANCE)); // Maintenance queue
        vc.setTireChangeQueuelabel(servicePoints.get(ServicePointTypes.TIRE_CHANGE));  // Tire Change queue
        vc.setOilChangeQueuelabel(servicePoints.get(ServicePointTypes.OIL_CHANGE));   // Oil Change queue
        vc.setRepairworkQueueLabel(servicePoints.get(ServicePointTypes.OTHER_REPAIRS));  // Other Repairs queue
        vc.setInspectionQueuelabel(servicePoints.get(ServicePointTypes.INSPECTION));  // Inspection queue

        // Service labels
        vc.setCustomerServicelabel(servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE));
        vc.setMaintenancelabel(servicePoints.get(ServicePointTypes.MAINTENANCE));
        vc.setTireChangeServicelabel(servicePoints.get(ServicePointTypes.TIRE_CHANGE));
        vc.setOilChangeServicelabel(servicePoints.get(ServicePointTypes.OIL_CHANGE));
        vc.setRepairWorklabel(servicePoints.get(ServicePointTypes.OTHER_REPAIRS));
        vc.setInspectionServicelabel(servicePoints.get(ServicePointTypes.INSPECTION));


        // total served
        vc.setCustomerServedlabel(Customer.getTotalServed());

        // Occupied / Free colors
        vc.setCustomerServiceOccupied(servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE).isReserved());
        vc.setMaintenanceServiceOccupied(servicePoints.get(ServicePointTypes.MAINTENANCE).isReserved());
        vc.setTireChangeServiceOccupied(servicePoints.get(ServicePointTypes.TIRE_CHANGE).isReserved());
        vc.setOilChangeServiceOccupied(servicePoints.get(ServicePointTypes.OIL_CHANGE).isReserved());
        vc.setRepairWorkServiceOccupied(servicePoints.get(ServicePointTypes.OTHER_REPAIRS).isReserved());
        vc.setInspectionServiceOccupied(servicePoints.get(ServicePointTypes.INSPECTION).isReserved());

    }
}
