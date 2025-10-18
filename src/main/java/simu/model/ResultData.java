package simu.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * A data class used to store simulation result data
 */
public class ResultData implements Serializable {

    /* There are six service points
           0. Customer service
           1. Maintenance
           2. Tire Change
           3. Oil Change
           4. Other repairs
           5. Inspection

           Each service point has its own class
    */

    private final double customerServiceUtilizationRate;
    private final double maintenanceServiceUtilizationRate;
    private final double tireChangeServiceUtilizationRate;
    private final double oilChangeServiceUtilizationRate;
    private final double repairServiceUtilizationRate;
    private final double inspectionServiceUtilizationRate;
    private final double customerThroughput;

    private final DecimalFormat df = new DecimalFormat("#.##");
    private final DecimalFormat customerThroughputFormat = new DecimalFormat("#.#######");

    /**
     * Creates a new {@link ResultData} object containing the utilization rates and throughput metrics.
     *
     * @param tireChangeServiceUtilizationRate  utilization rate of the Tire Change service point
     * @param customerServiceUtilizationRate    utilization rate of the Customer Service point
     * @param maintenanceServiceUtilizationRate utilization rate of the Maintenance service point
     * @param oilChangeServiceUtilizationRate   utilization rate of the oil change service point
     * @param repairServiceUtilizationRate      utilization rate of the other repairs service point
     * @param inspectionServiceUtilizationRate  utilization rate of the Inspection service point
     * @param customerThroughput                average number of customers served per hour (customers/hour)
     *
     *
     */
    public ResultData(double tireChangeServiceUtilizationRate, double customerServiceUtilizationRate, double maintenanceServiceUtilizationRate, double oilChangeServiceUtilizationRate, double repairServiceUtilizationRate, double inspectionServiceUtilizationRate, double customerThroughput) {
        df.setGroupingUsed(false);
        customerThroughputFormat.setGroupingUsed(false);
        this.tireChangeServiceUtilizationRate = Math.min(1.0, tireChangeServiceUtilizationRate);
        this.customerServiceUtilizationRate = Math.min(1.0, customerServiceUtilizationRate);
        this.maintenanceServiceUtilizationRate = Math.min(1.0, maintenanceServiceUtilizationRate);
        this.oilChangeServiceUtilizationRate = Math.min(1.0, oilChangeServiceUtilizationRate);
        this.repairServiceUtilizationRate = Math.min(1.0, repairServiceUtilizationRate);
        this.inspectionServiceUtilizationRate = Math.min(1.0, inspectionServiceUtilizationRate);
        this.customerThroughput = customerThroughput;
    }

    /**
     * Returns the utilization rate of the Customer Service point as a percentage string.
     *
     * @return the formatted Customer Service utilization rate (e.g., {@code "85.4 %"})
     *
     */
    public String getCustomerServiceUtilizationRate() {
        return df.format(customerServiceUtilizationRate * 100) + " %";
    }

    /**
     * Returns the utilization rate of the Maintenance service point as a percentage string.
     *
     * @return the formatted Maintenance utilization rate (e.g.{@code "77.2%"})
     */
    public String getMaintenanceServiceUtilizationRate() {
        return df.format(maintenanceServiceUtilizationRate * 100) + " %";
    }

    /**
     *
     * Returns the utilization rate of the Tire Change service point as a percentage string.
     *
     * @return the formatted Tire Change utilization rate (e.g., {@code "91.7 %"})
     */

    public String getTireChangeServiceUtilizationRate() {
        return df.format(tireChangeServiceUtilizationRate * 100) + " %";
    }

    /**
     * Returns the utilization rate of the Oil Change service point as a percentage string.
     *
     * @return the formatted Oil Change utilization rate (e.g., {@code "83.9 %"})
     */

    public String getOilChangeServiceUtilizationRate() {
        return df.format(oilChangeServiceUtilizationRate * 100) + " %";
    }

    /**
     * Returns the utilization rate of the Other Repairs service point as a percentage string.
     *
     * @return the formatted Other Repairs utilization rate (e.g., {@code "62.5 %"})
     *
     */

    public String getRepairServiceUtilizationRate() {
        return df.format(repairServiceUtilizationRate * 100) + " %";
    }

    /**
     * Returns the utilization rate of the Inspection service point as a percentage string.
     *
     * @return the formatted Inspection utilization rate (e.g., {@code "70.3 %"})
     *
     */

    public String getInspectionServiceUtilizationRate() {
        return df.format(inspectionServiceUtilizationRate * 100) + " %";
    }

    /**
     * Returns the overall customer throughput of the simulation.
     *
     * @return the formatted throughput value (e.g., {@code "3.4578912 c/h"})
     *
     */

    public String getCustomerThroughput() {
        return customerThroughputFormat.format(customerThroughput) + " c/h";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultData that = (ResultData) o;
        return Double.compare(customerServiceUtilizationRate, that.customerServiceUtilizationRate) == 0 &&
                Double.compare(maintenanceServiceUtilizationRate, that.maintenanceServiceUtilizationRate) == 0 &&
                Double.compare(tireChangeServiceUtilizationRate, that.tireChangeServiceUtilizationRate) == 0 &&
                Double.compare(oilChangeServiceUtilizationRate, that.oilChangeServiceUtilizationRate) == 0 &&
                Double.compare(repairServiceUtilizationRate, that.repairServiceUtilizationRate) == 0 &&
                Double.compare(inspectionServiceUtilizationRate, that.inspectionServiceUtilizationRate) == 0 &&
                Double.compare(customerThroughput, that.customerThroughput) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customerServiceUtilizationRate,
                maintenanceServiceUtilizationRate,
                tireChangeServiceUtilizationRate,
                oilChangeServiceUtilizationRate,
                repairServiceUtilizationRate,
                inspectionServiceUtilizationRate,
                customerThroughput
        );
    }
}
