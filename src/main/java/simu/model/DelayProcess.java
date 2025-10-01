package simu.model;

import simu.framework.Process;

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

    public void setDelay(int delayMs) {
        this.delayMs = delayMs;
    }

    public void stopDelay() {
        running = false;
        deregister(); // Removing from ProcessManager
    }
}
