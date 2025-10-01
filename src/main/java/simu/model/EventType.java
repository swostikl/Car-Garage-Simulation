package simu.model;

import simu.framework.IEventType;

/**
 * Event types are defined by the requirements of the simulation model
 *
 */
public enum EventType implements IEventType {
    /**
     * Customer arrives to the customer service point
     */
    ARR_CUSTOMER_SERVICE,
    /**
     * Customer leaves customer service to be moved to maintenance
     */
    DEP_CS_MAINTENANCE,
    /**
     * Customer leaves customer service to be moved to inspection
     */
    DEP_CS_INSPECTION,
    /**
     * Customer leaves inspection to be moved to maintenance
     */
    DEP_INSPECTION_MAINTENANCE,
    /**
     * Customer leaves maintenance to be moved to tire repair
     */
    DEP_MAINTENANCE_TIRE,
    /**
     * Customer leaves maintenance to be moved to oil change
     */
    DEP_MAINTENANCE_OIL,
    /**
     * Customer leaves maintenance to be moved to other repairs
     */
    DEP_MAINTENANCE_OTHER,
    /**
     * Customer leaves tire change to be moved to inspection
     */
    DEP_TIRE_INSPECTION,
    /**
     * Customer leaves oil change to be moved to inspection
     */
    DEP_OIL_INSPECTION,
    /**
     * Customer leaves other repairs to be moved to inspection
     */
    DEP_OTHER_INSPECTION,
    /**
     * Customer leaves inspection to exit the simulation
     */
    DEP_INSPECTION_END,
    /**
     * Customer leaves tire change to exit the simulation
     */
    DEP_TIRE_END,
    /**
     * Customer leaves oil change to exit the simulation
     */
    DEP_OIL_END,
    /**
     * Customer leaves other repairs to exit the simulation
     */
    DEP_OTHER_END,
    /**
     * Customer leaves oil change to be moved to maintenance
     */
    DEP_OIL_MAINTENANCE,
    /**
     * Customer leaves tire change to be moved to maintenance
     */
    DEP_TIRE_MAINTENANCE,
    /**
     * Customer leaves other repairs to be moved to maintenance
     */
    DEP_OTHER_MAINTENANCE,
}
