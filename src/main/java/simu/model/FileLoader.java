package simu.model;

import simu.model.Exceptions.CannotLoadFileException;
import simu.model.Exceptions.NoFileSetException;

import java.io.*;

/**
 * Utility class for saving and loading {@link DataStore} objects from the file
 */
public class FileLoader {

    private static File currentFile;

    /**
     * Loads a {@link DataStore} object from a specified file
     * @param file the file to load data from
     * @return the deserialized {@link DataStore} object
     * @throws CannotLoadFileException if the file is null or an error occurs during loading
     */
    public static DataStore loadFromFile(File file) throws CannotLoadFileException {
        if (file == null) {
            throw new CannotLoadFileException();
        }
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object loadedObject = ois.readObject();
            DataStore dataStore = (DataStore) loadedObject;
            System.out.println("Data loaded from file: " + file.getAbsolutePath());
            currentFile = file;
            return dataStore;
        } catch (IOException | ClassNotFoundException e) {
            throw new CannotLoadFileException();
        }
    }

    /**
     * Saves the current {@link DataStore} instance to the specified file
     *
     * @param file the file to which data will be saved
     * @throws IOException if an I/O error occurs while writing to the file
     */

    public static void saveToFileAs(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(DataStore.getInstance());
            System.out.println("Data saved to file: " + file.getAbsolutePath());
            currentFile = file;
        }
    }

    /**
     * Saves the current {@link DataStore} instance to the last used file
     *
     * @throws NoFileSetException if no file has been set to save to, or if an I/O error occurs during saving
     */

    public static void saveToFile() throws NoFileSetException {
        if (currentFile != null) {
            try {
                saveToFileAs(currentFile);
            } catch (IOException e) {
                throw new NoFileSetException();
            }
        } else {
            throw new NoFileSetException();
        }
    }

    /**
     * Clears the current file reference.
     */
    public static void clearLoader() {
        currentFile = null;
    }
}
