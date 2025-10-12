package simu.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simu.controller.ResultViewController;
import simu.model.DataStore;
import simu.model.ResultData;

import java.io.IOException;

/**
 * A view to display the simulation results
 */
public class ResultView {

    private static ResultView instance;
    private final ResultViewController controller;
    private final Stage currentStage;

    private ResultView() {
        Stage stage = new Stage();
        stage.setTitle("Results");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/result_view.fxml"));
        try {
            Parent parent = loader.load();
            controller = loader.getController();
            controller.init(DataStore.getInstance().getResultDataList());
            stage.setTitle("Simulation Results");

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            currentStage = stage;
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultView getInstance() {
        if (instance == null) {
            instance = new ResultView();
        } else {
            if (instance.getCurrentStage().isShowing()) {
                instance.getCurrentStage().toFront();
            } else {
                instance.getCurrentStage().show();
            }
        }
        return instance;
    }

    public static void clearTableView() {
        instance = null;
    }

    public void addResult(ResultData data) {
        DataStore.getInstance().addResult(data);
        if (controller != null) {
            controller.addResult(data);
        }
    }

    public ResultViewController getController() {
        return controller;
    }

    private Stage getCurrentStage() {
        return currentStage;
    }

    public void closeStage() {
        if (getCurrentStage() != null) {
            getCurrentStage().close();
        }
    }


}
