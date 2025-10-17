package simu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simu.controller.VisualizeController;
import simu.model.servicePoints.ServicePointTypes;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class GuiUpdateThreadTest {
    private VisualizeController vcMock;
    private Map<ServicePointTypes, ServicePoint> servicePoints;

    @BeforeEach
    void setUp() {
        //mock controller
        vcMock = mock(VisualizeController.class);

        // Inject the mock into the listener (singleton)
        simu.listener.VisualizeControllerListener listener = simu.listener.VisualizeControllerListener.getInstance();
        listener.init(vcMock);

        // Mock service points
        servicePoints = new HashMap<>();
        for (ServicePointTypes type : ServicePointTypes.values()) {
            ServicePoint spMock = mock(ServicePoint.class);
            when(spMock.isReserved()).thenReturn(false);
            servicePoints.put(type, spMock);
            Customer.getTotalServed();
        }
    }

    @Test
    void run() {
        GuiUpdateThread thread = new GuiUpdateThread(servicePoints);
        thread.run(); // call directly

        // Verify queue labels
        verify(vcMock).setArrivalLabel(servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE));
        verify(vcMock).setMaintenanceQueuelabel(servicePoints.get(ServicePointTypes.MAINTENANCE));
        verify(vcMock).setTireChangeQueuelabel(servicePoints.get(ServicePointTypes.TIRE_CHANGE));
        verify(vcMock).setOilChangeQueuelabel(servicePoints.get(ServicePointTypes.OIL_CHANGE));
        verify(vcMock).setRepairworkQueueLabel(servicePoints.get(ServicePointTypes.OTHER_REPAIRS));
        verify(vcMock).setInspectionQueuelabel(servicePoints.get(ServicePointTypes.INSPECTION));

        //verify service labels
        verify(vcMock).setCustomerServicelabel(servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE));
        verify(vcMock).setMaintenancelabel(servicePoints.get(ServicePointTypes.MAINTENANCE));
        verify(vcMock).setTireChangeServicelabel(servicePoints.get(ServicePointTypes.TIRE_CHANGE));
        verify(vcMock).setOilChangeServicelabel(servicePoints.get(ServicePointTypes.OIL_CHANGE));
        verify(vcMock).setRepairWorklabel(servicePoints.get(ServicePointTypes.OTHER_REPAIRS));
        verify(vcMock).setInspectionServicelabel(servicePoints.get(ServicePointTypes.INSPECTION));

        // verify total served
        verify(vcMock).setCustomerServedlabel(0);

        // verify Occupied / Free colors
        verify(vcMock).setCustomerServiceOccupied(servicePoints.get(ServicePointTypes.CUSTOMER_SERVICE).isReserved());
        verify(vcMock).setMaintenanceServiceOccupied(servicePoints.get(ServicePointTypes.MAINTENANCE).isReserved());
        verify(vcMock).setTireChangeServiceOccupied(servicePoints.get(ServicePointTypes.TIRE_CHANGE).isReserved());
        verify(vcMock).setOilChangeServiceOccupied(servicePoints.get(ServicePointTypes.OIL_CHANGE).isReserved());
        verify(vcMock).setRepairWorkServiceOccupied(servicePoints.get(ServicePointTypes.OTHER_REPAIRS).isReserved());
        verify(vcMock).setInspectionServiceOccupied(servicePoints.get(ServicePointTypes.INSPECTION).isReserved());
    }
}