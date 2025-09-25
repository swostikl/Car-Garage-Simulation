package simu.model.bEvent;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simu.framework.ArrivalProcess;
import simu.framework.Clock;
import simu.framework.Event;
import simu.model.Customer;
import simu.model.EventType;
import simu.model.ServicePoint;

/**
 * B event
 */
public class BEvent {
    public BEvent() {}

    // write B event here
    static public void runBEvent(
            // pass something here if needed
            ServicePoint[] servicePoints,
            ArrivalProcess arrivalProcess,
            Event event
    ) {
        // B event code here

        /* There are six service points
           0. Customer service
           1. Maintenance
           2. Tire Change
           3. Oil Change
           4. Other repairs
           5. Inspection

           Each service points are in the array servicePoints[], in this exact order
         */


        Customer a;

		switch ((EventType)event.getType()) {
            case ARR_CUSTOMER_SERVICE:
                ContinuousGenerator maintenanceGenerator = new Normal(2, 1);
                a = new Customer(maintenanceGenerator, 0.6, 0.1);
                servicePoints[0].addQueue(a);
				arrivalProcess.generateNextEvent();
                break;

            case DEP_CS_MAINTENANCE:
                a = servicePoints[0].removeQueue();
                servicePoints[1].addQueue(a);
                break;
            case DEP_CS_INSPECTION:
                a = servicePoints[0].removeQueue();
                servicePoints[5].addQueue(a);
                break;
            case DEP_INSPECTION_MAINTENANCE:
                a = servicePoints[5].removeQueue();
                servicePoints[1].addQueue(a);
                break;

            case DEP_MAINTENANCE_TIRE:
                a = servicePoints[1].removeQueue();
                servicePoints[2].addQueue(a);
                break;
            case DEP_MAINTENANCE_OIL:
                a = servicePoints[1].removeQueue();
                servicePoints[3].addQueue(a);
                break;
            case DEP_MAINTENANCE_OTHER:
                a = servicePoints[1].removeQueue();
                servicePoints[4].addQueue(a);
                break;

            case DEP_TIRE_INSPECTION:
                a = servicePoints[2].removeQueue();
                servicePoints[5].addQueue(a);
                break;

            case DEP_OIL_INSPECTION:
                a = servicePoints[3].removeQueue();
                servicePoints[5].addQueue(a);
                break;
            case DEP_OTHER_INSPECTION:
                a = servicePoints[4].removeQueue();
                servicePoints[5].addQueue(a);
                break;
            case DEP_INSPECTION_END:
                a = servicePoints[5].removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;


            case DEP_TIRE_END:
                a = servicePoints[2].removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;
            case DEP_OIL_END:
                a = servicePoints[3].removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;
            case DEP_OTHER_END:
                a = servicePoints[4].removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;

            case DEP_OIL_MAINTENANCE:
                a = servicePoints[3].removeQueue();
                servicePoints[1].addQueue(a);
                break;
            case DEP_TIRE_MAINTENANCE:
                a = servicePoints[2].removeQueue();
                servicePoints[1].addQueue(a);
                break;
            case DEP_OTHER_MAINTENANCE:
                a = servicePoints[4].removeQueue();
                servicePoints[1].addQueue(a);
                break;
        }
    }
}
