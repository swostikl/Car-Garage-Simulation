package simu.model.servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.EventList;
import simu.model.ServicePoint;

/**
 * Abstract class for maintenance stations
 */
public abstract class MaintenanceStationServicePoint extends ServicePoint {


    /**
     * Create the maintenance station service point with a waiting queue.
     *
     * @param generator Random number generator for service time simulation
     * @param eventList Simulator event list, needed for the insertion of service ready event
     */
    public MaintenanceStationServicePoint(ContinuousGenerator generator, EventList eventList) {
        super(generator, eventList);
    }

    @Override
    abstract public void beginService();
}
