package simu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import simu.view.StepperView;

import java.io.IOException;

public class SimulatorSetupViewController {

    @FXML
    private Button applySimulationTime;

    @FXML
    private Button arrivalApply;

    @FXML
    private TextField arrivalMean;

    @FXML
    private TextField arrivalVariance;

    @FXML
    private Label delayLabel;

    @FXML
    private Button increaseSpeed;

    @FXML
    private Slider inspectionFailRate;

    @FXML
    private TextField meanService;

    @FXML
    private Button runProgram;

    @FXML
    private Button serviceApply;

    @FXML
    private TextField serviceVariance;

    @FXML
    private Button setDefault;

    @FXML
    private Button setSave;

    @FXML
    private Button slowSpeed;

    @FXML
    private TextField totalTime;

    private StepperView stepperView;

    @FXML
    void onApplySimulationTime(ActionEvent event) {

    }

    @FXML
    void onArrivalApply(ActionEvent event) {

    }

    @FXML
    void onDecreaseSpeed(ActionEvent event) {

    }

    @FXML
    void onIncreaseSpeed(ActionEvent event) {

    }

    @FXML
    void onInspectionFailRate(MouseEvent event) {

    }

    @FXML
    void onRunProgram(ActionEvent event) {
        stepperView = new StepperView();
        try {
            Stage stage = stepperView.init();
            runProgram.setDisable(true);
            stage.showAndWait();
            runProgram.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onServiceApply(ActionEvent event) {

    }

    @FXML
    void onSetDefault(ActionEvent event) {

    }

    @FXML
    void onSetSave(ActionEvent event) {

    }

}
