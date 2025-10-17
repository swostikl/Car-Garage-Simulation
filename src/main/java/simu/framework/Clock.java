package simu.framework;

/**
 * Singleton for holding global simulation time
 */
public class Clock {
	private double clock;
	private static Clock instance;

    /**
     * Private constructor to enforce the singleton pattern
     * <p>
     *  Initializes the simulation clock to zero
     * </p>
     */
	private Clock(){
		clock = 0;
	}

    /**
     *
     * @return the single {@code Clock} instance
     */
	public static Clock getInstance(){
		if (instance == null){
			instance = new Clock();
		}
		return instance;
	}

    /**
     * Sets the current simulation time
     * @param clock the new simulation time
     */
	public void setClock(double clock){
		this.clock = clock;
	}

    /**
     * Returns the current simulation time
     * @return the current value of the simulation clock
     */
	public double getClock(){
		return clock;
	}
}
