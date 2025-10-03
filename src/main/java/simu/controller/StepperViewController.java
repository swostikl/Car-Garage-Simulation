package simu.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import simu.framework.ProcessManager;
import simu.model.DelayProcess;
import simu.model.Interrupt;
import simu.model.MyEngine;

public class StepperViewController {

    private ProcessManager pm;
    private Interrupt interrupt;
    private DelayProcess delayProcess;

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

    private int currentDelay;

    @FXML
    void onPausePressed() {
        if (pauseButton.isSelected()) {
            delayProcess.deregister();
            pauseButton.setText("Resume");
            interrupt = new Interrupt();
            interrupt.setName("INTERRUPT SIMULATION");
            stepButton.setDisable(false);
            pm.addProcess(interrupt);
        } else {
            delayProcess = new DelayProcess(currentDelay);
            pm.addProcess(delayProcess);
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
        currentDelay = Math.max(currentDelay - 100, 100);
        updateDelayLabel();
        updateDelayProcess();
    }

    @FXML
    void onIncreaseSpeed() {
        currentDelay = Math.min(currentDelay + 100, 2000);
        updateDelayLabel();
        updateDelayProcess();
    }

    private void updateDelayLabel() {
        Platform.runLater(() -> {
            if (delayLabel != null) {
                delayLabel.setText(currentDelay + "ms");
            }
        });
    }

    private void updateDelayProcess() {
        if (delayProcess != null) {
            delayProcess.setDelay(currentDelay);
        }
    }

    public void init(ProcessManager pm, int currentDelay) {
        this.pm = pm;
        this.currentDelay = currentDelay;
        updateDelayLabel();
        delayProcess = new DelayProcess(currentDelay);
        pm.addProcess(delayProcess);
    }

    //  stop method for window closing
    public void stopSimulation() {
        MyEngine.requestStop();

        if (delayProcess != null) {
            delayProcess.deregister();
            delayProcess = null;
        }

        if (interrupt != null) {
            interrupt.deregister();
            interrupt = null;
        }

        Platform.runLater(() -> {
            if (pauseButton != null) {
                pauseButton.setSelected(false);
                pauseButton.setText("Pause");
            }
            if (stepButton != null) {
                stepButton.setDisable(true);
            }
        });
    }
}
