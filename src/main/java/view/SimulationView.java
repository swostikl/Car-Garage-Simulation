package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.awt.Toolkit;
import java.awt.Dimension;

import java.io.IOException;

public class SimulationView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulator_visual.fxml"));

        Parent root = loader.load();

        Group content = new Group(root);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        Scene scene = new Scene(scrollPane, dimension.getWidth() / 2, dimension.getHeight() / 2);

        // zoom factor
        double[] scale = {1.0};

        content.setOnScroll(event -> {
            if (event.isControlDown() || event.isShortcutDown() || event.isDirect()) {
                if (event.getDeltaY() > 0) {
                    scale[0] *= 1.1;
                } else {
                    scale[0] /= 1.1;
                }

                // clamp zoom
                scale[0] = Math.max(0.2, Math.min(scale[0], 5.0));

                root.setScaleX(scale[0]);
                root.setScaleY(scale[0]);
                event.consume();
            }
        });


        stage.setScene(scene);
        stage.show();

    }
}
