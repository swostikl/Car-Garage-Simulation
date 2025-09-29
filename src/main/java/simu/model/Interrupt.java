package simu.model;

import simu.framework.Process;
import simu.framework.ProcessManager;

public class Interrupt extends Process {
    public Interrupt(Object lock, ProcessManager pm) {
        super(lock, pm);
    }

    @Override
    public void run() {
        await();
    }
}
