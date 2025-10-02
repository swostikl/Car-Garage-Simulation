//package simu.view;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import simu.controller.StepperViewController;
//
//import java.io.IOException;
//
//// don't worry about the 1 related problem, it is from Unit Test
//public class StepperView {
//
//    private StepperViewController controller;
//
//    /**
//     * Create and display stepper view
//     * @Note you can pass parameters here if needed
//     * @throws IOException
//     */
//    public Stage init() throws IOException {
//        Stage stage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stepper.fxml"));
//        Parent root = loader.load();
//        controller = loader.getController();
//        controller.init();
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        return stage;
//    }
//
//    public StepperViewController getController() {
//        return controller;
//    }
//}

package simu.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simu.controller.StepperViewController;
import java.io.IOException;

public class StepperView {

    private StepperViewController controller;

    /**
     * Create and display stepper view
     * @throws IOException
     */
    public Stage init() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stepper.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        controller.init();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return stage;
    }

    public StepperViewController getController() {
        return controller;
    }
}
