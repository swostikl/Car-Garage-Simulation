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
    private static File currentFile;
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
        if (file == null) {
            throw new CannotLoadFileException();
        }
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object loadedObject = ois.readObject();
            instance = new DataStore((DataStore) loadedObject);
            System.out.println("Data loaded from file: " + file.getAbsolutePath());
            currentFile = file;
        } catch (IOException | ClassNotFoundException e) {
            throw new CannotLoadFileException();
        }
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
        currentFile = null;
    }

    /**
     * Save the current DataStore instance to a specified file.
     *
     * @param file File to save the DataStore to
     */
    public void saveToFileAs(File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            System.out.println("Data saved to file: " + file.getAbsolutePath());
            currentFile = file;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the current DataStore instance to the last used file.
     *
     * @throws NoFileSetException if no file has been set yet
     */
    public void saveToFile() throws NoFileSetException {
        if (currentFile != null) {
            saveToFileAs(currentFile);
        } else {
            throw new NoFileSetException();
        }
    }

    /**
     * Get the list of ResultData.
     *
     * @return List of ResultData
     */
    public List<ResultData> getResultDataList() {
        return resultDataList;
    }

//    /**
//     * Initialize the DataStore singleton instance with the provided settings if it hasn't been initialized yet.
//     * @param settings SimulationSettings to initialize the DataStore with
//     */
//    public static void init(SimulationSettings settings) {
//        if (instance == null) {
//            instance = new DataStore(settings);
//        }
//    }

    /**
     * Get the simulation settings.
     *
     * @return SimulationSettings
     */
    public SimulationSettings getSimulationSettings() {
        return simulationSettings;
    }

    public void setSimulationSettings(SimulationSettings s) {
        this.simulationSettings = s;
    }

    /**
     * Add a ResultData entry to the list.
     *
     * @param data ResultData to be added
     */
    public void addResult(ResultData data) {
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
