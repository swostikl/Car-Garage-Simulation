package simu.model.bEvent;

import simu.framework.ArrivalProcess;
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
            ArrivalProcess arrivalProcess
    ) {
        // B event code here

        // TODO: Replace this code with your own work, use this as a template
        // The customer class is pretty well documented, have a look at the Customer.java file, see what it does


        Customer a;

		switch ((EventType)t.getType()) {
		case ARR1:
			servicePoints[0].addQueue(new Customer());
			arrivalProcess.generateNextEvent();
			break;

		case DEP1:
			a = servicePoints[0].removeQueue();
			servicePoints[1].addQueue(a);
			break;

		case DEP2:
			a = servicePoints[1].removeQueue();
			servicePoints[2].addQueue(a);
			break;

		case DEP3:
			a = servicePoints[2].removeQueue();
			a.setRemovalTime(Clock.getInstance().getClock());
		    a.reportResults();
			break;
		}
    }
}
