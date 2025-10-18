# 🚗 Car-Garage Simulation (Java)
The Car Garage Simulation is a Java-based application that models the operations of a modern automotive garage. This object-oriented program demonstrates key Java concepts including inheritance, polymorphism, collections, and exception handling while simulating real-world garage operations.

# Overview
Car garages manage complex operations involving multiple vehicles, each requiring different services such as maintenance, repair, or inspection. Managing these operations manually can lead to inefficiency, errors and delayed service. The “Car Garage Simulation” project aims to digitally model these operations with specialized service.


# 🌟 Features
* Simulate customers arriving at the garage at fixed time intervals.  
* Process customers through **Customer Service**, **Inspection**, and **Maintenance**.  
* Maintenance includes **Tire Change, Oil Change, and Repair Work**.  
* Queue management for Inspection and Maintenance.  
* Re-inspection loop for cars that fail inspection.  
* Track when customers are served and exit the system. 


# Contribution instructions
1. Create a new branch.
2. Commit changes to the new branch created.
3. If you are done with your changes, create a pull request to the main branch, and describe your changes.
4. Wait for everyone's approval before merging your pull request.




# Flowchart how the Car-Garage Simulation works

```mermaid
flowchart LR
    nowhere1[" "] -- Customer arrives <br> every x minutes --> queue1[(" ")]
    queue1 --> customerService["Customer Service"]
    customerService -.-> needInspection{"Need Inspection?"}
    needInspection -- No --> queueMaintenance[(" ")]
    needInspection -- Yes --> queueInspection[(" ")]
    queueMaintenance --> maintenance["Maintenance"]
    queueInspection --> inspection("Inspection")
    maintenance --> tireQueue[(" ")] & oilQueue[(" ")] & repairQueue[(" ")]
    tireQueue --> tireChange("Tire Change")
    oilQueue --> oilChange("Oil Change")
    repairQueue --> repairWork("Repair Work")
    tireChange -.-> maintenanceDone{"Maintenance Done?"}
    oilChange -.-> maintenanceDone
    repairWork -.-> maintenanceDone
    maintenanceDone -. Yes .-> needInspection1{"Need Inspection?"}
    needInspection1 -- No --> customerServed("Customer Served")
    needInspection1 -- Yes --> queueInspection
    maintenanceDone -- No --> queueMaintenance
    inspection -.-> isPassed{"Is passed?"}
    isPassed -- Yes --> customerServed
    isPassed -- No --> queueMaintenance
    customerServed -- customer exit --> exit[" "]
    style nowhere1 width:0px
    style exit width:0px

```
```mermaid
flowchart TD
    start[" "] -- Start of simulation --> init["Initialize simulation:
        generate initial event at <b>random</b>"]
    init -. event .-> eventList[("EventList")]
    init --> A["<b>A Phase</b>
        Find time of next event
        and advance the clock to that time"]
    eventList -. "peek.getTime" .-> A
    A --> B["<b>B Phase</b>
        B events (Arrival || Departure);
        Schedule new Arrival B events at <b>random</b>;"]
    eventList -. poll .-> B
    B -. event .-> eventList
    B -. Customer .-> queue[("Queue")]
    B --> logic2{"Is all B event of the same clock time executed?"}
    logic2 -- Yes --> C["<b>C Phase</b>
        Remove customer from queue;
        Schedule Departure B event;"]
    logic2 -- No --> B
    queue -. poll .-> C
    C -. event .-> eventList
    C --> logic1{"Is Simulation Complete?"}
    logic1 -- Yes --> endofsim("End of simulation")
    logic1 -- No --> A
    
    style start height:0px
```
# Simulation Instructions
1. Download the .jar file from the latest [release](https://github.com/swostikl/Car-Garage-Simulation/releases).
2. Run the JAR file with JDK or JRE 21 ([get Temurin JDK or JRE version 21](https://adoptium.net/en-GB/temurin/releases?version=21&os=any&arch=any))
3. Open the Simulator and load the Setup Window.
4. Enter the required input Parameters.
5. Adjust the inspection failure rate and speed if necessary.
6. Click Run to start the simulation.
7. Observe the real-time process in the Simulation window.
8. Use the Stepper Control Window to pause, resume, or step through events.
9. Once the simulation completes, the result view will automatically appear when the simulation exits.
10. Interpret the performance data and adjust parameters for further experiment.
