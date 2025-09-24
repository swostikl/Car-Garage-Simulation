package simu.model;

import eduni.distributions.*;
import simu.framework.*;
import simu.model.bEvent.BEvent;
import simu.model.servicePoints.CustomerServicePoint;
import simu.model.servicePoints.InspectionServicePoint;
import simu.model.servicePoints.MaintenanceServicePoint;
import simu.model.servicePoints.maintenanceStations.OilChangeServicePoint;
import simu.model.servicePoints.maintenanceStations.OtherServicePoint;
import simu.model.servicePoints.maintenanceStations.TireChangeServicePoint;

import java.util.Random;

/**
 * Main simulator engine.
 *
 * TODO: This is the place where you implement your own simulator
 *
 * Demo simulation case:
 * Simulate three service points, customer goes through all three service points to get serviced
 * 		--> SP1 --> SP2 --> SP3 -->
 */
public class MyEngine extends Engine {
	private ArrivalProcess arrivalProcess;
	private ServicePoint[] servicePoints;
	public static final boolean TEXTDEMO = true;
	public static final boolean FIXEDARRIVALTIMES = false;
	public static final boolean FXIEDSERVICETIMES = false;
    public ContinuousGenerator cGenerator;

	/**
	 * Service Points and random number generator with different distributions are created here.
	 * We use exponent distribution for customer arrival times and normal distribution for the
	 * service times.
	 */

    public MyEngine() {
//        // TODO: use an appropriate ContinuousGenerator (you can choose one you think it would fit)
//        // TODO: replace with a continuous generator
//        cGenerator = new ContinuousGenerator();
//
//        // TODO: Create service points
//        servicePoints = new ServicePoint[6];
//
//        /* TODO: There are six service points
//           0. Customer service
//           1. Maintenance
//           2. Tire Change
//           3. Oil Change
//           4. Other repairs
//           5. Inspection
//
//           Each service points has its own class
//           Service points should be added to the array in this order
//         */
//        // EXAMPLE:
//        servicePoints[0] = new CustomerServicePoint(cGenerator, eventList);
//
//        // TODO: also create an arrival process
//        arrivalProcess = new ArrivalProcess(cGenerator, eventList, EventType.ARR_CUSTOMER_SERVICE);

        test();

    }

    private void test() {
        cGenerator = new ContinuousGenerator() {
            @Override
            public double sample() {
                return 10;
            }

            @Override
            public void setSeed(long seed) {

            }

            @Override
            public long getSeed() {
                return 10;
            }

            @Override
            public void reseed() {

            }
        };

        servicePoints = new ServicePoint[6];

        servicePoints[0] = new CustomerServicePoint(cGenerator, eventList);
        servicePoints[1] = new MaintenanceServicePoint(cGenerator, eventList);
        servicePoints[2] = new TireChangeServicePoint(cGenerator, eventList);
        servicePoints[3] = new OilChangeServicePoint(cGenerator, eventList);
        servicePoints[4] = new OtherServicePoint(cGenerator, eventList);
        servicePoints[5] = new InspectionServicePoint(cGenerator, eventList);

        arrivalProcess = new ArrivalProcess(new ContinuousGenerator() {
            @Override
            public double sample() {
                return 20;
            }

            @Override
            public void setSeed(long seed) {

            }

            @Override
            public long getSeed() {
                return 0;
            }

            @Override
            public void reseed() {

            }
        }, eventList, EventType.ARR_CUSTOMER_SERVICE);

    }

	@Override
	protected void initialize() {	// First arrival in the system
		arrivalProcess.generateNextEvent();
	}

	@Override
	protected void runEvent(Event t) {  // B phase events
        BEvent.runBEvent(servicePoints, arrivalProcess, t);
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
		System.out.println("Results ... are currently missing");
	}
}
