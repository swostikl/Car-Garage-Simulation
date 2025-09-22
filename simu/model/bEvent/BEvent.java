package simu.model.bEvent;

import simu.framework.ArrivalProcess;
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
                servicePoints[0].addQueue(new Customer(generator, percentage, percentage));
				arrivalProcess.generateNextEvent();
                break;
		}
    }
}
