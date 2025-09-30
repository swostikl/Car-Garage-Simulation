package simu.controller;

import controller.VisualizeController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import simu.framework.Engine;
import simu.framework.ProcessManager;
import simu.framework.Trace;
import simu.model.Interrupt;
import simu.model.MyEngine;
import simu.view.VisualizeView;

import java.io.IOException;

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

    private VisualizeView view;

    private VisualizeController visualizeController;

    @FXML
    void onPressRun(ActionEvent event) throws IOException {
        Trace.setTraceLevel(Trace.Level.INFO);

        Engine m = new MyEngine(lock, pm);
        m.setSimulationTime(100000);
        m.setName("Main Simulation");
        pm.addProcess(m);
        view = new VisualizeView();
        visualizeController = view.init();
    }

    @FXML
    void onPausePressed() {
        if (pauseButton.isSelected()) {
            interrupt = new Interrupt(lock, pm);
            interrupt.setName("INTERRUPT SIMULATION");
            stepButton.setDisable(false);
            pm.addProcess(interrupt);
//            Platform.runLater(() -> {
//                visualizeController.tireChangeServicelabel.setText("0");
//            });
        } else {
            stepButton.setDisable(true);
            if (interrupt != null) {
                interrupt.deregister();
                interrupt = null;
            }
//            Platform.runLater(() -> {
//                visualizeController.tireChangeServicelabel.setText("I AM STARTING");
//            });
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
