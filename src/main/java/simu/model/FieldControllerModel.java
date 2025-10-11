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

    public void setArrivalTime(double mean, double variance) {
        myEngine.setArrivalContinuousGenerator(new Normal(mean, variance));
    }

    /**
     * Set number of maintenance required for customers
     *
     * @param mean
     * @param variance
     */
    public void setServiceRequired(double mean, double variance) {
        myEngine.setServiceContinuousGenerator(new Normal(mean, variance));
    }

    public void setInspectionFailRate(double inspectionFailRate) {
        myEngine.setInspectionFailRate(inspectionFailRate);
    }
}
