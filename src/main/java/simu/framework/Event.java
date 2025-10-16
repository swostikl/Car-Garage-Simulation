package simu.framework;

/**
 * Event holds three-phase simulation event information; type and time of the event.
 * Events are compared according to time.
 */
public class Event implements Comparable<Event> {
	private IEventType type;
	private double time;

    /**
     *  Constructs a new {@code Event} with the given type and scheduled time
     * @param type the type of the event
     * @param time the scheduled simulation time for this event
     */
	public Event(IEventType type, double time){
		this.type = type;
		this.time = time;
	}

    /**
     * Sets the type of the event
     * @param type the new event type
     */
	public void setType(IEventType type) {
		this.type = type;
	}

    /**
     * Returns the type of the event
     * @return the event type
     */
	public IEventType getType() {
		return type;
	}

    /**
     * Sets the scheduled time
     * @param time the new simulation time
     */

	public void setTime(double time) {
		this.time = time;
	}

    /**
     * Returns the scheduled simulation time of the event
     * @return the simulation time
     */
	public double getTime() {
		return time;
	}

    /**
     *  Campares the event with another event based on the simulation time
     * @param arg the event to compare with
     * @return -1 if this event occurs earlier, 1 if later and 0 if at the same time
     */
	@Override
	public int compareTo(Event arg) {
		if (this.time < arg.time) return -1;
		else if (this.time > arg.time) return 1;
		return 0;
	}
}
