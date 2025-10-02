package simu.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simu.controller.StepperViewController;

import java.io.IOException;

public class StepperView extends Application {

    private StepperViewController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stepper.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        controller.init();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

