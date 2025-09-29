package simu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import simu.framework.Engine;
import simu.framework.Process;
import simu.framework.ProcessManager;
import simu.framework.Trace;
import simu.model.Interrupt;
import simu.model.MyEngine;

public class StepperTestViewController {

    private ProcessManager pm;

    private Object lock;

    private Interrupt interrupt;

    @FXML
    private Button runButton;

    @FXML
    private ToggleButton pauseButton;

    @FXML
    private Button stepButton;

    @FXML
    void onPressRun(ActionEvent event) {
        Trace.setTraceLevel(Trace.Level.INFO);

        Engine m = new MyEngine(lock, pm);
        m.setSimulationTime(100000);
        pm.addProcess(m);
    }

    @FXML
    void onPausePressed() {
        if (pauseButton.isSelected()) {
            interrupt = new Interrupt(lock, pm);
            stepButton.setDisable(false);
            pm.addProcess(interrupt);
        } else {
            stepButton.setDisable(true);
            interrupt.deregister();
        }
    }

    @FXML
    void onStepPressed() {
        interrupt.giveUp();
    }

    public void init(Object lock, ProcessManager pm) {
        this.lock = lock;
        this.pm = pm;
    }
}
