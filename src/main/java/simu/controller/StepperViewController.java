//package simu.controller;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ToggleButton;
//import simu.framework.Engine;
//import simu.framework.ProcessManager;
//import simu.framework.Trace;
//import simu.model.DelayProcess; // Add this import
//import simu.model.Interrupt;
//import simu.model.MyEngine;
//import java.io.IOException;
//
//public class StepperViewController {
//
//    private ProcessManager pm;
//    private Interrupt interrupt;
//    private DelayProcess delayProcess;
//    private VisualizeController visualizeController;
//
//    @FXML
//    private ToggleButton pauseButton;
//
//    @FXML
//    private Button stepButton;
//
//    @FXML
//    private Button decreaseSpeed;
//
//    @FXML
//    private Button increaseSpeed;
//
//    @FXML
//    private Label delayLabel;
//
//    private int currentDelay;
//
//    @FXML
//    void onPausePressed() {
//        if (pauseButton.isSelected()) {
//            delayProcess.deregister();
//            pauseButton.setText("Resume");
//            interrupt = new Interrupt();
//            interrupt.setName("INTERRUPT SIMULATION");
//            stepButton.setDisable(false);
//            pm.addProcess(interrupt);
//        } else {
//            delayProcess = new DelayProcess(currentDelay);
//            pm.addProcess(delayProcess);
//            pauseButton.setText("Pause");
//            stepButton.setDisable(true);
//            if (interrupt != null) {
//                interrupt.deregister();
//                interrupt = null;
//            }
//        }
//    }
//
//    @FXML
//    void onStepPressed() {
//        if (interrupt != null) {
//            interrupt.giveUp(); //  simulation run by one click only
//        }
//    }
//
//    @FXML
//    void onDecreaseSpeed() {
//        currentDelay = Math.max(currentDelay - 100, 100);
//        updateDelayLabel();
//        updateDelayProcess();
//    }
//
//    @FXML
//    void onIncreaseSpeed() {
//        currentDelay = Math.min(currentDelay + 100, 2000);
//        updateDelayLabel();
//        updateDelayProcess();
//    }
//
//    private void updateDelayLabel() {
//        Platform.runLater(() -> {
//            if (delayLabel != null) {
//                delayLabel.setText(currentDelay + "ms");
//            }
//        });
//    }
//
//    private void updateDelayProcess() {
//        if (delayProcess != null) {
//            delayProcess.setDelay(currentDelay);
//        }
//    }
//
//    public void init(ProcessManager pm, int currentDelay) {
//        this.pm = pm;
//        this.currentDelay = currentDelay;
//        updateDelayLabel();
//        delayProcess = new DelayProcess(currentDelay);
//        pm.addProcess(delayProcess);
//    }
//
////    public void startSimulation(VisualizeController visualizeController) {
////        this.visualizeController = visualizeController;
////
////        Trace.setTraceLevel(Trace.Level.INFO);
////
////
////        Engine m = new MyEngine(visualizeController);
////        m.setSimulationTime(100000);
////        m.setName("Main Simulation");
////        pm.addProcess(m);
////
////        delayProcess = new DelayProcess(currentDelay);
////        pm.addProcess(delayProcess);
////
////        System.out.println("Simulation engine and delay process added to ProcessManager");
////    }
//}
package simu.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import simu.framework.Engine;
import simu.framework.ProcessManager;
import simu.framework.Trace;
import simu.model.DelayProcess;
import simu.model.Interrupt;
import simu.model.MyEngine;
import java.io.IOException;

public class StepperViewController {

    private ProcessManager pm;
    private Interrupt interrupt;
    private DelayProcess delayProcess;
    private VisualizeController visualizeController;

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
            interrupt.giveUp(); //  simulation run by one click only
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

    // Stop simulation
    public void stopSimulation() {
        System.out.println("Stopping simulation...");

        // Stop DelayProcess
        if (delayProcess != null) {
            delayProcess.deregister();
            delayProcess = null;
        }

        // Stop any active interrupt
        if (interrupt != null) {
            interrupt.deregister();
            interrupt = null;
        }

        // Reset UI elements
        Platform.runLater(() -> {
            if (pauseButton != null) {
                pauseButton.setSelected(false);
                pauseButton.setText("Pause");
            }
            if (stepButton != null) {
                stepButton.setDisable(true);
            }
        });

        System.out.println("Simulation stopped");
    }
}
