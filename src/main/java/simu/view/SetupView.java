package simu.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simu.controller.SimulatorSetupViewController;

import java.io.IOException;

public class SetupView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/simulator_setup.fxml"));
        Parent parent = loader.load();
        SimulatorSetupViewController controller = loader.getController();
        controller.init();
        Scene scene = new Scene(parent);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
