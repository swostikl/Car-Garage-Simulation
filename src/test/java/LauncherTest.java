import javafx.application.Application;
import org.junit.jupiter.api.Test;
import simu.view.StepperTestView;

class LauncherTest {

    @Test
    void launchApp() {
        Application.launch(StepperTestView.class);
    }
}