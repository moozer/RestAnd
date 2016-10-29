package world.my.moz.restand;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Shap on 29/10/2016.
 */

public class ConverterTest {

    @Test
    public void jsonToGpio_isCorrect() throws Exception {

        Converter c = new Converter();
        Gpio expected = new Gpio();
        expected.setValue("0");
        Gpio result = c.JsonToGpio("{\"value\": \"0\"}");
        assertEquals(expected.getValue(),result.getValue());
    }

    @Test
    public void gpioToJson_isCorrect() throws Exception {

        Converter c = new Converter();
        String expected = "{\"value\":\"0\"}";

        Gpio gpio = new Gpio();
        gpio.setValue("0");

        String result = c.GpioToJson(gpio);
        assertEquals(expected,result);
    }



}
