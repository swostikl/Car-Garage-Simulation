package simu.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.controller.SimulatorSetupViewController;
import simu.model.DataStore;

import java.io.IOException;

public class SetupView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/simulator_setup.fxml"));
        Parent parent = loader.load();
        SimulatorSetupViewController controller = loader.getController();
        controller.init();

        // create menubar
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");

        KeyCodeCombination openShortcut = new KeyCodeCombination(javafx.scene.input.KeyCode.O, KeyCombination.SHORTCUT_DOWN);
        KeyCodeCombination saveShortcut = new KeyCodeCombination(javafx.scene.input.KeyCode.S, KeyCombination.SHORTCUT_DOWN);
        KeyCodeCombination newShortcut = new KeyCodeCombination(javafx.scene.input.KeyCode.N, KeyCombination.SHORTCUT_DOWN);
        KeyCodeCombination saveAsShortcut = new KeyCodeCombination(javafx.scene.input.KeyCode.S, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN);

        openItem.setAccelerator(openShortcut);
        saveItem.setAccelerator(saveShortcut);
        newItem.setAccelerator(newShortcut);
        saveAsItem.setAccelerator(saveAsShortcut);

        openItem.setOnAction(event -> {
            controller.handleOpen(stage);
        });
        saveItem.setOnAction(event -> {
            controller.handleSave(stage);
        });
        saveAsItem.setOnAction(event -> {
            controller.handleSaveAs(stage);
        });
        newItem.setOnAction(event -> {
            controller.handleNew();
        });

        // add items to menu
        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem);
        menuBar.getMenus().addAll(fileMenu);

        VBox vBox = new VBox(menuBar, parent);


        Scene scene = new Scene(vBox);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setOnCloseRequest(SetupView::closeApp);

        stage.setScene(scene);
        stage.show();
    }

    public static void closeApp(WindowEvent event) {
        event.consume();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Confirmation");
        alert.setHeaderText("Do you really want to quit the app?");
        alert.setContentText("Any unsaved data will be lost.");
        alert.showAndWait().ifPresent(response -> {
            // Close only if user confirms
            if (response == ButtonType.OK) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
