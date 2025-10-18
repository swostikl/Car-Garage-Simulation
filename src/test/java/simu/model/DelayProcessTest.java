package simu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import simu.framework.ProcessManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class DelayProcessTest {
    private DelayProcess delayProcess;
    private ProcessManager pmMock;

    @BeforeEach
    void setUp() {
        //Spy on DelayProcess to verify method calls
        delayProcess = spy(new DelayProcess(500) {
            @Override
            public void await() {
                //no-op for testing
            }

            @Override
            public void giveUp() {
                // no-op for testing
            }
        });
        pmMock = Mockito.mock(ProcessManager.class);  //mock process manager
        delayProcess.setPm(pmMock);
    }

    @Test
    void setDelayTest() {
        delayProcess.setDelay(1000);
        assertEquals(1000, delayProcess.getDelayMs(), "Delay should be updated correctly.");
    }

    @Test
    void getDelayMsTest() {
        assertEquals(500, delayProcess.getDelayMs(), " Initial delay should match constructor");
    }


    @Test
    void testDeregister() {
        assertDoesNotThrow(() -> delayProcess.deregister(), "Stop delay should not throw any exceptions");
    }

    @Test
    void testRunTerminateAfterStop() throws InterruptedException {
        ProcessManager pm = new ProcessManager();
        pm.addProcess(delayProcess);
        delayProcess.deregister();
        assertFalse(pm.containProcess(delayProcess));
    }

}