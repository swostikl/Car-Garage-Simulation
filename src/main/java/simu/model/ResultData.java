package simu.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class ResultData implements Serializable {

    /* There are six service points
           0. Customer service
           1. Maintenance
           2. Tire Change
           3. Oil Change
           4. Other repairs
           5. Inspection

           Each service point has its own class
           Service points should be added to the array in this order
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

    public String getCustomerServiceUtilizationRate() {
        return df.format(customerServiceUtilizationRate * 100) + " %";
    }

    public String getMaintenanceServiceUtilizationRate() {
        return df.format(maintenanceServiceUtilizationRate * 100) + " %";
    }

    public String getTireChangeServiceUtilizationRate() {
        return df.format(tireChangeServiceUtilizationRate * 100) + " %";
    }

    public String getOilChangeServiceUtilizationRate() {
        return df.format(oilChangeServiceUtilizationRate * 100) + " %";
    }

    public String getRepairServiceUtilizationRate() {
        return df.format(repairServiceUtilizationRate * 100) + " %";
    }

    public String getInspectionServiceUtilizationRate() {
        return df.format(inspectionServiceUtilizationRate * 100) + " %";
    }

    public String getCustomerThroughput() {
        return customerThroughputFormat.format(customerThroughput) + " c/h";
    }
}
