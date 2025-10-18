package simu.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * class for the configurable settings for the simulation run.
 */
public class SimulationSettings implements Serializable {

    private String arrivalTimeMean;
    private String arrivalTimeVariance;
    private String totalSimulationTime;
    private String serviceRequiredMean;
    private String serviceRequiredVariance;
    private double inspectionFailRate;

    /**
     * constructs a new {@code SimulationSettings} object with the specified parameters.
     *
     * @param arrivalTimeMean         the mean arrival time for customers
     * @param arrivalTimeVariance     the variance for the arrival time
     * @param totalSimulationTime     the total duration of the simulation
     * @param serviceRequiredMean     the mean service requires per customer
     * @param serviceRequiredVariance the variance of the service time
     * @param inspectionFailRate      the probability of the failed inspection (0.0-1.0)
     */
    public SimulationSettings(String arrivalTimeMean, String arrivalTimeVariance, String totalSimulationTime, String serviceRequiredMean, String serviceRequiredVariance, double inspectionFailRate) {
        this.inspectionFailRate = inspectionFailRate;
        this.arrivalTimeMean = arrivalTimeMean;
        this.arrivalTimeVariance = arrivalTimeVariance;
        this.totalSimulationTime = totalSimulationTime;
        this.serviceRequiredMean = serviceRequiredMean;
        this.serviceRequiredVariance = serviceRequiredVariance;
    }

    /**
     * Returns the mean arrival time
     */
    public String getArrivalTimeMean() {
        return arrivalTimeMean;
    }

    /**
     * Sets the mean arrival time
     */
    public void setArrivalTimeMean(String arrivalTimeMean) {
        this.arrivalTimeMean = arrivalTimeMean;
    }

    /**
     * Returns the variance of the arrival time
     */
    public String getArrivalTimeVariance() {
        return arrivalTimeVariance;
    }

    /**
     * Sets the variance of the arrival time
     */
    public void setArrivalTimeVariance(String arrivalTimeVariance) {
        this.arrivalTimeVariance = arrivalTimeVariance;
    }

    /**
     * Returns the total simulation time
     */
    public String getTotalSimulationTime() {
        return totalSimulationTime;
    }

    /**
     * Sets the total simulation time
     */
    public void setTotalSimulationTime(String totalSimulationTime) {
        this.totalSimulationTime = totalSimulationTime;
    }

    /**
     * Returns the mean service time required per person
     */
    public String getServiceRequiredMean() {
        return serviceRequiredMean;
    }

    /**
     * Sets the mean of the service time required
     */
    public void setServiceRequiredMean(String serviceRequiredMean) {
        this.serviceRequiredMean = serviceRequiredMean;
    }

    /**
     * Returns the variance of the service time required.
     */
    public String getServiceRequiredVariance() {
        return serviceRequiredVariance;
    }

    /**
     * Sets the variance of the service time required.
     */
    public void setServiceRequiredVariance(String serviceRequiredVariance) {
        this.serviceRequiredVariance = serviceRequiredVariance;
    }

    /**
     * Returns the inspection failure rate.
     */
    public double getInspectionFailRate() {
        return inspectionFailRate;
    }

    /**
     * Sets the inspection failure rate.
     */
    public void setInspectionFailRate(double inspectionFailRate) {
        this.inspectionFailRate = inspectionFailRate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SimulationSettings that = (SimulationSettings) o;
        return Double.compare(inspectionFailRate, that.inspectionFailRate) == 0 && Objects.equals(arrivalTimeMean, that.arrivalTimeMean) && Objects.equals(arrivalTimeVariance, that.arrivalTimeVariance) && Objects.equals(totalSimulationTime, that.totalSimulationTime) && Objects.equals(serviceRequiredMean, that.serviceRequiredMean) && Objects.equals(serviceRequiredVariance, that.serviceRequiredVariance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                arrivalTimeMean,
                arrivalTimeVariance,
                totalSimulationTime,
                serviceRequiredMean,
                serviceRequiredVariance,
                inspectionFailRate
        );
    }
}
