package simu.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simu.controller.StepperTestViewController;
import simu.framework.ProcessManager;

import java.io.IOException;

public class StepperTestView extends Application {

    private StepperTestViewController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stepper_test.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        controller.init(new Object(), new ProcessManager());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
