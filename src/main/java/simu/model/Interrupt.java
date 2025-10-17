package simu.model;

import simu.framework.Process;

/**
 * A thread of type {@link Process} that pauses the simulation
 */
public class Interrupt extends Process {
    public Interrupt() {
        super();
    }

    @Override
    public void run() {
        await();
    }
}
