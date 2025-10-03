package simu.model;

import controller.VisualizeController;

public class GuiUpdateThread extends Thread {

    private VisualizeController vc;
    private ServicePoint[] servicePoints;

    public GuiUpdateThread(VisualizeController vc, ServicePoint[] servicePoints) {
        this.vc = vc;
        this.servicePoints = servicePoints;
    }

    @Override
    public void run() {
        vc.setArrivalLabel(servicePoints[0]);          // Customer Service queue
        vc.setMaintenanceQueuelabel(servicePoints[1]); // Maintenance queue
        vc.setTireChangeQueuelabel(servicePoints[2]);  // Tire Change queue
        vc.setOilChangeQueuelabel(servicePoints[3]);   // Oil Change queue
        vc.setRepairworkQueueLabel(servicePoints[4]);  // Other Repairs queue
        vc.setInspectionQueuelabel(servicePoints[5]);  // Inspection queue

        // Service labels
        vc.setCustomerServicelabel(servicePoints[0]);
        vc.setMaintenancelabel(servicePoints[1]);
        vc.setTireChangeServicelabel(servicePoints[2]);
        vc.setOilChangeServicelabel(servicePoints[3]);
        vc.setRepairWorklabel(servicePoints[4]);
        vc.setInspectionServicelabel(servicePoints[5]);


        // total served
        vc.setCustomerServedlabel(Customer.getTotalServed());

        // Occupied / Free colors
        vc.setCustomerServiceOccupied(servicePoints[0].isReserved());
        vc.setMaintenanceServiceOccupied(servicePoints[1].isReserved());
        vc.setTireChangeServiceOccupied(servicePoints[2].isReserved());
        vc.setOilChangeServiceOccupied(servicePoints[3].isReserved());
        vc.setRepairWorkServiceOccupied(servicePoints[4].isReserved());
        vc.setInspectionServiceOccupied(servicePoints[5].isReserved());

    }
}
