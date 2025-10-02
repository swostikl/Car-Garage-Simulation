package controller;

import eduni.distributions.Normal;
import simu.model.MyEngine;

public class FieldController {

    private MyEngine myEngine;

    public void setArrivalTime(String mean, String variance) {
        myEngine.setArrivalContinuousGenerator(new Normal(Double.parseDouble(mean), Double.parseDouble(variance)));
    }

    public void totalSimulationTime(String mean, String variance) {
        myEngine.setSimulationTime(Double.parseDouble(mean));
        myEngine.setServiceContinuousGenerator(new Normal(Double.parseDouble(mean), Double.parseDouble(variance)));

    }

    public void setInspecionFailRate(double inspecionFailRate) {
        myEngine.setInspectionFailRate(inspecionFailRate);
    }


    public void init(MyEngine myEngine) {
        this.myEngine = myEngine;
    }
}
