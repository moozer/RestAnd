package world.my.moz.restand;

import com.google.gson.Gson;

/**
 * Created by Shap on 29/10/2016.
 */

public class Converter {

    public String GpioToJson(Gpio gpio) {
        Gson gson = new Gson();
        String json = gson.toJson(gpio, gpio.getClass());
        return json;
    }

    public Gpio JsonToGpio (String json) {
        Gson gson = new Gson();
        Gpio gpio = null;
        gpio = gson.fromJson(json, gpio.getClass());
        return gpio;
    }

}
