package simu.listener;

import simu.controller.VisualizeController;
import simu.model.ServicePoint;

/**
 * Singleton listener that acts as a proxy to update the {@link VisualizeController}
 */
public class VisualizeControllerListener extends VisualizeController {

    private VisualizeController vc;

    private static VisualizeControllerListener instance;

    private VisualizeControllerListener() {}

    /**
     * Initializes the listener with the actual {@link VisualizeController} instance
     * @param vc the controller to delegate GUI updates to
     */
    public void init(VisualizeController vc) {
        this.vc = null;
    } // set as null for testing purposes

    /**
     * Returns the singleton instance of this listener
     * @return the {@code VisualizeControllerListener} instance
     */
    public static VisualizeControllerListener getInstance() {
        if (instance == null) {
            instance = new VisualizeControllerListener();
        }
        return instance;
    }

    /**
     * Updates the label displaying the number of customers in the arrival queue.
     *
     * @param servicepoint the service point representing the arrival queue
     */
    @Override
    public void setArrivalLabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setArrivalLabel(servicepoint);
    }

    /**
     * Updates the label displaying the number of customers waiting for maintenance.
     *
     * @param servicepoint the maintenance service point
     */
    @Override
    public void setMaintenanceQueuelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setMaintenanceQueuelabel(servicepoint);
    }

    /**
     * Updates the label displaying the number of customers waiting for inspection.
     *
     * @param servicepoint the inspection service point
     */
    @Override
    public void setInspectionQueuelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setInspectionQueuelabel(servicepoint);
    }

    /**
     * Updates the label displaying the number of customers waiting for tire changes.
     *
     * @param servicepoint the tire change service point
     */
    @Override
    public void setTireChangeQueuelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setTireChangeQueuelabel(servicepoint);
    }

    /**
     * Updates the label displaying the number of customers waiting for oil changes.
     *
     * @param servicepoint the oil change service point
     */
    @Override
    public void setOilChangeQueuelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setOilChangeQueuelabel(servicepoint);
    }

    /**
     * Updates the label displaying the number of customers waiting for repair work.
     *
     * @param servicepoint the repair work service point
     */
    @Override
    public void setRepairworkQueueLabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setRepairworkQueueLabel(servicepoint);
    }

    @Override
    public void setCustomerServicelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setCustomerServicelabel(servicepoint);
    }

    @Override
    public void setMaintenancelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setMaintenancelabel(servicepoint);
    }

    @Override
    public void setTireChangeServicelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setTireChangeServicelabel(servicepoint);
    }

    @Override
    public void setOilChangeServicelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setOilChangeServicelabel(servicepoint);
    }

    @Override
    public void setRepairWorklabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setRepairWorklabel(servicepoint);
    }

    @Override
    public void setInspectionServicelabel(ServicePoint servicepoint) {
        if (vc != null)
            vc.setInspectionServicelabel(servicepoint);
    }

    @Override
    public void setCustomerServedlabel(int i) {
        if (vc != null)
            vc.setCustomerServedlabel(i);
    }

    @Override
    public void setCustomerServiceOccupied(boolean occupied) {
        if (vc != null)
            vc.setCustomerServiceOccupied(occupied);
    }

    @Override
    public void setMaintenanceServiceOccupied(boolean occupied) {
        if (vc != null)
            vc.setMaintenanceServiceOccupied(occupied);
    }

    @Override
    public void setTireChangeServiceOccupied(boolean occupied) {
        if (vc != null)
            vc.setTireChangeServiceOccupied(occupied);
    }

    @Override
    public void setOilChangeServiceOccupied(boolean occupied) {
        if (vc != null)
            vc.setOilChangeServiceOccupied(occupied);
    }

    @Override
    public void setRepairWorkServiceOccupied(boolean occupied) {
        if (vc != null)
            vc.setRepairWorkServiceOccupied(occupied);
    }

    @Override
    public void setInspectionServiceOccupied(boolean occupied) {
        if (vc != null)
            vc.setInspectionServiceOccupied(occupied);
    }
}
