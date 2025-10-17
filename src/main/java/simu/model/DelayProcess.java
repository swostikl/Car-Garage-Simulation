package simu.model;

import simu.framework.Process;

/**
 * A thread of type {@code Process} that will delay the simulation for a given milliseconds
 */
public class DelayProcess extends Process {
    private volatile int delayMs;
    private volatile boolean running = true;

    /**
     * Constructs a new {@code DelayProcess} with the given delay duration
     * @param delayMs the delay time in milliseconds
     */
    public DelayProcess(int delayMs) {
        this.delayMs = delayMs;
        setName("Delay Process");
    }

    /**
     * Main process loop. Waits for activation from the {@code ProcessManager},
     * then sleeps for the configured delay time, and yields control back.
     * <p>
     * This method will continue looping until {@link #deregister()} is called,
     * or the thread is interrupted.
     * </p>
     */
    @Override
    public void run() {
        while (running) {
            try {
                await(); // waiting for ProcessManager to make this process to run


                Thread.sleep(delayMs); // Introduce the delay

                giveUp(); // yield control back to the ProcessManager
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Re-interrupt the thread and exit the loop
                break;
            }
        }
    }

    /**
     * Set delay time
     * @param delayMs delay time (in milliseconds)
     */
    public void setDelay(int delayMs) {
        this.delayMs = delayMs;
    }

    /**
     * Get delay time in ms
     * @return delay in ms
     */
    public int getDelayMs() {
        return delayMs;
    }
}
