package simu.model;

import simu.framework.IEventType;

/**
 * Event types are defined by the requirements of the simulation model
 *
 * TODO: This must be adapted to the actual simulator
 */
public enum EventType implements IEventType {
    ARR_CUSTOMER_SERVICE,
    DEP_CS_MAINTENANCE,
    DEP_CS_INSPECTION,
    DEP_INSPECTION_MAINTENANCE,
    DEP_MAINTENANCE_TIRE,
    DEP_MAINTENANCE_OIL,
    DEP_MAINTENANCE_OTHER,
    DEP_TIRE_INSPECTION,
    DEP_OIL_INSPECTION,
    DEP_OTHER_INSPECTION,
    DEP_INSPECTION_END,
    DEP_END
}
