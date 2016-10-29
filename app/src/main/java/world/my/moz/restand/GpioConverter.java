package world.my.moz.restand;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Shap on 29/10/2016.
 */

public class GpioConverter {

    public String GpioToJson(Gpio gpio) {
        Gson gson = new Gson();
        Type type = new TypeToken<Gpio>() {}.getType();
        String json = gson.toJson(gpio, type);
        return json;
    }

    public Gpio JsonToGpio (String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Gpio>() {}.getType();
        Gpio gpio = gson.fromJson(json, type);
        return gpio;
    }

}

