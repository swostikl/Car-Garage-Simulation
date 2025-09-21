package simu.model.servicePoints.maintenanceStations;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.EventList;
import simu.framework.Trace;
import simu.model.Customer;
import simu.model.EventType;
import simu.model.servicePoints.MaintenanceStationServicePoint;

public class TireChangeServicePoint extends MaintenanceStationServicePoint {
    /**
     * Create the tire change station service point with a waiting queue.
     *
     * @param generator   Random number generator for service time simulation
     * @param eventList   Simulator event list, needed for the insertion of service ready event
     * @param successRate success rate of the service in service point from 0.0 to 1.0
     */
    public TireChangeServicePoint(ContinuousGenerator generator, EventList eventList, double successRate) {
        super(generator, eventList, successRate);
    }

    @Override
    public void beginService() {
        Customer nextCustomer = getQueue().peek();
        Trace.out(Trace.Level.INFO, "Starting a new service at tire change service for the customer #" + nextCustomer.getId());
//        this.eventTypeScheduled = nextCustomer.needInspection() ? EventType.DEP_CS_INSPECTION : EventType.DEP_CS_MAINTENANCE;
        boolean success = Math.random() < successRate;
        if (success) {
            if (nextCustomer.needInspection()) {
                this.eventTypeScheduled = EventType.DEP_TIRE_INSPECTION;
            } else {
                this.eventTypeScheduled = EventType.DEP_TIRE_END;
            }
        } else {
            this.eventTypeScheduled = EventType.DEP_TIRE_MAINTENANCE;
        }
        reserved = true;
        double serviceTime = generator.sample();
        eventList.add(new Event(eventTypeScheduled, Clock.getInstance().getClock()+serviceTime));
        this.serviceTime += serviceTime;
    }
}
