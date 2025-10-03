//package simu.controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Slider;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import simu.view.StepperView;
//
//import java.io.IOException;
//
//public class SimulatorSetupViewController {
//
//    @FXML
//    private Button applySimulationTime;
//
//    @FXML
//    private Button arrivalApply;
//
//    @FXML
//    private TextField arrivalMean;
//
//    @FXML
//    private TextField arrivalVariance;
//
//    @FXML
//    private Label delayLabel;
//
//    @FXML
//    private Button increaseSpeed;
//
//    @FXML
//    private Slider inspectionFailRate;
//
//    @FXML
//    private TextField meanService;
//
//    @FXML
//    private Button runProgram;
//
//    @FXML
//    private Button serviceApply;
//
//    @FXML
//    private TextField serviceVariance;
//
//    @FXML
//    private Button setDefault;
//
//    @FXML
//    private Button setSave;
//
//    @FXML
//    private Button slowSpeed;
//
//    @FXML
//    private TextField totalTime;
//
//    private StepperView stepperView;
//
//    @FXML
//    void onApplySimulationTime(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onArrivalApply(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onDecreaseSpeed(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onIncreaseSpeed(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onInspectionFailRate(MouseEvent event) {
//
//    }
//
//    @FXML
//    void onRunProgram(ActionEvent event) {
//        stepperView = new StepperView();
//        try {
//            Stage stage = stepperView.init();
//            runProgram.setDisable(true);
//            stage.showAndWait();
//            runProgram.setDisable(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void onServiceApply(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onSetDefault(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onSetSave(ActionEvent event) {
//
//    }
//
//}
package simu.controller;

