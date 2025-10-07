package simu.view;

import de.jangassen.MenuToolkit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.controller.SimulatorSetupViewController;
import simu.model.PlatformInfo;

import java.io.IOException;

public class SetupView extends Application {

    // create menubar
    private MenuBar menuBar;


    @Override
    public void start(Stage stage) throws IOException {
        MenuToolkit tk = MenuToolkit.toolkit();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/simulator_setup.fxml"));
        stage.setTitle("Setup");
        Parent parent = loader.load();
        SimulatorSetupViewController controller = loader.getController();
        controller.init();

        if (!PlatformInfo.getInstance().getIsMac()) {
            menuBar = controller.getMenuBar();
        } else {
            menuBar = new MenuBar();
        }

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
        fileMenu.getItems().addAll(newItem, openItem, new SeparatorMenuItem(), saveItem, saveAsItem);
        if (PlatformInfo.getInstance().getIsMac()) {

            // create Mac about menu
            Stage aboutView = new AboutView().aboutView();
            Menu aboutMenu = tk.createDefaultApplicationMenu("Car Garage Simulation", aboutView);
            aboutMenu.getItems().removeLast();
            MenuItem quitMenu = tk.createQuitMenuItem("Car Garage Simulation");
            quitMenu.setOnAction(event -> closeApp());
            aboutMenu.getItems().add(quitMenu);

            // add to menu bar
            menuBar.getMenus().addAll(aboutMenu, fileMenu);
        } else {
            menuBar.getMenus().add(fileMenu);
        }





        Scene scene = new Scene(parent);

        try {
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("/style.css not found");
            System.exit(2);
        }

        stage.setOnCloseRequest(SetupView::closeAppRequest);

        stage.setScene(scene);
        if (PlatformInfo.getInstance().getIsMac()) {
            tk.setMenuBar(stage, menuBar);
        }
        stage.show();
    }

    public static void closeAppRequest(WindowEvent event) {
        event.consume();
        closeApp();
    }

    public static void closeApp() {
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
