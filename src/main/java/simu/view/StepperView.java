package simu.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simu.controller.StepperViewController;

import java.io.IOException;

public class StepperView {

    private StepperViewController controller;

    public Stage init() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stepper.fxml"));
        Parent root = loader.load();
        controller = loader.getController();


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Simulation Control");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();

        return stage;
    }

    public StepperViewController getController() {
        return controller;
    }
}
