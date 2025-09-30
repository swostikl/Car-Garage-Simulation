import javafx.application.Application;
import simu.view.StepperTestView;
import simu.view.VisualizeTestView;

public class Launcher {

    void launchApp() {
        Application.launch(VisualizeTestView.class);
    }

    public static void main(String[] args) {
        new Launcher().launchApp();
    }
}
