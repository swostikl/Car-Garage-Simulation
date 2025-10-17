package simu.model;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InterruptTest {
// Test that Interrupt.run() properly calls await() from process
    @Test
    void runCallsAwait() {
        Interrupt interrupt = spy(new Interrupt(){
            @Override
            public void await(){
                // to prevent blocking
            }
        });
        interrupt.run();
        verify(interrupt, times(1)).await();
    }
}