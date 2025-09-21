package simu.model.servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.EventList;
import simu.framework.Trace;
import simu.model.Customer;
import simu.model.EventType;
import simu.model.MaintenanceType;
import simu.model.ServicePoint;

public class MaintenanceServicePoint extends ServicePoint {

    /**
     * Create the Maintenance service point with a waiting queue.
     *
     * @param generator Random number generator for service time simulation
     * @param eventList Simulator event list, needed for the insertion of service ready event
     */
    public MaintenanceServicePoint(ContinuousGenerator generator, EventList eventList) {
        super(generator, eventList);
    }

    @Override
    public void beginService() {
        Customer nextCustomer = getQueue().peek();
        Trace.out(Trace.Level.INFO, "Starting a new service at maintenance for the customer #" + nextCustomer.getId());
        switch (nextCustomer.getMaintenanceType()) {
            case MaintenanceType.TIRE_CHANGE:
                this.eventTypeScheduled = EventType.DEP_MAINTENANCE_TIRE;
            case MaintenanceType.OIL:
                this.eventTypeScheduled = EventType.DEP_MAINTENANCE_OIL;
            case MaintenanceType.OTHER_REPAIR:
                this.eventTypeScheduled = EventType.DEP_MAINTENANCE_OTHER;
        }
        reserved = true;
        double serviceTime = generator.sample();
        eventList.add(new Event(eventTypeScheduled, Clock.getInstance().getClock()+serviceTime));
        this.serviceTime += serviceTime;
    }
}
