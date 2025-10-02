package simu.controller;

import controller.VisualizeController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import simu.framework.Engine;
import simu.framework.Process;
import simu.framework.ProcessManager;
import simu.framework.Trace;
import simu.model.DelayProcess; // Import your new DelayProcess
import simu.model.Interrupt;
import simu.model.MyEngine;
import simu.view.VisualizeView;
import java.io.IOException;

public class StepperTestViewController {

    private ProcessManager pm;
    private Object lock;
    private Interrupt interrupt;
    private DelayProcess delayProcess;
    private boolean hasRun = false;

    @FXML
    private Button runButton;

    @FXML
    private ToggleButton pauseButton;

    @FXML
    private Button stepButton;

    @FXML
    private Button decreaseSpeed;

    @FXML
    private Button increaseSpeed;

    @FXML
    private Label delayLabel;

    private VisualizeView view;
    private VisualizeController visualizeController;
    private int currentDelay = 500;

    @FXML
    void onPressRun(ActionEvent event) throws IOException {
        if (!hasRun) {
            runButton.setText("Running");
            runButton.setDisable(true);
            hasRun = true;

            Trace.setTraceLevel(Trace.Level.INFO);
            view = new VisualizeView();
            visualizeController = view.init();

            Engine m = new MyEngine(visualizeController);
            m.setSimulationTime(100000);
            m.setName("Main Simulation");
            pm.addProcess(m);

            // Create and add delay process for timing control
            delayProcess = new DelayProcess(currentDelay);
            pm.addProcess(delayProcess);
        }
    }

    @FXML
    void onPausePressed() {
        if (pauseButton.isSelected()) {
            pauseButton.setText("Resume");
            interrupt = new Interrupt();
            interrupt.setName("INTERRUPT SIMULATION");
            stepButton.setDisable(false);
            pm.addProcess(interrupt);
        } else {
            pauseButton.setText("Pause");
            stepButton.setDisable(true);
            if (interrupt != null) {
                interrupt.deregister();
                interrupt = null;
            }
        }
    }

    @FXML
    void onStepPressed() {
        if (interrupt != null) {
            interrupt.giveUp();
        }
    }

    @FXML
    void onDecreaseSpeed() {
        // Decrease delay = faster simulation
        currentDelay = Math.max(currentDelay - 100, 100);
        updateDelayLabel();
        updateSimulationDelay();
    }

    @FXML
    void onIncreaseSpeed() {
        // Increase delay = slower simulation
        currentDelay = Math.min(currentDelay + 100, 2000);
        updateDelayLabel();
        updateSimulationDelay();
    }

    private void updateDelayLabel() {
        if (delayLabel != null) {
            delayLabel.setText(currentDelay + "ms");
        }
    }

    private void updateSimulationDelay() {
        // Update the DelayProcess with new timing
        if (delayProcess != null) {
            delayProcess.setDelay(currentDelay);
        }
    }

    public void init() {
        this.pm = new ProcessManager();
    }
}
