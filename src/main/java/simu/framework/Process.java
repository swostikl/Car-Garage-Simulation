package simu.framework;

public abstract class Process extends Thread {

    private Object lock;
    private ProcessManager pm;

    /**
     * Create a process for ProcessManager
     * each processes must call {@code await();} and {@code giveUp();} for the ProcessManager to work properly
     */
    public Process() {
    }

    public ProcessManager getPm() {
        return pm;
    }

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
