import javafx.application.Application;
import simu.view.StepperTestView;
import simu.view.VisualizeTestView;
import simu.view.VisualizeView;

public class Launcher {

    void launchApp() {
        Application.launch(StepperTestView.class);
    }

    public static void main(String[] args) {
        new Launcher().launchApp();
    }
}
