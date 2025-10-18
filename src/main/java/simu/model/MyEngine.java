package simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import javafx.application.Platform;
import simu.framework.ArrivalProcess;
import simu.framework.Clock;
import simu.framework.Engine;
import simu.framework.Event;
import simu.model.bEvent.BEvent;
import simu.model.servicePoints.CustomerServicePoint;
import simu.model.servicePoints.InspectionServicePoint;
import simu.model.servicePoints.MaintenanceServicePoint;
import simu.model.servicePoints.ServicePointTypes;
import simu.model.servicePoints.maintenanceStations.OilChangeServicePoint;
import simu.model.servicePoints.maintenanceStations.OtherServicePoint;
import simu.model.servicePoints.maintenanceStations.TireChangeServicePoint;
import simu.view.ResultView;

import java.util.HashMap;
import java.util.Map;

/**
 * Main simulator engine.
 */
public class MyEngine extends Engine {
    private static volatile boolean stopSimulation = false;
    private final ArrivalProcess arrivalProcess;
    private ContinuousGenerator arrivalContinuousGenerator;
    private ContinuousGenerator serviceContinuousGenerator;
    private double inspectionFailRate;
    private double mySimulationTime;

    /**
     * Service Points and random number generator with different distributions are created here.
     * We use exponent distribution for customer arrival times and normal distribution for the
     * service times.
     *
     * @param arrivalContinuousGenerator the generator used to create a customer arrivals
     */

    public MyEngine(ContinuousGenerator arrivalContinuousGenerator) {
//        super();

        // set arrivalContinuousGenerator
        this.arrivalContinuousGenerator = arrivalContinuousGenerator;


        ContinuousGenerator customerServiceTime = new Normal(20, 16);
        ContinuousGenerator inspectionServiceTime = new Normal(45, 100);
        ContinuousGenerator tireChangeServiceTime = new Normal(60, 225);
        ContinuousGenerator oilChangeServiceTime = new Normal(30, 25);
        ContinuousGenerator repairWorkServiceTime = new Normal(90, 400);
        ContinuousGenerator maintenanceServiceTime = new Normal(40, 100);

        // Create service points array
        servicePoints = new HashMap<>();

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

        // Create all service points in the correct order
        servicePoints.put(ServicePointTypes.CUSTOMER_SERVICE, new CustomerServicePoint(customerServiceTime, eventList));
        servicePoints.put(ServicePointTypes.MAINTENANCE, new MaintenanceServicePoint(maintenanceServiceTime, eventList));
        servicePoints.put(ServicePointTypes.TIRE_CHANGE, new TireChangeServicePoint(tireChangeServiceTime, eventList));
        servicePoints.put(ServicePointTypes.OIL_CHANGE, new OilChangeServicePoint(oilChangeServiceTime, eventList));
        servicePoints.put(ServicePointTypes.OTHER_REPAIRS, new OtherServicePoint(repairWorkServiceTime, eventList));
        servicePoints.put(ServicePointTypes.INSPECTION, new InspectionServicePoint(inspectionServiceTime, eventList));

        // Create arrival process (customers arriving at the system)
        arrivalProcess = new ArrivalProcess(this.arrivalContinuousGenerator, eventList, EventType.ARR_CUSTOMER_SERVICE);
    }

    //  Store simulation time locally

    /**
     * Sets the total simulation Time
     *
     * @param simulationTime Ending time of the simulation
     */
    @Override
    public void setSimulationTime(double simulationTime) {
        super.setSimulationTime(simulationTime);
        this.mySimulationTime = simulationTime;
    }

    // Method to request stop

    /**
     * Requests the Simulation to stop
     * <p>
     * This method sets a stop flag, updates the simulation time to the current
     * clock value, and ensures the engine's current process deregisters properly.
     * </p>
     */
    public void requestStop() {
        System.out.println("Trying to stop simulation...");
        stopSimulation = true;
        super.setSimulationTime(Clock.getInstance().getClock());
        while (!getPm().getCurrentProcess().equals(this)) {
            getPm().getCurrentProcess().deregister();
        }
    }

    @Override
    protected void initialize() {// First arrival in the system
        Customer.resetTotalServed();     // Reset customer count for potential next run
        arrivalProcess.generateNextEvent();
    }

    @Override
    protected void runEvent(Event t) {  // B phase events
        // ADDITION: Check for stop condition
        if (stopSimulation) {
            super.setSimulationTime(Clock.getInstance().getClock());
            return;
        }

        BEvent.runBEvent(servicePoints, arrivalProcess, t, serviceContinuousGenerator, inspectionFailRate);
    }

    @Override
    protected void tryCEvents() {
        for (Map.Entry<ServicePointTypes, ServicePoint> entry : servicePoints.entrySet()) {
            ServicePoint p = entry.getValue();
            if (!p.isReserved() && p.isOnQueue()) {
                p.beginService();
            }
        }
    }

    public void setArrivalContinuousGenerator(ContinuousGenerator arrivalContinuousGenerator) {
        this.arrivalContinuousGenerator = arrivalContinuousGenerator;
    }

    public void setServiceContinuousGenerator(ContinuousGenerator serviceContinuousGenerator) {
        this.serviceContinuousGenerator = serviceContinuousGenerator;
    }

    public void setInspectionFailRate(double inspectionFailRate) {
        this.inspectionFailRate = inspectionFailRate;
    }

    @Override
    protected void results() {
        double currentTime = Clock.getInstance().getClock();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("SIMULATION RESULTS");
        System.out.println("=".repeat(50));

        if (stopSimulation) {
            System.out.println("Status: STOPPED BY USER");
            if (mySimulationTime > 0) {
                System.out.printf("Completed: %.1f%% (%.0f / %.0f minutes)%n",
                        (currentTime / mySimulationTime) * 100, currentTime, mySimulationTime);
            }
        } else {
            System.out.println("Status: COMPLETED SUCCESSFULLY");
        }

        System.out.printf("Simulation ended at: %.2f minutes%n", currentTime);
        System.out.printf("Customer throughput: %.6f customer(s) per hour%n",
                (Customer.getTotalServed() / currentTime) * 60);
        System.out.printf("Total customers served: %d%n", Customer.getTotalServed());
        System.out.println("=".repeat(50) + "\n");

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

        Platform.runLater(() -> {
            ResultData resultData = new ResultData(
                    servicePoints.get(ServicePointTypes.TIRE_CHANGE).serviceTime / currentTime,
                    servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE).serviceTime / currentTime,
                    servicePoints.get(ServicePointTypes.MAINTENANCE).serviceTime / currentTime,
                    servicePoints.get(ServicePointTypes.OIL_CHANGE).serviceTime / currentTime,
                    servicePoints.get(ServicePointTypes.OTHER_REPAIRS).serviceTime / currentTime,
                    servicePoints.get(ServicePointTypes.INSPECTION).serviceTime / currentTime,
                    ((Customer.getTotalServed() / currentTime) * 60));
            ResultView.getInstance().getController().addResult(resultData);
            DataStore.getInstance().addResult(resultData);
        });

        Clock.getInstance().setClock(0); // Reset clock for potential next run

        stopSimulation = false;
        deregister();
    }
}
