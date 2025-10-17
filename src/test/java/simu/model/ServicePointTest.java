package simu.model;

import eduni.distributions.ContinuousGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import simu.framework.Event;
import simu.framework.EventList;
import simu.framework.Trace;
import simu.model.servicePoints.CustomerServicePoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServicePointTest {

    @BeforeAll
    static void traceSetup() {
        Trace.setTraceLevel(Trace.Level.INFO);
    }

    @Test
    void whenMaintenanceNeeded() {
        // arrange
        ContinuousGenerator continuousGenerator = mock(ContinuousGenerator.class);
        when(continuousGenerator.sample()).thenReturn(10.0);
        EventList eventList = mock(EventList.class);
        Customer c = mock(Customer.class);
        when(c.needInspection()).thenReturn(false);
        when(c.pollMaintenance()).thenReturn(MaintenanceType.OIL);
        when(c.peekMaintenance()).thenReturn(MaintenanceType.OIL);
        CustomerServicePoint customerServicePoint = new CustomerServicePoint(continuousGenerator, eventList);
        ArgumentCaptor<Event> eventTypeArgumentCaptor = ArgumentCaptor.forClass(Event.class);

        // act
        customerServicePoint.getQueue().add(c);
        customerServicePoint.beginService();

        // assert
        verify(eventList).add(eventTypeArgumentCaptor.capture());
        EventType eventType = (EventType) eventTypeArgumentCaptor.getValue().getType();
        assertEquals(EventType.DEP_CS_MAINTENANCE, eventType);
    }

    @Test
    void whenInspectionNeeded() {
        // arrange
        ContinuousGenerator continuousGenerator = mock(ContinuousGenerator.class);
        when(continuousGenerator.sample()).thenReturn(10.0);
        EventList eventList = mock(EventList.class);
        Customer c = mock(Customer.class);
        when(c.needInspection()).thenReturn(true);
        when(c.pollMaintenance()).thenReturn(MaintenanceType.OIL);
        when(c.peekMaintenance()).thenReturn(MaintenanceType.OIL);
        CustomerServicePoint customerServicePoint = new CustomerServicePoint(continuousGenerator, eventList);
        ArgumentCaptor<Event> eventArgumentCaptor = ArgumentCaptor.forClass(Event.class);

        // act
        customerServicePoint.getQueue().add(c);
        customerServicePoint.beginService();
        verify(eventList).add(eventArgumentCaptor.capture());
        EventType eventType = (EventType) eventArgumentCaptor.getValue().getType();
        
        // assert
        assertEquals(EventType.DEP_CS_INSPECTION, eventType);
    }
}
