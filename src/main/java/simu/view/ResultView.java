package simu.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simu.controller.ResultViewController;
import simu.model.ResultData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultView {

    private List<ResultData> resultDataList;

    private ResultViewController controller;

//    @Override
//    public void start(Stage stage) throws IOException {
//        List<ResultData> results = new ArrayList<>();
//        results.add(new ResultData(1, 1, 1, 1, 1, 1, 1));
//        results.add(new ResultData(1, 1, 1, 1, 1, 1, 1));
//        initialize(results);
//    }

    public Stage initialize(List<ResultData> results) throws IOException {
        this.resultDataList = results;
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/result_view.fxml"));
        Parent parent = loader.load();
        controller = loader.getController();
        controller.init(resultDataList);
        stage.setTitle("Simulation Results");

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        return stage;
    }

    public void addResult(ResultData data) {
        resultDataList.add(data);
        if (controller != null) {
            controller.addResult(data);
        }
    }

    public ResultViewController getController() {
        return controller;
    }
}
