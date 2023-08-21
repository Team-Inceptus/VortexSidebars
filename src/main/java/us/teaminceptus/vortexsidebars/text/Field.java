package us.teaminceptus.vortexsidebars.text;

import java.util.concurrent.Callable;

public class Field {
    private final Callable<Object> function;

    /**
     * @return the output of the function, or null if it cannot be called
     */
    public Object getValue() {
        try {
            return function.call();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Usage: new Field(() -> someFunction())
     * @param function the function to call when the output is requested
     */
    public Field(Callable<Object> function) {
        this.function = function;
    }
}
