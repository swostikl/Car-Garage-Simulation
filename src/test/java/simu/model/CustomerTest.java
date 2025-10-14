package simu.model;

import eduni.distributions.ContinuousGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simu.controller.VisualizeController;
import simu.framework.ArrivalProcess;
import simu.framework.EventList;
import simu.framework.Trace;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer c;

    private final ContinuousGenerator continuousGenerator = new ContinuousGenerator() {
        @Override
        public double sample() {
            return 4;
        }

        @Override
        public void setSeed(long seed) {

        }

        @Override
        public long getSeed() {
            return 0;
        }

        @Override
        public void reseed() {

        }
    };

    @BeforeAll
    static void beforeAll() {
        Trace.setTraceLevel(Trace.Level.INFO);
    }

    @BeforeEach
    void beforeEach() {
        c = new Customer(continuousGenerator, 0.5, 0.5);
    }

    @Test
    void testMaintenanceCount() {
        System.out.println("-".repeat(15) + "Testing Maintenance Count" + "-".repeat(15));
        int count = 0;
        while (c.pollMaintenance() != null) {
            count++;
        }
        assertEquals(4, count);
    }

}