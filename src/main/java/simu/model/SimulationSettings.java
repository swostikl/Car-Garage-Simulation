package simu.model;

import java.io.Serializable;

public class SimulationSettings implements Serializable {

    private String arrivalTimeMean;
    private String arrivalTimeVariance;
    private String totalSimulationTime;
    private String serviceRequiredMean;
    private String serviceRequiredVariance;
    private double inspectionFailRate;

    public SimulationSettings(String arrivalTimeMean, String arrivalTimeVariance, String totalSimulationTime, String serviceRequiredMean, String serviceRequiredVariance, double inspectionFailRate) {
        this.inspectionFailRate = inspectionFailRate;
        this.arrivalTimeMean = arrivalTimeMean;
        this.arrivalTimeVariance = arrivalTimeVariance;
        this.totalSimulationTime = totalSimulationTime;
        this.serviceRequiredMean = serviceRequiredMean;
        this.serviceRequiredVariance = serviceRequiredVariance;
    }

    public String getArrivalTimeMean() {
        return arrivalTimeMean;
    }

    public void setArrivalTimeMean(String arrivalTimeMean) {
        this.arrivalTimeMean = arrivalTimeMean;
    }

    public String getArrivalTimeVariance() {
        return arrivalTimeVariance;
    }

    public void setArrivalTimeVariance(String arrivalTimeVariance) {
        this.arrivalTimeVariance = arrivalTimeVariance;
    }

    public String getTotalSimulationTime() {
        return totalSimulationTime;
    }

    public void setTotalSimulationTime(String totalSimulationTime) {
        this.totalSimulationTime = totalSimulationTime;
    }

    public String getServiceRequiredMean() {
        return serviceRequiredMean;
    }

    public void setServiceRequiredMean(String serviceRequiredMean) {
        this.serviceRequiredMean = serviceRequiredMean;
    }

    public String getServiceRequiredVariance() {
        return serviceRequiredVariance;
    }

    public void setServiceRequiredVariance(String serviceRequiredVariance) {
        this.serviceRequiredVariance = serviceRequiredVariance;
    }

    public double getInspectionFailRate() {
        return inspectionFailRate;
    }

    public void setInspectionFailRate(double inspectionFailRate) {
        this.inspectionFailRate = inspectionFailRate;
    }
}
