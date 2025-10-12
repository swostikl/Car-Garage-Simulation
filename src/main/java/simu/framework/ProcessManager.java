package simu.framework;

import java.util.ArrayList;

/**
 * A cooperative multitasking thread manager. Relying on each process to voluntarily call {@code giveUp();} to hand the processing power to the next process.
 */
public class ProcessManager {

    private ArrayList<Process> processes;
    private int currentProcessIndex;
    private Process currentProcess;

    /**
     * A cooperative multitasking thread manager. Relying on each process to voluntarily call {@code giveUp();} to hand the processing power to the next process.
     */
    public ProcessManager() {
        this.processes = new ArrayList<>();
    }

    /**
     * Add a process to the process manager and start it.
     * @param p Process
     */
    synchronized public void addProcess(Process p) {
        if (processes.contains(p)) {
            return;
        }
        processes.add(p);
        if (this.currentProcess == null) {
            this.currentProcess = p;
            currentProcessIndex = 0;
        }
        p.setPm(this);
        p.start();
    }

    /**
     * <b>DO NOT USE</b>, call {@code deregister()} on the {@code Process} instead
     */
    synchronized public void removeProcess(Process p) {
        if (processes.contains(p)) {
            p.interrupt();
            processes.remove(p);
            if (currentProcessIndex >= processes.size()) {
                currentProcessIndex = 0;
                if (processes.isEmpty()) {
                    currentProcess = null;
                } else {
                    currentProcess = processes.get(currentProcessIndex);
                }
            } else {
                currentProcess = processes.get(currentProcessIndex);
            }
        }
    }

    /**
     * <b>DO NOT USE</b>, call {@code giveUp()} on the {@code Process} instead
     */
    public synchronized void yield() {
        if ((currentProcessIndex + 1) >= processes.size()) {
            currentProcessIndex = 0;
        } else {
            currentProcessIndex += 1;
        }
        currentProcess = processes.get(currentProcessIndex);
    }

    /**
     * Get process currently running
     * @return {@code Process} process currently running
     */
    public Process getCurrentProcess() {
        return currentProcess;
    }

    /**
     * Check if there are no processes in the process manager
     * @return true if there are no processes, false otherwise
     */
    public boolean hasNoProcesses() {
        return processes.isEmpty();
    }

    /**
     * Check if the process manager contains the given process
     * @param p Process to check
     * @return true if the process manager contains the process, false otherwise
     */
    public boolean containProcess(Process p) {
        return processes.contains(p);
    }

    @Deprecated
    public ArrayList<Process> getProcesses() {
        return processes;
    }
}
