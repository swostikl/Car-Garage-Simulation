package simu.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import simu.framework.ProcessManager;
import simu.model.DelayProcess;
import simu.model.Interrupt;

/**
 * Controller class for StepperView UI
 */
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

    /**
     * Handles the pause/resume toggle button action
     * <p>
     * When paused, the delay process is deregistered and an interrupt
     * process is added to halt the simulation. When resumed, the delay
     * process is restarted and the interrupt is removed.
     * </p>
     */
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

    /**
     * handles the "Step" button action
     */
    @FXML
    void onStepPressed() {
        if (interrupt != null) {
            interrupt.giveUp();
        }
    }

    /**
     * Decreases the simulation speed (reduces delay between steps).
     * <p>
     * Updates the delay value, the label, and the delay process.
     * Minimum delay is 100 ms.
     * </p>
     */

    @FXML
    void onDecreaseSpeed() {
        currentDelay = Math.max(currentDelay - 100, 100);
        updateDelayLabel();
        updateDelayProcess();
    }

    /**
     * Increases the simulation speed (increases delay between steps).
     * <p>
     * Updates the delay value, the label, and the delay process.
     * Maximum delay is 2000 ms.
     * </p>
     */

    @FXML
    void onIncreaseSpeed() {
        currentDelay = Math.min(currentDelay + 100, 2000);
        updateDelayLabel();
        updateDelayProcess();
    }

    /**
     * Updates the delay label in the UI asynchronously using {@link Platform#runLater}.
     */

    private void updateDelayLabel() {
        Platform.runLater(() -> {
            if (delayLabel != null) {
                delayLabel.setText(currentDelay + "ms");
            }
        });
    }

    /**
     * Updates the delay value of the current {@link DelayProcess}.
     */

    private void updateDelayProcess() {
        if (delayProcess != null) {
            delayProcess.setDelay(currentDelay);
        }
    }

    /**
     * Initializes the controller with the {@link ProcessManager} and starting delay.
     * @param pm process manager that runs the simulation
     * @param currentDelay the initial delay in ms between simulation steps
     */
    public void init(ProcessManager pm, int currentDelay) {
        this.pm = pm;
        this.currentDelay = currentDelay;
        updateDelayLabel();
        delayProcess = new DelayProcess(currentDelay);
        pm.addProcess(delayProcess);
    }


    //  stop method for window closing

    /**
     * Stops simulation and cleans up processes and UI elements
     */
    @Deprecated
    public void stopSimulation() {

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
