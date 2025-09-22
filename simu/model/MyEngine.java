package simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import eduni.distributions.Uniform;
import simu.framework.*;
import eduni.distributions.Negexp;
import simu.model.bEvent.BEvent;
import simu.model.servicePoints.CustomerServicePoint;

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

	/**
	 * Service Points and random number generator with different distributions are created here.
	 * We use exponent distribution for customer arrival times and normal distribution for the
	 * service times.
	 */

    public MyEngine() {
        // TODO: Create service points
        servicePoints = new ServicePoint[6];

        /* TODO: There are six service points
           1. Customer service
           2. Maintenance
           3. Tire Change
           4. Oil Change
           5. Other repairs
           6. Inspection

           Each service points has its own class
         */
        // EXAMPLE:
        servicePoints[0] = new CustomerServicePoint(generator, eventList);
        // NOTE: use an appropriate ContinuousGenerator (you can choose one you think it would fit)

        // TODO: also create an arrival process
        arrivalProcess = new ArrivalProcess(generator, eventList, EventType.ARR_CUSTOMER_SERVICE);
    }

	@Override
	protected void initialize() {	// First arrival in the system
		arrivalProcess.generateNextEvent();
	}

	@Override
	protected void runEvent(Event t) {  // B phase events
        BEvent.runBEvent(servicePoints, arrivalProcess);
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
