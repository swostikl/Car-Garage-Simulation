package simu.model;

import eduni.distributions.ContinuousGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import simu.framework.Trace;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private final ContinuousGenerator continuousGenerator = new ContinuousGenerator() {
        @Override
        public double sample() {
            return 4;
        }

        @Override
        public long getSeed() {
            return 0;
        }

        @Override
        public void setSeed(long seed) {

        }

        @Override
        public void reseed() {

        }
    };
    private Customer c;

    @BeforeAll
    static void beforeAll() {
        Trace.setTraceLevel(Trace.Level.INFO);
    }

    @Test
    void testMaintenanceCount() {
        c = new Customer(continuousGenerator, 0.5, 0.5);
        int count = 0;
        while (c.pollMaintenance() != null) {
            count++;
        }
        assertEquals(4, count);
    }

}