package simu.controller;

import eduni.distributions.Normal;
import simu.model.MyEngine;

/**
 * A class handling Simulation setup
 */
public class FieldController {

    private final MyEngine myEngine;

    public FieldController(MyEngine engine) {
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
