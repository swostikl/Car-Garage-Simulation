package controller;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import simu.framework.EventList;
import simu.framework.Trace;
import simu.model.Customer;
import simu.model.ServicePoint;
import simu.model.servicePoints.CustomerServicePoint;
import simu.view.VisualizeView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class VisualizeControllerTest extends ApplicationTest {

    private VisualizeView vv;
    private VisualizeController vc;
    private ContinuousGenerator dummyContinuousGen = new Normal(1, 1);

    @Start
    public void start(Stage stage) throws IOException {
        Trace.setTraceLevel(Trace.Level.INFO);
        vv = new VisualizeView();
        vc = vv.init();
    }

    @Test
    void setArrivalLabel() {
        ServicePoint customerServicePoint = new CustomerServicePoint(dummyContinuousGen, new EventList());
//        customerServicePoint.addQueue(new Customer(dummyContinuousGen, 0, 0));
//        customerServicePoint.addQueue(new Customer(dummyContinuousGen, 0, 0));
        vc.setArrivalLabel(customerServicePoint);
        FxAssert.verifyThat(vc.getArrivalLabel(), LabeledMatchers.hasText("Queue: 0"));
    }
}