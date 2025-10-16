package simu.controller;

import eduni.distributions.Normal;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import simu.model.MyEngine;
import test.PrivateFieldAccess;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorSetupViewControllerTest {

    private SimulatorSetupViewController simulatorSetupViewController;

    @BeforeAll
    static void initJavaFX() throws Exception {
        new JFXPanel();
    }

    @BeforeEach
    void init() throws IOException, NoSuchFieldException, IllegalAccessException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/simulator_setup.fxml"));
        loader.load();
        simulatorSetupViewController = loader.getController();
    }

    @Test
    void startSimulationTest() throws Throwable {

        // arrange
        Button runProgram = PrivateFieldAccess.getPrivateField(simulatorSetupViewController, "runProgram");

        TextField arrivalMean = PrivateFieldAccess.getPrivateField(simulatorSetupViewController, "arrivalMean");
        TextField arrivalVariance = PrivateFieldAccess.getPrivateField(simulatorSetupViewController, "arrivalVariance");
        TextField totalTime = PrivateFieldAccess.getPrivateField(simulatorSetupViewController, "totalTime");
        TextField meanService = PrivateFieldAccess.getPrivateField(simulatorSetupViewController, "meanService");
        TextField serviceVariance = PrivateFieldAccess.getPrivateField(simulatorSetupViewController, "serviceVariance");
        Slider inspectionFailRateSlider = PrivateFieldAccess.getPrivateField(simulatorSetupViewController, "inspectionFailRateSlider");


        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> JFXThreadThrowables = new AtomicReference<>();
        Platform.runLater(() -> {
            try {
                // act
                arrivalMean.setText("5");
                arrivalVariance.setText("500");
                totalTime.setText("1000");
                meanService.setText("6");
                serviceVariance.setText("600");
                inspectionFailRateSlider.valueProperty().setValue(20);
                runProgram.fire();

                // assert
                assertTrue(runProgram.isDisable(), "Run button not disabled");
                MyEngine m = PrivateFieldAccess.getPrivateField(simulatorSetupViewController, "m");
                Normal arrivalNormalDistribution = PrivateFieldAccess.getPrivateField(m, "arrivalContinuousGenerator");
                Normal serviceNormalDistribution = PrivateFieldAccess.getPrivateField(m, "serviceContinuousGenerator");
                assertNotNull(arrivalNormalDistribution);
                assertNotNull(serviceNormalDistribution);

                double arrivalMeanVal = PrivateFieldAccess.getPrivateField(arrivalNormalDistribution, "mean");
                double arrivalSTDVal = PrivateFieldAccess.getPrivateField(arrivalNormalDistribution, "std_dev");
                double serviceMeanVal = PrivateFieldAccess.getPrivateField(serviceNormalDistribution, "mean");
                double serviceSTDVal = PrivateFieldAccess.getPrivateField(serviceNormalDistribution, "std_dev");
                assertNotNull(m);
                assertEquals(5.0, arrivalMeanVal, 0.01);
                assertEquals(Math.sqrt(500.0), arrivalSTDVal, 0.01);
                assertEquals(6.0, serviceMeanVal, 0.01);
                assertEquals(Math.sqrt(600.0), serviceSTDVal, 0.01);

            } catch (Throwable e) {
                JFXThreadThrowables.set(e);
            } finally {
                latch.countDown();
            }
        });

        latch.await();

        if (JFXThreadThrowables.get() != null) {
            throw JFXThreadThrowables.get();
        }
    }
}