package simu.model.servicePoints.maintenanceStations;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.EventList;
import simu.framework.Trace;
import simu.model.Customer;
import simu.model.EventType;
import simu.model.servicePoints.MaintenanceStationServicePoint;

public class OilChangeServicePoint extends MaintenanceStationServicePoint {
    /**
     * Create the oil change service point with a waiting queue.
     *
     * @param generator   Random number generator for service time simulation
     * @param eventList   Simulator event list, needed for the insertion of service ready event
     * @param successRate success rate of the service in service point from 0.0 to 1.0
     */
    public OilChangeServicePoint(ContinuousGenerator generator, EventList eventList, double successRate) {
        super(generator, eventList, successRate);
    }

    @Override
    public void beginService() {
        Customer nextCustomer = getQueue().peek();
        Trace.out(Trace.Level.INFO, "Starting a new service at oil change service for the customer #" + nextCustomer.getId());
        boolean success = Math.random() < successRate;
        if (success) {
            if (nextCustomer.needInspection()) {
                this.eventTypeScheduled = EventType.DEP_OIL_INSPECTION;
            } else {
                this.eventTypeScheduled = EventType.DEP_OIL_END;
            }
        } else {
            this.eventTypeScheduled = EventType.DEP_OIL_MAINTENANCE;
        }
        reserved = true;
        double serviceTime = generator.sample();
        eventList.add(new Event(eventTypeScheduled, Clock.getInstance().getClock()+serviceTime));
        this.serviceTime += serviceTime;
    }
}
