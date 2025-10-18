package simu.model;

import simu.model.Exceptions.ZeroValueException;

/**
 * Utility class for validating and formatting numeric input values from text fields.
 */
public class FormFormatter {
    /**
     * Method used to format string in {@link javafx.scene.control.TextField} into double
     *
     * @param valText the string representation of the numeric value (e.g. "10.5" or "1,14")
     * @return the parsed {@code double} value
     * @throws ZeroValueException when the value is zero or contains only decimal point
     */
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
