package simu.model;

import simu.controller.VisualizeController;
import eduni.distributions.*;
import simu.framework.*;
import simu.model.bEvent.BEvent;
import simu.model.servicePoints.CustomerServicePoint;
import simu.model.servicePoints.InspectionServicePoint;
import simu.model.servicePoints.MaintenanceServicePoint;
import simu.model.servicePoints.maintenanceStations.TireChangeServicePoint;
import simu.model.servicePoints.maintenanceStations.OilChangeServicePoint;
import simu.model.servicePoints.maintenanceStations.OtherServicePoint;

/**
 * Main simulator engine.
 *
 * TODO: This is the place where you implement your own simulator
 *
 * Demo simulation case:
 * Simulate three service points, customer goes through all three service points to get serviced
 *       --> SP1 --> SP2 --> SP3 -->
 */
public class MyEngine extends Engine {
    private ArrivalProcess arrivalProcess;
    public static final boolean TEXTDEMO = true;
    public static final boolean FIXEDARRIVALTIMES = false;
    public static final boolean FXIEDSERVICETIMES = false;
    public ContinuousGenerator cGenerator;
    private ContinuousGenerator arrivalContinuousGenerator;
    private ContinuousGenerator serviceContinuousGenerator;
    private double inspectionFailRate;


    private static volatile boolean stopSimulation = false;
    private double mySimulationTime;

    /**
     * Service Points and random number generator with different distributions are created here.
     * We use exponent distribution for customer arrival times and normal distribution for the
     * service times.
     */

    public MyEngine(VisualizeController vc, ContinuousGenerator arrivalContinuousGenerator) {
        super(vc);

        // set arrivalContinuousGenerator
        this.arrivalContinuousGenerator = arrivalContinuousGenerator;


        ContinuousGenerator customerServiceTime = new Normal(20, 16);
        ContinuousGenerator inspectionServiceTime = new Normal(45, 100);
        ContinuousGenerator tireChangeServiceTime = new Normal(60, 225);
        ContinuousGenerator oilChangeServiceTime = new Normal(30, 25);
        ContinuousGenerator repairWorkServiceTime = new Normal(90, 400);
        ContinuousGenerator maintenanceServiceTime = new Normal(40, 100);

        // Create service points array
        servicePoints = new ServicePoint[6];

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
        servicePoints[0] = new CustomerServicePoint(customerServiceTime, eventList);
        servicePoints[1] = new MaintenanceServicePoint(maintenanceServiceTime, eventList);
        servicePoints[2] = new TireChangeServicePoint(tireChangeServiceTime, eventList);
        servicePoints[3] = new OilChangeServicePoint(oilChangeServiceTime, eventList);
        servicePoints[4] = new OtherServicePoint(repairWorkServiceTime, eventList);
        servicePoints[5] = new InspectionServicePoint(inspectionServiceTime, eventList);

        // Create arrival process (customers arriving at the system)
        arrivalProcess = new ArrivalProcess(arrivalContinuousGenerator, eventList, EventType.ARR_CUSTOMER_SERVICE);
    }

    //  Store simulation time locally
    @Override
    public void setSimulationTime(double simulationTime) {
        super.setSimulationTime(simulationTime);
        this.mySimulationTime = simulationTime;
    }

    // Method to request stop
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

        BEvent.runBEvent(servicePoints, arrivalProcess, t, vc, serviceContinuousGenerator, inspectionFailRate);
    }

    @Override
    protected void tryCEvents() {
       for (ServicePoint p: servicePoints){
          if (!p.isReserved() && p.isOnQueue()){
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

        Clock.getInstance().setClock(0); // Reset clock for potential next run

        stopSimulation = false;
        deregister();
    }
}
