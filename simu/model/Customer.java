package simu.model;

import simu.framework.*;

/**
 * Customer in a simulator
 * TODO: This is to be implemented according to the requirements of the simulation model (data!)
 */


// Here we need more method like needInspection, maintenanceType,
public class Customer {
    private double arrivalTime;
    private double removalTime;
    private int id;

    private static int totalServed = 0; // total number of customer served
    private static int i = 1;
    private static long sum = 0;    //sum of all service time
    private boolean needInspection; // check  need inspection or not
    private boolean passedInspection; // inspection is passed or not
    private MaintenanceType mt;

    private final Random rand = new Random();
    private static final MaintenanceType[] MAINTENANCE_TYPES = MaintenanceType.values();


    /**
     * Create a unique customer
     */
    public Customer() {
        id = i++;
        this.needInspection = rand.nextDouble < 0.3;
        this.passedInspection = false; //set default
        this.mt = getRandomMaintenanceType();

        arrivalTime = Clock.getInstance().getClock();
        Trace.out(Trace.Level.INFO, "New customer #" + id + " arrived at  " + arrivalTime +
                " | Maintenance: " + mt +
                " | Needs inspection: " + needInspection);

        private static MaintenanceType getRandomMaintenanceType() {
            return MAINTENANCE_TYPES[rand.nextInt(MAINTENANCE_TYPES.length)];
        }
    }

    /**
     * Give the time when customer has been removed (from the system to be simulated)
     *
     * @return Customer removal time
     */
    public double getRemovalTime() {
        return removalTime;
    }

    /**
     * Mark the time when the customer has been removed (from the system to be simulated)
     *
     * @param removalTime Customer removal time
     */
    public void setRemovalTime(double removalTime) {
        this.removalTime = removalTime;
    }

    /**
     * Give the time when the customer arrived to the system to be simulated
     *
     * @return Customer arrival time
     */
    public double getArrivalTime() {

        return arrivalTime;
    }

    /**
     * Mark the time when the customer arrived to the system to be simulated
     *
     * @param arrivalTime Customer arrival time
     */
    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Get the (unique) customer id
     *
     * @return Customer id
     */
    public int getId() {
        return id;
    }

    /**
     * Report the measured variables of the customer. In this case to the diagnostic output.
     */
    public MaintenanceType getMaintenanceType() {
        return mt;
    }

    public void setMaintenanceType(MaintenanceType mt) {
        this.mt = mt;
    }

    //boolean methods
    public boolean needInspection() {
        return needInspection;

    }

    public boolean hasPassedInspection() {
        return passedInspection;
    }

    public void setPassedInspection(boolean passed) {
        this.passedInspection = passed;
    }

    public void reportResults() {

        Trace.out(Trace.Level.INFO, "\nCustomer " + id + " ready! ");
        Trace.out(Trace.Level.INFO, "Customer " + id + " arrived: " + arrivalTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " removed: " + removalTime);
        Trace.out(Trace.Level.INFO, "Customer " + id + " stayed: " + (removalTime - arrivalTime));

        sum += (removalTime - arrivalTime);
        totalServed++; // increament global counter (to see total number of customer being served
        double mean = sum / id;
        System.out.println("Current mean of the customer service times " + mean);
        System.out.println(" Total number of customer served : " + totalServed);
    }
}
