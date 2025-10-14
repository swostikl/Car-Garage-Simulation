package simu.model;

import simu.controller.VisualizeController;
import simu.listener.VisualizeControllerListener;
import simu.model.servicePoints.ServicePointTypes;

import java.util.Map;

public class GuiUpdateThread extends Thread {

    private final VisualizeController vc;
    private final Map<ServicePointTypes, ServicePoint> servicePoints;

    public GuiUpdateThread(Map<ServicePointTypes, ServicePoint> servicePoints) {
        this.vc = VisualizeControllerListener.getInstance();
        this.servicePoints = servicePoints;
    }

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
