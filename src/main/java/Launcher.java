import javafx.application.Application;
import simu.view.StepperTestView;


public class Launcher {

    void launchApp() {
        Application.launch(StepperTestView.class);
    }

    public static void main(String[] args) {
        new Launcher().launchApp();
    }
}

