package simu.model;

import simu.model.Exceptions.CannotLoadFileException;
import simu.model.Exceptions.NoFileSetException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A data class used to store data
 */
public class DataStore implements Serializable {

    private static DataStore instance = null;
    private final List<ResultData> resultDataList;
    private SimulationSettings simulationSettings;

    private DataStore(DataStore entity) {
        this.resultDataList = entity.resultDataList;
        this.simulationSettings = entity.simulationSettings;
    }

    private DataStore() {
        this.resultDataList = new ArrayList<>();
        this.simulationSettings = null;
    }

    private DataStore(SimulationSettings simulationSettings) {
        this.resultDataList = new ArrayList<>();
        this.simulationSettings = simulationSettings;
    }

    /**
     * Load DataStore from a file and set it as the singleton instance.
     *
     * @param file File to load the DataStore from
     */
    public static void loadFromFile(File file) throws CannotLoadFileException {
        instance = new DataStore(FileLoader.loadFromFile(file));
    }

    /**
     * Get the singleton instance of DataStore.
     *
     * @return DataStore instance
     */
    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public static void clearInstance() {
        instance = null;
        FileLoader.clearLoader();
    }

    /**
     * Save the current DataStore instance to a specified file.
     *
     * @param file File to save the DataStore to
     */
    public synchronized void saveToFileAs(File file) {
        try {
            FileLoader.saveToFileAs(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the current DataStore instance to the last used file.
     *
     * @throws NoFileSetException if no file has been set yet
     */
    public synchronized void saveToFile() throws NoFileSetException {
        FileLoader.saveToFile();
    }

    /**
     * Get the list of ResultData.
     *
     * @return List of ResultData
     */
    public synchronized List<ResultData> getResultDataList() {
        return resultDataList;
    }

    /**
     * Get the simulation settings.
     *
     * @return SimulationSettings
     */
    public synchronized SimulationSettings getSimulationSettings() {
        return simulationSettings;
    }

    public synchronized void setSimulationSettings(SimulationSettings s) {
        this.simulationSettings = s;
    }

    /**
     * Add a ResultData entry to the list.
     *
     * @param data ResultData to be added
     */
    public synchronized void addResult(ResultData data) {
        resultDataList.add(data);
    }

    /**
     * Remove a ResultData entry from the list.
     *
     * @param data ResultData to be removed
     */
    public void removeResult(ResultData data) {
        resultDataList.remove(data);
    }
}
