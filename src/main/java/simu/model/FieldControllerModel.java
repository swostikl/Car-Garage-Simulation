package simu.model;

import eduni.distributions.Normal;

/**
 * A class handling Simulation setup
 */
public class FieldControllerModel {

    private final MyEngine myEngine;

    public FieldControllerModel(MyEngine engine) {
        myEngine = engine;
    }

    /**
     *  Sets the arrival time distribution for this object using a Normal distribution.
     * @param mean the mean (expected value) of the normal distribution.
     * @param variance the variance (spread) of the normal distribution.
     */
    public void setArrivalTime(double mean, double variance) {
        myEngine.setArrivalContinuousGenerator(new Normal(mean, variance));
    }

    /**
     * Set number of maintenance required for customers ( using Normal distribution)
     *
     * @param mean the mean (expected value) of the normal distribution.
     * @param variance the variance (spread) of the normal distribution.
     * @see Normal
     *
     * See also the {@code setServiceContinuousGenerator} method of {@code myEngine}.
     */
    public void setServiceRequired(double mean, double variance) {
        myEngine.setServiceContinuousGenerator(new Normal(mean, variance));
    }

    /**
     * sets the inspection failure rate for this object
     * <p>
     * This method configures the inspection failure rate of{@code myEngine} with the specified value.The inspection failure
     * rate represents the probability that an inspection will fail.
     * </p>
     *
     * @param inspectionFailRate the failure rate for inspections, typically expressed as value between {@code 0.0} and {@code 1.0}
     */
    public void setInspectionFailRate(double inspectionFailRate) {
        myEngine.setInspectionFailRate(inspectionFailRate);
    }
}
