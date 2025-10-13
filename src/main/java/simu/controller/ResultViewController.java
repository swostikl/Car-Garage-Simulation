package simu.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import simu.model.DataStore;
import simu.model.ResultData;

import java.util.List;

/**
 * Controller class for ResultView
 */
public class ResultViewController {

    @FXML
    private TableColumn<ResultData, String> cThroughputColumn;

    @FXML
    private TableColumn<ResultData, String> checkInColumn;

    @FXML
    private TableColumn<ResultData, String> inspectionColumn;

    @FXML
    private TableColumn<ResultData, String> maintenanceColumn;

    @FXML
    private TableColumn<ResultData, String> oilChangeColumn;

    @FXML
    private TableColumn<ResultData, String> repairColumn;

    @FXML
    private TableColumn<ResultData, String> tireChangeColumn;

    /*
    Data in ResultData class
    private double customerServiceUtilizationRate;
    private double maintenanceServiceUtilizationRate;
    private double tireChangeServiceUtilizationRate;
    private double oilChangeServiceUtilizationRate;
    private double repairServiceUtilizationRate;
    private double inspectionServiceUtilizationRate;
    private double customerThroughput;
     */

    /**
     * Initialize result view
     * @param results {@code ResultData} data
     */
    public void init(List<ResultData> results) {
        cThroughputColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerThroughput")
        );
        checkInColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerServiceUtilizationRate")
        );
        maintenanceColumn.setCellValueFactory(
                new PropertyValueFactory<>("maintenanceServiceUtilizationRate")
        );
        tireChangeColumn.setCellValueFactory(
                new PropertyValueFactory<>("tireChangeServiceUtilizationRate")
        );
        oilChangeColumn.setCellValueFactory(
                new PropertyValueFactory<>("oilChangeServiceUtilizationRate")
        );
        repairColumn.setCellValueFactory(
                new PropertyValueFactory<>("repairServiceUtilizationRate")
        );
        inspectionColumn.setCellValueFactory(
                new PropertyValueFactory<>("inspectionServiceUtilizationRate")
        );


        for (ResultData resultData : results) {
            addResult(resultData);
        }
    }

    /**
     * Method to add result to the result view
     * @param resultData {@code ResultData}
     */
    public void addResult(ResultData resultData) {
        DataStore.getInstance().addResult(resultData);
        cThroughputColumn.getTableView().getItems().add(resultData);
    }

    /**
     * Clear the table view
     */
    public void clearTableView() {
        Platform.runLater(() -> {
            cThroughputColumn.getTableView().getItems().clear();
        });
    }

}
