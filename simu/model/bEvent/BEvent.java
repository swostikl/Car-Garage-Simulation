package simu.model.bEvent;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Generator;
import eduni.distributions.Normal;
import simu.framework.ArrivalProcess;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.IEventType;
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

        // TODO: Replace this code with your own work, use this as a template

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
//      Example:
//
//		case ARR1:
//			servicePoints[0].addQueue(new Customer());
//			arrivalProcess.generateNextEvent();
//			break;
//
//		case DEP1:
//			a = servicePoints[0].removeQueue();
//			servicePoints[1].addQueue(a);
//			break;
//
//		case DEP2:
//			a = servicePoints[1].removeQueue();
//			servicePoints[2].addQueue(a);
//			break;
//
//		case DEP3:
//			a = servicePoints[2].removeQueue();
//			a.setRemovalTime(Clock.getInstance().getClock());
//		    a.reportResults();
//			break;

            // Example
            case ARR_CUSTOMER_SERVICE:
                ContinuousGenerator maintenanceGenerator = new Normal(2, 1);
                a = new Customer(maintenanceGenerator, 0.6, 0.1);
                servicePoints[0].addQueue(a);
				arrivalProcess.generateNextEvent();

                if (!servicePoints[0].isOnQueue()){
                    servicePoints[0].beginService();
                }
                break;

            case DEP_CS_MAINTENANCE:
                a = servicePoints[1].removeQueue();
                servicePoints[2].addQueue(a);
                if (!servicePoints[2].isOnQueue()){
                    servicePoints[2].beginService();
                }
                break;
            case DEP_CS_INSPECTION:
                a = servicePoints[0].removeQueue();
                servicePoints[5].addQueue(a);
                if (!servicePoints[5].isOnQueue()){
                    servicePoints[5].beginService();
                }
                break;
            case DEP_INSPECTION_MAINTENANCE:
                a = servicePoints[5].removeQueue();
                if(a.hasPassedInspection()) {
                    servicePoints[1].addQueue(a);
                    if (!servicePoints[1].isOnQueue()){
                        servicePoints[1].beginService();
                    }
                } else {
                    servicePoints[1].addQueue(a);
                    if (!servicePoints[1].isOnQueue()){
                        servicePoints[1].beginService();
                    }
                }
                break;
            case DEP_MAINTENANCE_TIRE:
                a = servicePoints[1].removeQueue();
                servicePoints[2].addQueue(a);
                if (!servicePoints[2].isOnQueue()){
                    servicePoints[2].beginService();
                }
                break;
            case DEP_MAINTENANCE_OIL:
                a = servicePoints[1].removeQueue();
                servicePoints[3].addQueue(a);
                if (!servicePoints[3].isOnQueue()){
                    servicePoints[3].beginService();
                }
                break;
            case DEP_MAINTENANCE_OTHER:
                a = servicePoints[1].removeQueue();
                servicePoints[3].addQueue(a);
                if (!servicePoints[3].isOnQueue()){
                    servicePoints[3].beginService();
                }
                break;
            case DEP_TIRE_INSPECTION:
                a = servicePoints[2].removeQueue();
                servicePoints[5].addQueue(a);
                if (!servicePoints[5].isOnQueue()){
                    servicePoints[5].beginService();
                }
                break;
            case DEP_OIL_INSPECTION:
                a = servicePoints[3].removeQueue();
                servicePoints[5].addQueue(a);
                if (!servicePoints[5].isOnQueue()){
                    servicePoints[5].beginService();
                }
                break;
            case DEP_OTHER_INSPECTION:
                a = servicePoints[4].removeQueue();
                servicePoints[5].addQueue(a);
                if (!servicePoints[5].isOnQueue()){
                    servicePoints[5].beginService();
                }
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
		}
    }
}
