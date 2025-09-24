package simu.model.bEvent;

import eduni.distributions.ContinuousGenerator;
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
        // The customer class is pretty well documented, have a look at the Customer.java file, see what it does

        /* There are six service points
           0. Customer service
           1. Maintenance
           2. Tire Change
           3. Oil Change
           4. Other repairs
           5. Inspection

           Each service points are in the array servicePoints[], in this exact order
         */


//        Customer a;
//
//		switch ((EventType)event.getType()) {
////      Example:
////
////		case ARR1:
////			servicePoints[0].addQueue(new Customer());
////			arrivalProcess.generateNextEvent();
////			break;
////
////		case DEP1:
////			a = servicePoints[0].removeQueue();
////			servicePoints[1].addQueue(a);
////			break;
////
////		case DEP2:
////			a = servicePoints[1].removeQueue();
////			servicePoints[2].addQueue(a);
////			break;
////
////		case DEP3:
////			a = servicePoints[2].removeQueue();
////			a.setRemovalTime(Clock.getInstance().getClock());
////		    a.reportResults();
////			break;
//
//            // Example
//            case ARR_CUSTOMER_SERVICE:
//                servicePoints[0].addQueue(new Customer(generator, percentage, percentage));
//                break;
//		}
        test(servicePoints, arrivalProcess, event);
    }

    static private void test(
            ServicePoint[] servicePoints,
            ArrivalProcess arrivalProcess,
            Event event
    ) {
        Customer a;

        switch ((EventType) event.getType()) {
            case ARR_CUSTOMER_SERVICE:
                servicePoints[0].addQueue(new Customer(new ContinuousGenerator() {
                    @Override
                    public double sample() {
                        return 10;
                    }

                    @Override
                    public void setSeed(long seed) {

                    }

                    @Override
                    public long getSeed() {
                        return 0;
                    }

                    @Override
                    public void reseed() {

                    }
                }, 1, 1));
                arrivalProcess.generateNextEvent();
                break;

                /* There are six service points
           0. Customer service
           1. Maintenance
           2. Tire Change
           3. Oil Change
           4. Other repairs
           5. Inspection

           Each service points are in the array servicePoints[], in this exact order
         */
            case DEP_CS_INSPECTION:
                a = servicePoints[0].removeQueue();
                servicePoints[5].addQueue(a);
                break;
            case DEP_INSPECTION_MAINTENANCE:
                a = servicePoints[5].removeQueue();
                servicePoints[1].addQueue(a);
                break;
            case DEP_MAINTENANCE_OIL:
                a = servicePoints[1].removeQueue();
                servicePoints[3].addQueue(a);
                break;
            case DEP_MAINTENANCE_OTHER:
                a = servicePoints[1].removeQueue();
                servicePoints[4].addQueue(a);
                break;
            case DEP_MAINTENANCE_TIRE:
                a = servicePoints[1].removeQueue();
                servicePoints[2].addQueue(a);
                break;
            case DEP_OIL_INSPECTION:
                a = servicePoints[3].removeQueue();
                servicePoints[5].addQueue(a);
                break;
            case DEP_TIRE_INSPECTION:
                a = servicePoints[2].removeQueue();
                servicePoints[5].addQueue(a);
                break;
            case DEP_OTHER_INSPECTION:
                a = servicePoints[3].removeQueue();
                servicePoints[5].addQueue(a);
                break;
            case DEP_INSPECTION_END:
                a = servicePoints[5].removeQueue();
                a.setRemovalTime(Clock.getInstance().getClock());
                a.reportResults();
                break;
        }
    }
}
