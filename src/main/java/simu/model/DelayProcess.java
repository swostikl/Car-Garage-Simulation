package simu.model;

import simu.framework.Process;

/**
 * A thread of type {@code Process} that will delay the simulation for a given milliseconds
 */
public class DelayProcess extends Process {
    private volatile int delayMs;
    private volatile boolean running = true;

    public DelayProcess(int delayMs) {
        this.delayMs = delayMs;
        setName("Delay Process");
    }

    @Override
    public void run() {
        while (running) {
            try {
                await(); // waiting for ProcessManager to make this process to run


                Thread.sleep(delayMs);

                giveUp();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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

    public void stopDelay() {
        running = false;
        deregister(); // Removing from ProcessManager
    }
}
