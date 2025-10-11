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
     * <br>
     * <b>Deprecated: This constructor is only for compatibility and will be removed soon. Do not use.</b>
     *
     * @param generator   Random number generator for service time simulation
     * @param eventList   Simulator event list, needed for the insertion of service ready event
     * @param successRate success rate of the service in service point from 0.0 to 1.0
     * @deprecated This constructor is deprecated and will be removed soon. Use the constructor without successRate instead.
     */
    @Deprecated
    public OilChangeServicePoint(ContinuousGenerator generator, EventList eventList, double successRate) {
        super(generator, eventList);
    }

    /**
     * Create the oil change service point with a waiting queue.
     *
     * @param generator Random number generator for service time simulation
     * @param eventList Simulator event list, needed for the insertion of service ready event
     */
    public OilChangeServicePoint(ContinuousGenerator generator, EventList eventList) {
        super(generator, eventList);
    }

    @Override
    public void beginService() {
        Customer nextCustomer = getQueue().peek();
        Trace.out(Trace.Level.INFO, "Starting a new service at oil change service for the customer #" + nextCustomer.getId());
        if (nextCustomer.peekMaintenance() != null) {
            this.eventTypeScheduled = EventType.DEP_OIL_MAINTENANCE;
        } else {
            if (nextCustomer.needInspection()) {
                this.eventTypeScheduled = EventType.DEP_OIL_INSPECTION;
                nextCustomer.setPassedInspection(true);
            } else {
                this.eventTypeScheduled = EventType.DEP_OIL_END;
            }
        }
        reserved = true;
        double serviceTime = generator.sample();
        eventList.add(new Event(eventTypeScheduled, Clock.getInstance().getClock() + serviceTime));
        this.serviceTime += serviceTime;
        currentCustomer = nextCustomer;
    }
}
