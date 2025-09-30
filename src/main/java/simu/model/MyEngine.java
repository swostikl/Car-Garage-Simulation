package simu.model;

import controller.VisualizeController;
import eduni.distributions.*;
import simu.framework.*;
import simu.model.bEvent.BEvent;
import simu.model.servicePoints.CustomerServicePoint;
import simu.model.servicePoints.InspectionServicePoint;
import simu.model.servicePoints.MaintenanceServicePoint;
import simu.model.servicePoints.maintenanceStations.TireChangeServicePoint;
import simu.model.servicePoints.maintenanceStations.OilChangeServicePoint;
import simu.model.servicePoints.maintenanceStations.OtherServicePoint;

import java.util.Random;

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


    /**
     * Service Points and random number generator with different distributions are created here.
     * We use exponent distribution for customer arrival times and normal distribution for the
     * service times.
     */

    public MyEngine(VisualizeController vc) {
        super(vc);

        // Using Normal distribution for service times (average 3 time units, standard deviation 1)
        cGenerator = new Normal(3.0, 1.0);

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
        servicePoints[0] = new CustomerServicePoint(cGenerator, eventList);
        servicePoints[1] = new MaintenanceServicePoint(cGenerator, eventList);
        servicePoints[2] = new TireChangeServicePoint(cGenerator, eventList);
        servicePoints[3] = new OilChangeServicePoint(cGenerator, eventList);
        servicePoints[4] = new OtherServicePoint(cGenerator, eventList);
        servicePoints[5] = new InspectionServicePoint(cGenerator, eventList);

        // Create arrival process (customers arriving at the system)
        arrivalProcess = new ArrivalProcess(cGenerator, eventList, EventType.ARR_CUSTOMER_SERVICE);
    }

    @Override
    protected void initialize() {  // First arrival in the system
       arrivalProcess.generateNextEvent();
    }

    @Override
    protected void runEvent(Event t) {  // B phase events
        BEvent.runBEvent(servicePoints, arrivalProcess, t, vc);
    }

    @Override
    protected void tryCEvents() {
       for (ServicePoint p: servicePoints){
          if (!p.isReserved() && p.isOnQueue()){
             p.beginService();
          }
       }
    }

    @Override
    protected void results() {
       System.out.println("Simulation ended at " + Clock.getInstance().getClock());
       System.out.printf("%nResults:%nCustomer throughput: %f customer(s) per hour.%n", (Customer.getTotalServed() / Clock.getInstance().getClock()) * 60);
    }
}