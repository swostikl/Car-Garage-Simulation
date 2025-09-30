package simu.model;

import simu.framework.Process;
import simu.framework.ProcessManager;

public class Interrupt extends Process {
    public Interrupt() {
        super();
    }

    @Override
    public void run() {
        await();
    }
}
