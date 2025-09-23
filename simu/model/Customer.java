package simu.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
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
    private LinkedList<MaintenanceType> mt;

    private final Random rand = new Random();
    private static final MaintenanceType[] MAINTENANCE_TYPES = MaintenanceType.values();


    /**
     * Create a unique customer
     * @param maintenanceGenerator a continuous generator used to generate numbers of maintenance needed
     * @param needInspectionPercentage percentage of customers that requires an inspection from 0.0 to 1.0
     * @param inspectionFailRate percentage of customers that will fail initial inspection
     */
    public Customer(ContinuousGenerator maintenanceGenerator, double needInspectionPercentage, double inspectionFailRate) {
        this.mt = new LinkedList<>();
        if (needInspectionPercentage > 1 || needInspectionPercentage < 0) {
            throw new IllegalArgumentException("needInspectionPercentage cannot be more than 1 or less than 0.");
        }
        if (inspectionFailRate > 1 || inspectionFailRate < 0) {
            throw new IllegalArgumentException("inspectionFailRate cannot be more than 1 or less than 0.");
        }
        id = i++;
        this.needInspection = rand.nextDouble() < needInspectionPercentage;
        this.passedInspection = !(rand.nextDouble() < inspectionFailRate); //set default

        int maintenanceNeeded = Math.max(0, (int)Math.round(maintenanceGenerator.sample()));

        for (int i = 0; i < maintenanceNeeded; i++) {
            mt.add(getRandomMaintenanceType());
        }

        arrivalTime = Clock.getInstance().getClock();
        Trace.out(Trace.Level.INFO, "New customer #" + id + " arrived at  " + arrivalTime +
                " | Needs inspection: " + needInspection);
    }
// Randomly pick a maintenance type
    private MaintenanceType getRandomMaintenanceType() {
        return MAINTENANCE_TYPES[rand.nextInt(MAINTENANCE_TYPES.length)];

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
//    public MaintenanceType[] getMaintenanceType() {
//        return mt.toArray(new MaintenanceType[0]);
//    }

    public void addMaintenanceType(MaintenanceType mt) {
        this.mt.add(mt);
    }

    /**
     * @return next maintenance type (without deleting)
     */
    public MaintenanceType peekMaintenance() {
        if (mt.isEmpty()) {
            return null;
        }
        return mt.get(0);
    }

    /**
     * @return next maintenance type (will also remove it from the list)
     */
    public MaintenanceType pollMaintenance() {
        if (mt.isEmpty()) {
            return null;
        }
        return mt.remove(0);
    }

    /**
     * is inspection needed
     * @return is inspection needed
     */
    public boolean needInspection() {
        return needInspection;
    }

    /**
     * Misleading method name, returns if customer will pass the upcoming inspection
     * @return will customer pass upcoming inspection
     */
    public boolean hasPassedInspection() {
        return passedInspection;
    }

    /**
     * Misleading method name, set if customer will pass the upcoming inspection
     * @param passed will customer pass the upcoming inspection
     */
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
        double mean = sum / totalServed;
        System.out.println("Current mean of the customer service times " + mean);
        System.out.println(" Total number of customer served : " + totalServed);
    }
}

