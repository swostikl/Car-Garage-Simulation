import javafx.application.Application;
import simu.view.StepperView;


public class Launcher {

    void launchApp() {
        Application.launch(StepperView.class);
    }

    public static void main(String[] args) {
        new Launcher().launchApp();
    }
}

