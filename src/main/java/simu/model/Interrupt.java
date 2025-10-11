package simu.model;

import simu.framework.Process;

public class Interrupt extends Process {
    public Interrupt() {
        super();
    }

    @Override
    public void run() {
        await();
    }
}
