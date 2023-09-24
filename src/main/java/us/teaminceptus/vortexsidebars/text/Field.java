package us.teaminceptus.vortexsidebars.text;

import java.util.concurrent.Callable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a Field that holds an output.
 * @deprecated Use FunctionText instead.
 * <p>
 *     Note: there are no current plans to remove Field or FieldText.
 * </p>
 */
@Deprecated
public class Field {
    private final Callable<Object> function;

    /**
     * Fetches the output of the function.
     * @return the output of the function, or null if it cannot be called
     */
    @Nullable
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
    public Field(@NotNull Callable<Object> function) {
        if (function == null) throw new IllegalArgumentException("function cannot be null");
        this.function = function;
    }
}
