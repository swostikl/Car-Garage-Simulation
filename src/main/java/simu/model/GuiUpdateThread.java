package simu.model;

import simu.controller.VisualizeController;
import simu.listener.VisualizeControllerListener;
import simu.model.servicePoints.ServicePointTypes;

import java.util.Map;


/**
 * A thread responsible for updating the GUI with the latest simulation data.
 * <p>
 * This thread retrieves information about various service points and updates
 * the corresponding labels and visual indicators in the GUI. It handles queue sizes,
 * service statuses, total customers served, and occupied/free status of service points.
 * </p>
 */
public class GuiUpdateThread extends Thread {

    private final VisualizeController vc;
    private final Map<ServicePointTypes, ServicePoint> servicePoints;

    /**
     *
     * @param servicePoints a map of {@link ServicePointTypes} to {@link ServicePoint} representing all service points in the simulation
     */
    public GuiUpdateThread(Map<ServicePointTypes, ServicePoint> servicePoints) {
        this.vc = VisualizeControllerListener.getInstance();
        this.servicePoints = servicePoints;
    }

    /**
     * Runs in the GUI update thread
     * <p>
     * This method is executed in a separate thread, allowing the GUI to refresh
     * concurrently with the simulation logic.
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
