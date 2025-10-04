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

    private double customerServiceUtilizationRate;
    private double maintenanceServiceUtilizationRate;
    private double tireChangeServiceUtilizationRate;
    private double oilChangeServiceUtilizationRate;
    private double repairServiceUtilizationRate;
    private double inspectionServiceUtilizationRate;
    private double customerThroughput;

    private DecimalFormat df = new DecimalFormat("#.##");
    private DecimalFormat customerThroughputFormat = new DecimalFormat("#.#######");

    public ResultData(double tireChangeServiceUtilizationRate, double customerServiceUtilizationRate, double maintenanceServiceUtilizationRate, double oilChangeServiceUtilizationRate, double repairServiceUtilizationRate, double inspectionServiceUtilizationRate, double customerThroughput) {
        df.setGroupingUsed(false);
        customerThroughputFormat.setGroupingUsed(false);
        this.tireChangeServiceUtilizationRate = tireChangeServiceUtilizationRate;
        this.customerServiceUtilizationRate = customerServiceUtilizationRate;
        this.maintenanceServiceUtilizationRate = maintenanceServiceUtilizationRate;
        this.oilChangeServiceUtilizationRate = oilChangeServiceUtilizationRate;
        this.repairServiceUtilizationRate = repairServiceUtilizationRate;
        this.inspectionServiceUtilizationRate = inspectionServiceUtilizationRate;
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
