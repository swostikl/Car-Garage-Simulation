package simu.model.bEvent;

import eduni.distributions.ContinuousGenerator;
import simu.framework.ArrivalProcess;
import simu.framework.Clock;
import simu.framework.Event;
import simu.model.Customer;
import simu.model.EventType;
import simu.model.ServicePoint;
import simu.model.servicePoints.ServicePointTypes;

import java.util.Map;

/**
 * B event of the simulation
 */
public class BEvent {

    // write B event here

    /**
     * Execute B Event
     *
     * @param servicePoints        service point HashMap
     * @param arrivalProcess       arrival process
     * @param event                event to be run
     * @param maintenanceGenerator maintenance generator (Continuous Generator)
     * @param inspectionFailRate   inspection fail rate (0.0 - 1.0)
     */
    static public void runBEvent(
            // pass something here if needed
            Map<ServicePointTypes, ServicePoint> servicePoints,
            ArrivalProcess arrivalProcess,
            Event event,
            ContinuousGenerator maintenanceGenerator,
            double inspectionFailRate
    ) {
        // B event code here


        Customer a;

        switch ((EventType) event.getType()) {
            case ARR_CUSTOMER_SERVICE:
                a = new Customer(maintenanceGenerator, 0.6, inspectionFailRate);
                servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE).addQueue(a);
                arrivalProcess.generateNextEvent();
                break;
            case DEP_CS_MAINTENANCE:
                a = servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE).removeQueue();
                servicePoints.get(ServicePointTypes.MAINTENANCE).addQueue(a);
                break;
            case DEP_CS_INSPECTION:
                a = servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE).removeQueue();
                servicePoints.get(ServicePointTypes.INSPECTION).addQueue(a);
                break;
            case DEP_INSPECTION_MAINTENANCE:
                a = servicePoints.get(ServicePointTypes.INSPECTION).removeQueue();
                servicePoints.get(ServicePointTypes.MAINTENANCE).addQueue(a);
                break;

            case DEP_MAINTENANCE_TIRE:
                a = servicePoints.get(ServicePointTypes.MAINTENANCE).removeQueue();
                servicePoints.get(ServicePointTypes.TIRE_CHANGE).addQueue(a);
                break;
            case DEP_MAINTENANCE_OIL:
                a = servicePoints.get(ServicePointTypes.MAINTENANCE).removeQueue();
                servicePoints.get(ServicePointTypes.OIL_CHANGE).addQueue(a);
                break;
            case DEP_MAINTENANCE_OTHER:
                a = servicePoints.get(ServicePointTypes.MAINTENANCE).removeQueue();
                servicePoints.get(ServicePointTypes.OTHER_REPAIRS).addQueue(a);
                break;

            case DEP_TIRE_INSPECTION:
                a = servicePoints.get(ServicePointTypes.TIRE_CHANGE).removeQueue();
                servicePoints.get(ServicePointTypes.INSPECTION).addQueue(a);
                break;

            case DEP_OIL_INSPECTION:
                a = servicePoints.get(ServicePointTypes.OIL_CHANGE).removeQueue();
                servicePoints.get(ServicePointTypes.INSPECTION).addQueue(a);
                break;
            case DEP_OTHER_INSPECTION:
                a = servicePoints.get(ServicePointTypes.OTHER_REPAIRS).removeQueue();
                servicePoints.get(ServicePointTypes.INSPECTION).addQueue(a);
                break;
            case DEP_INSPECTION_END:
                a = servicePoints.get(ServicePointTypes.INSPECTION).removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;
            case DEP_TIRE_END:
                a = servicePoints.get(ServicePointTypes.TIRE_CHANGE).removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;
            case DEP_OIL_END:
                a = servicePoints.get(ServicePointTypes.OIL_CHANGE).removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;
            case DEP_OTHER_END:
                a = servicePoints.get(ServicePointTypes.OTHER_REPAIRS).removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;

            case DEP_OIL_MAINTENANCE:
                a = servicePoints.get(ServicePointTypes.OIL_CHANGE).removeQueue();
                servicePoints.get(ServicePointTypes.MAINTENANCE).addQueue(a);
                break;
            case DEP_TIRE_MAINTENANCE:
                a = servicePoints.get(ServicePointTypes.TIRE_CHANGE).removeQueue();
                servicePoints.get(ServicePointTypes.MAINTENANCE).addQueue(a);
                break;
            case DEP_OTHER_MAINTENANCE:
                a = servicePoints.get(ServicePointTypes.OTHER_REPAIRS).removeQueue();
                servicePoints.get(ServicePointTypes.MAINTENANCE).addQueue(a);
                break;
        }
    }
}
