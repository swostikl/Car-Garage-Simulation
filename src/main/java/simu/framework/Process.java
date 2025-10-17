package simu.framework;

/**
 * Threads that are managed by the {@code ProcessManager}
 */
public abstract class Process extends Thread {

    private Object lock;
    private ProcessManager pm;

    /**
     * Create a process for ProcessManager
     * each processes must call {@code await();} and {@code giveUp();} for the ProcessManager to work properly
     */
    public Process() {
    }

    /**
     * returns the {@link ProcessManager} that manages this process
     * @return the associated {@code ProcessManager}
     */
    public ProcessManager getPm() {
        return pm;
    }

    /**
     * Sets the {@link ProcessManager} foe this process
     * @param pm the {@code ProcessManager} to manage this process
     */
    public void setPm(ProcessManager pm) {
        this.pm = pm;
        this.lock = pm;
    }

    /**
     * Wait for the process turn. Must be called before each processes to make ProcessManager work properly
     */
    public void await() {
        synchronized (lock) {
            while (!pm.getCurrentProcess().equals(this)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

    }

    /**
     * give up the CPU, and allow the next process to run
     */
    public void giveUp() {
        pm.yield();
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    /**
     * Remove current Process (Thread) from the Process Manager
     */
    public void deregister() {
        pm.removeProcess(this);
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Process)) {
            return false;
        }
        return ((Process) o).threadId() == this.threadId();
    }
}
