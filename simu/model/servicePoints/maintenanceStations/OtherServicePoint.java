package simu.model.servicePoints.maintenanceStations;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Clock;
import simu.framework.Event;
import simu.framework.EventList;
import simu.framework.Trace;
import simu.model.Customer;
import simu.model.EventType;
import simu.model.servicePoints.MaintenanceStationServicePoint;

public class OtherServicePoint extends MaintenanceStationServicePoint {
    /**
     * Create the other repairs service point with a waiting queue.
     * <br>
     * <b>This function is only for compatibility, will be removed soon.</b> <h1>Do not use.</h1>
     * @param generator   Random number generator for service time simulation
     * @param eventList   Simulator event list, needed for the insertion of service ready event
     * @param successRate success rate of the service in service point from 0.0 to 1.0
     */
    @Deprecated
    public OtherServicePoint(ContinuousGenerator generator, EventList eventList, double successRate) {
        super(generator, eventList);
    }

    /**
     * Create the other repairs service point with a waiting queue.
     *
     * @param generator   Random number generator for service time simulation
     * @param eventList   Simulator event list, needed for the insertion of service ready event
     */
    public OtherServicePoint(ContinuousGenerator generator, EventList eventList) {
        super(generator, eventList);
    }

    @Override
    public void beginService() {
        Customer nextCustomer = getQueue().peek();
        Trace.out(Trace.Level.INFO, "Starting a new service at other repairs for the customer #" + nextCustomer.getId());
//        this.eventTypeScheduled = nextCustomer.needInspection() ? EventType.DEP_CS_INSPECTION : EventType.DEP_CS_MAINTENANCE;
        if (nextCustomer.peekMaintenance() != null) {
            this.eventTypeScheduled = EventType.DEP_OTHER_MAINTENANCE;
        } else {
            if (nextCustomer.needInspection()) {
                this.eventTypeScheduled = EventType.DEP_OTHER_INSPECTION;
                nextCustomer.setPassedInspection(true);
            } else {
                this.eventTypeScheduled = EventType.DEP_OTHER_END;
            }
        }
        reserved = true;
        double serviceTime = generator.sample();
        eventList.add(new Event(eventTypeScheduled, Clock.getInstance().getClock()+serviceTime));
        this.serviceTime += serviceTime;
    }
}
