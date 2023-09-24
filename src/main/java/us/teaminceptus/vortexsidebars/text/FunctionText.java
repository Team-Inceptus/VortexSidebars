package us.teaminceptus.vortexsidebars.text;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

/**
 * Works as a text object but with a lambda.
 * Calls the function and updates accordingly every time
 * the value is requested.
 */
public class FunctionText extends Text {
    private Callable<String> function;
    private Text prefix;
    private Text suffix;

    /**
     * @return prefix, function and suffix put together.
     * If the function returns null or an error, it is ignored.
     */
    public String getText() {
        try {
            String funcValue = function.call();
            if (funcValue != null) {
                return prefix.getText() + funcValue + suffix.getText();
            } else return prefix.getText() + suffix.getText();
        } catch(Exception e) {
            return prefix.getText() + suffix.getText();
        }
    }

    @NotNull
    public Callable<String> getFunction() {
        return function;
    }
    public void setFunction(@NotNull Callable<String> newFunction) {
        if (newFunction == null) throw new IllegalArgumentException("newFunction cannot be null");
        function = newFunction;
    }

    @NotNull
    public Text getPrefix() { return prefix; }
    public void setPrefix(@NotNull Text newPrefix) {
        if (newPrefix == null) throw new IllegalArgumentException("newPrefix cannot be null");
        prefix = newPrefix;
    }
    @NotNull
    public Text getSuffix() { return suffix; }
    public void setSuffix(@NotNull Text newSuffix) {
        if (newSuffix == null) throw new IllegalArgumentException("newSuffix cannot be null");
        suffix = newSuffix;
    }

    /**
     * @param function the lambda to call (must return String).
     */
    public FunctionText(@NotNull Callable<String> function) {
        super("");
        this.function = function;
        prefix = new Text("");
        suffix = new Text("");
    }
    /**
     * @param function the lambda to call (must return String).
     * @param prefix the Text to put before the function.
     * @param suffix the Text to put after the function.
     */
    public FunctionText(Callable<String> function, Text prefix, Text suffix) {
        super("");
        this.function = function;
        this.prefix = prefix;
        this.suffix = suffix;
    }
}
