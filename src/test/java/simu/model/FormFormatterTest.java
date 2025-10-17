package simu.model;

import org.junit.jupiter.api.Test;
import simu.model.Exceptions.ZeroValueException;

import static org.junit.jupiter.api.Assertions.*;

class FormFormatterTest {

    @Test
    void formatField() throws ZeroValueException {
        assertEquals(3.2, FormFormatter.formatField("3,2"), 0.01);
        assertEquals(5.3, FormFormatter.formatField("5.3"), 0.01);
        assertEquals(10.0, FormFormatter.formatField("10"), 0.01);
        assertThrows(ZeroValueException.class, () -> FormFormatter.formatField("0"));
        assertThrows(ZeroValueException.class, () -> FormFormatter.formatField(","));
        assertThrows(ZeroValueException.class, () -> FormFormatter.formatField(""));
    }
}