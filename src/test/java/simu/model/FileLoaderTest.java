package simu.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simu.model.Exceptions.CannotLoadFileException;
import simu.model.Exceptions.NoFileSetException;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileLoaderTest {

    private File tempFile; // creating temporary file for testing save/load operations

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("testDataStore", ".tmp");
        tempFile.deleteOnExit();
        DataStore.clearInstance();
    }

    @AfterEach
    void tearDown(){
        FileLoader.clearLoader(); // clears the loader state after each test
        DataStore.clearInstance();
    }

    @Test
    void saveToFile() {
        assertThrows(NoFileSetException.class, FileLoader::saveToFile);
    }

    @Test
    void loadFromFile() throws IOException, CannotLoadFileException {
        FileLoader.saveToFileAs(tempFile);
        DataStore loaded = FileLoader.loadFromFile(tempFile);
        assertNotNull(loaded, "Loaded DataStore should not be null");

    }

    @Test
    void saveToFileAs() throws IOException, CannotLoadFileException {
        assertDoesNotThrow(() -> FileLoader.saveToFileAs(tempFile));
    }


    @Test
    void clearLoader() {
        FileLoader.clearLoader();
        assertThrows(NoFileSetException.class, FileLoader::saveToFile);
    }
}