package simu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import simu.framework.ProcessManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;

class DelayProcessTest {
    private DelayProcess delayProcess;
    private ProcessManager pmMock;

    @BeforeEach
    void setUp() {
        //Spy on DelayProcess to verify method calls
        delayProcess = spy (new DelayProcess(500){
            @Override
            public void await(){
                //no-op for testing
            }
            @Override
            public void giveUp(){
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
    void getDelayMsTest(){
        assertEquals(500, delayProcess.getDelayMs() , " Initial delay should match constructor");
    }


    @Test
    void testStopDelay() {
        assertDoesNotThrow(()-> delayProcess.stopDelay(),"Stop delay should not throw any exceptions");
    }


    @Test
    void testStopDelayCallsDeregister() {
        // prevent actual logic of deregister
        doNothing().when(delayProcess).deregister();

        delayProcess.stopDelay();

        // verify that deregister() is called exactly once
        verify(delayProcess, times(1)).deregister();
    }

    @Test
    void testRunTerminateAfterStop() throws InterruptedException{
        Thread t = new Thread(delayProcess::run);
        t.start();

        Thread.sleep(200);  // let it run in a bit
        delayProcess.stopDelay(); // stop process
        t.join(1000);  // wait for terminate
        assertFalse(t.isAlive(), " Thread should be stop after stopDelay() is called ");
    }

}