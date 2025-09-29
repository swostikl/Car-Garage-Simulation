package simu.framework;

public abstract class Process extends Thread {

    final private Object lock;
    private ProcessManager pm;

    public Process(Object lock, ProcessManager pm) {
        this.lock = lock;
        this.pm = pm;
    }

    public ProcessManager getPm() {
        return pm;
    }

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
