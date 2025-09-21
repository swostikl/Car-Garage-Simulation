package simu.model.servicePoints;

import eduni.distributions.ContinuousGenerator;
import simu.framework.EventList;
import simu.model.ServicePoint;

public abstract class MaintenanceStationServicePoint extends ServicePoint {

    protected double successRate;

    /**
     * Create the maintenance station service point with a waiting queue.
     *
     * @param generator Random number generator for service time simulation
     * @param eventList Simulator event list, needed for the insertion of service ready event
     * @param successRate success rate of the service in service point from 0.0 to 1.0
     */
    public MaintenanceStationServicePoint(ContinuousGenerator generator, EventList eventList, double successRate) {
        super(generator, eventList);
        this.successRate = successRate;
    }

    @Override abstract public void beginService();
}