import controller.FieldController;
import eduni.distributions.Normal;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import simu.framework.Engine;
import simu.framework.ProcessManager;
import simu.framework.Trace;
import simu.model.DelayProcess;
import simu.model.Exceptions.ZeroValueException;
import simu.model.MyEngine;
import simu.view.StepperView;
import simu.view.VisualizeView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SimulatorSetupViewController {

    private int currentDelay = 500;

    private boolean hasRun = false;
    private double inspectionFailRate;

    private StepperViewController stepperViewController;

    private ProcessManager pm;

    private final PauseTransition holdTimer = new PauseTransition(Duration.millis(800));

    private Thread delayUpdateThread;

    private final DecimalFormat df = new DecimalFormat("#.##");

    private final int DELAY_MIN = 100;
    private final int DELAY_MAX = 2000;

    @FXML
    private TextField arrivalMean;
    @FXML
    private TextField arrivalVariance;
    @FXML
    private TextField totalTime;
    @FXML
    private TextField meanService;
    @FXML
    private TextField serviceVariance;
    @FXML
    private Slider inspectionFailRateSlider;
    @FXML
    private Button runProgram;
    @FXML
    private Button setDefault;
    @FXML
    private Button setSave;
    @FXML
    private Button delayDecreaseButton;
    @FXML
    private Button delayIncreaseButton;
    @FXML
    private Label delayLabel;
    @FXML
    private Label inspectionFailRateLabel;

    @FXML
    void onRunProgram(ActionEvent event) throws IOException {
        if (!hasRun) {

            runProgram.setText("Running");
            runProgram.setDisable(true);
            hasRun = true;

            // visualization window first
            VisualizeView visualizeView = new VisualizeView();
            Stage visualizeStage = visualizeView.init();
            VisualizeController visualizeController = visualizeView.getController();

            //then stepper view
            StepperView stepperView = new StepperView();
            Stage stepperStage = stepperView.init();
            stepperStage.setTitle("Simulation Control");

            // to start the simulation through the stepper controller
            stepperViewController = stepperView.getController();
            try {
                startSimulation(visualizeController);
            } catch (ZeroValueException e) {
                runProgram.setText("Run");
                runProgram.setDisable(false);
                hasRun = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Fields cannot be empty or zero");
                stepperStage.close();
                visualizeStage.close();
                alert.showAndWait();
                return;
            }
            Trace.out(Trace.Level.INFO, "Simulation started - Run button now disabled");
        } else {
            Trace.out(Trace.Level.INFO, "Simulation already running - ignoring additional clicks");
        }
    }

    void onInspectionFailRateSlide() {
        inspectionFailRate = inspectionFailRateSlider.getValue();
        inspectionFailRateLabel.setText(String.format("%.2f%%", inspectionFailRate));
    }

    @FXML
    void onSetDefault(ActionEvent event) {
    }

    @FXML
    void onSetSave(ActionEvent event) {
    }

    @FXML
    void onDecreaseDelayPressed(MouseEvent event) {
        decreaseDelay();
        holdTimer.playFromStart();
        holdTimer.setOnFinished(e -> {
            delayUpdateThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        decreaseDelay();
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            });
            delayUpdateThread.start();
        });

    }

    @FXML
    void onDecreaseDelayReleased(MouseEvent event) {
        if (delayUpdateThread != null) {
            delayUpdateThread.interrupt();
        }
        holdTimer.stop();
    }

    @FXML
    void onIncreaseDelayPressed(MouseEvent event) {
        increaseDelay();
        holdTimer.playFromStart();
        holdTimer.setOnFinished(e -> {
            delayUpdateThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        increaseDelay();
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            });
            delayUpdateThread.start();
        });
    }

    @FXML
    void onIncreaseDelayReleased(MouseEvent event) {
        if (delayUpdateThread != null) {
            delayUpdateThread.interrupt();
        }
        holdTimer.stop();
    }

    public void startSimulation(VisualizeController visualizeController) throws ZeroValueException {
        pm = new ProcessManager();

        Trace.setTraceLevel(Trace.Level.INFO);

        Normal arrivalDistribution = new Normal(formatField(arrivalMean), formatField(arrivalVariance));
        MyEngine m = new MyEngine(visualizeController, arrivalDistribution);
        FieldController fieldController = new FieldController(m);

        // apply settings and format field
        fieldController.setServiceRequired(formatField(meanService), formatField(serviceVariance));
        fieldController.setInspectionFailRate(inspectionFailRate / 100);

        m.setSimulationTime(formatField(totalTime));
        m.setName("Main Simulation");
        pm.addProcess(m);
        stepperViewController.init(pm, currentDelay);

        Trace.out(Trace.Level.INFO, "Simulation engine and delay process added to ProcessManager");
    }

    public void init() {
        updateDelayLabel();
        df.setGroupingUsed(false);
        onInspectionFailRateSlide();
        inspectionFailRateSlider.valueProperty().addListener((o, oldVal, newVal) -> {
            onInspectionFailRateSlide();
        });
        arrivalMean.setTextFormatter(new TextFormatter<>(change -> {
            String nextText = change.getControlNewText();
            if (nextText.matches("\\d*([.,]\\d{0,2})?") && nextText.replaceAll("[.,]", "").length() <= 10) {
                return change;
            }
            return null;
        }));
        arrivalVariance.setTextFormatter(new TextFormatter<>(change -> {
            String nextText = change.getControlNewText();
            if (nextText.matches("\\d*([.,]\\d{0,2})?") && nextText.replaceAll("[.,]", "").length() <= 10) {
                return change;
            }
            return null;
        }));
        totalTime.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                int asInt = newText.isBlank() ? 0 : Integer.parseInt(newText);
                if (asInt < 1000000) {
                    return change;
                }
            }
            return null;
        }));
        meanService.setTextFormatter(new TextFormatter<>(change -> {
            String nextText = change.getControlNewText();
            if (nextText.matches("\\d*([.,]\\d{0,2})?") && nextText.replaceAll("[.,]", "").length() <= 10) {
                return change;
            }
            return null;
        }));
        serviceVariance.setTextFormatter(new TextFormatter<>(change -> {
            String nextText = change.getControlNewText();
            if (nextText.matches("\\d*([.,]\\d{0,2})?") && nextText.replaceAll("[.,]", "").length() <= 10) {
                return change;
            }
            return null;
        }));
    }

    private void updateDelayLabel() {
        Platform.runLater(() -> {
            delayLabel.setText(String.format("%dms", currentDelay));
        });
    }

    private void increaseDelay() {
        currentDelay = Math.min(currentDelay + 100, DELAY_MAX);
        if (currentDelay == DELAY_MAX) {
            delayIncreaseButton.setDisable(true);
        }
        delayDecreaseButton.setDisable(false);
        updateDelayLabel();
    }

    private void decreaseDelay() {
        currentDelay = Math.max(currentDelay - 100, DELAY_MIN);
        if (currentDelay == DELAY_MIN) {
            delayDecreaseButton.setDisable(true);
        }
        delayIncreaseButton.setDisable(false);
        updateDelayLabel();
    }

    private double formatField(TextField textField) throws ZeroValueException {
        String valText = textField.getText();
        if (!valText.isBlank()) {
            double doubleVal = Double.parseDouble(valText.replaceAll(",", "."));
            if (doubleVal <= 0) {
                Platform.runLater(() -> {
                    textField.getStyleClass().add("invalid");
                });
                throw new ZeroValueException();
            }
            textField.getStyleClass().remove("invalid");
            Platform.runLater(() -> {
                textField.setText(df.format(doubleVal));
            });
            return doubleVal;
        }
        Trace.out(Trace.Level.INFO, "Field '" + (textField.getId() != null ? textField.getId() : "unknown") + "' is blank, throwing ZeroValueException");
        Platform.runLater(() -> {
            textField.getStyleClass().add("invalid");
        });
        throw new ZeroValueException();
    }
}
