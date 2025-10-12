package simu.model.servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.EventList;
import simu.framework.Trace;
import simu.model.Customer;
import simu.model.EventType;
import simu.model.ServicePoint;

/**
 * Car inspection service point
 */
public class InspectionServicePoint extends ServicePoint {

    /**
     * Create the InspectionServicePoint service point with a waiting queue.
     *
     * @param generator Random number generator for service time simulation
     * @param eventList Simulator event list, needed for the insertion of service ready event
     */
    public InspectionServicePoint(ContinuousGenerator generator, EventList eventList) {
        super(generator, eventList);
    }

    @Override
    public void beginService() {
        Customer nextCustomer = getQueue().peek();
        Trace.out(Trace.Level.INFO, "Starting a new service at inspection for the customer #" + nextCustomer.getId());
        this.eventTypeScheduled = nextCustomer.hasPassedInspection() ? EventType.DEP_INSPECTION_END : EventType.DEP_INSPECTION_MAINTENANCE;
        reserved = true;
        double serviceTime = generator.sample();
        eventList.add(new Event(eventTypeScheduled, Clock.getInstance().getClock() + serviceTime));
        this.serviceTime += serviceTime;
        currentCustomer = nextCustomer;
    }
}
