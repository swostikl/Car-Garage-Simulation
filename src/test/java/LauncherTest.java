import javafx.application.Application;
import org.junit.jupiter.api.Test;
import simu.view.StepperView;

class LauncherTest {

    @Test
    void launchApp() {
        Application.launch(StepperView.class);
    }
}