package simu.model;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import simu.framework.Trace;
import simu.model.Exceptions.ZeroValueException;

import java.text.DecimalFormat;


public class FormFormatter {

    private static final DecimalFormat df = new DecimalFormat("#.##");
    public static double formatField(String valText) throws ZeroValueException {
        if (!valText.isBlank()) {
            if (valText.equals(".") || valText.equals(",")) {
                throw new ZeroValueException();
            }
            double doubleVal = Double.parseDouble(valText.replaceAll(",", "."));
            if (doubleVal <= 0) {
                throw new ZeroValueException();
            }
            return doubleVal;
        }
        throw new ZeroValueException();
    }
}
