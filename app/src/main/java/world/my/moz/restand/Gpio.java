package world.my.moz.restand;

/**
 * Created by Shap on 29/10/2016.
 */

public class Gpio {

    private String value;

    public Gpio() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "Gpio [value=" + value + "]";
    }

}
