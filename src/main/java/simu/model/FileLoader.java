package simu.model;

import simu.model.Exceptions.CannotLoadFileException;
import simu.model.Exceptions.NoFileSetException;

import java.io.*;

public class FileLoader {

    private static File currentFile;
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

    public static void saveToFileAs(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(DataStore.getInstance());
            System.out.println("Data saved to file: " + file.getAbsolutePath());
            currentFile = file;
        }
    }

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

    public static void clearLoader() {
        currentFile = null;
    }
}
