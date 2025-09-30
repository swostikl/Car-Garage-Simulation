package simu.framework;

import java.util.ArrayList;

public class ProcessManager {

    private ArrayList<Process> processes;
    private int currentProcessIndex;
    private Process currentProcess;

    public ProcessManager() {
        this.processes = new ArrayList<>();
    }

    synchronized public void addProcess(Process p) {
        if (processes.contains(p)) {
            return;
        }
        processes.add(p);
        if (this.currentProcess == null) {
            this.currentProcess = p;
            currentProcessIndex = 0;
        }
        p.start();
    }

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

    public synchronized void yield() {
        if ((currentProcessIndex + 1) >= processes.size()) {
            currentProcessIndex = 0;
        } else {
            currentProcessIndex += 1;
        }
        currentProcess = processes.get(currentProcessIndex);
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }
}
