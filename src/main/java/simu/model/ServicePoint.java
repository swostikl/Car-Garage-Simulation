package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.framework.*;
import java.util.LinkedList;

/**
 * Service Point implements the functionalities, calculations and reporting.
 *
 * TODO: This must be modified to actual implementation. Things to be added:
 *     - functionalities of the service point
 *     - measurement variables added
 *     - getters to obtain measurement values
 *
 * Service point has a queue where customers are waiting to be serviced.
 * Service point simulated the servicing time using the given random number generator which
 * generated the given event (customer serviced) for that time.
 *
 * Service point collects measurement parameters.
 */
public abstract class ServicePoint {
	private LinkedList<Customer> queue = new LinkedList<>(); // Data Structure used
	protected ContinuousGenerator generator;
	protected EventList eventList;
	protected EventType eventTypeScheduled;
	//QueueStrategy strategy; // option: ordering of the customer
	protected boolean reserved = false;
    protected double serviceTime;
    protected int customerServed;

    protected Customer currentCustomer; //to Check who is being served currently



	/**
	 * Create the service point with a waiting queue.
	 *
	 * @param generator Random number generator for service time simulation
	 * @param eventList Simulator event list, needed for the insertion of service ready event
	 */
	public ServicePoint(ContinuousGenerator generator, EventList eventList){
		this.eventList = eventList;
		this.generator = generator;
        this.serviceTime = 0;
	}

	/**
	 * Add a customer to the service point queue.
	 *
	 * @param a Customer to be queued
	 */
	public void addQueue(Customer a) {	// The first customer of the queue is always in service
		queue.add(a);
	}

	/**
	 * Remove customer from the waiting queue.
	 * Here we calculate also the appropriate measurement values.
	 *
	 * @return Customer retrieved from the waiting queue
	 */
	public Customer removeQueue() {		// Remove serviced customer
		reserved = false;
        currentCustomer = null; //eta
        customerServed++;
		return queue.poll();
	}

	/**
	 * Begins a new service, customer is on the queue during the service
	 *
	 * Inserts a new event to the event list when the service should be ready.
	 */
	public void beginService() {		// Begins a new service, customer is on the queue during the service
		currentCustomer = queue.peek(); // track active customer
        Trace.out(Trace.Level.INFO, "Starting a new service for the customer #" + queue.peek().getId());

		reserved = true;
		double serviceTime = generator.sample();
		eventList.add(new Event(eventTypeScheduled, Clock.getInstance().getClock()+serviceTime));
	}

	/**
	 * Check whether the service point is busy
	 *
	 * @return logical value indicating service state
	 */
	public boolean isReserved(){
		return reserved;
	}

	/**
	 * Check whether there is customers on the waiting queue
	 *
	 * @return logical value indicating queue status
	 */
	public boolean isOnQueue(){
		return !queue.isEmpty();
	}

    protected LinkedList<Customer> getQueue() {
        return queue;
    }

    /**
     * Get the number of customers in the queue
     * @return numbers of customer in the queue
     */
    public int onQueue() {
        return queue.size();
    }

    /**
     * Getter for controller display
     * @return number of customers srvice
     */
    public Customer getCurrentCustomer(){
        return currentCustomer;
    }

    /**
     * Safe getter for current customer ID.
     * Returns 0 if no customer is currently being served.
     *
     * @return current customer ID or 0 if null
     */




}
