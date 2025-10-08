package simu.util;

import simu.model.ResultData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {

    private static final String FILE_PATH = "simulation_results.csv";
    private static final String HEADER = "Customer Service %,Maintenance %,Tire Change %,Oil Change %,Repair %,Inspection %,Customer Throughput";

    public static void writeResult(ResultData data) {
        File file = new File(FILE_PATH);
        boolean fileExists = file.exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            if (!fileExists) {
                writer.println(HEADER); // Write header only once
            }

            writer.printf("%s,%s,%s,%s,%s,%s,%s%n",
                    data.getCustomerServiceUtilizationRate(),
                    data.getMaintenanceServiceUtilizationRate(),
                    data.getTireChangeServiceUtilizationRate(),
                    data.getOilChangeServiceUtilizationRate(),
                    data.getRepairServiceUtilizationRate(),
                    data.getInspectionServiceUtilizationRate(),
                    data.getCustomerThroughput()
            );
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }
}
