package us.teaminceptus.vortexsidebars.text;

import org.jetbrains.annotations.NotNull;

public class FieldText extends Text {
    private final Field field;
    private Text prefix = new Text("");
    private Text suffix = new Text("");

    /**
     * @return the Text ("" if not set) before the Field
     */
    @NotNull
    public Text getPrefix() {
        return prefix;
    }

    /**
     * @param newPrefix the Text to put before the Field (e.g. "Players: ") when displaying
     */
    public void setPrefix(@NotNull Text newPrefix) {
        if (newPrefix == null) throw new IllegalArgumentException("newPrefix cannot be null");
        prefix = newPrefix;
    }

    /**
     * @return the Text ("" if not set) after the Field
     */
    @NotNull
    public Text getSuffix() { return suffix; }

    /**
     * @param newSuffix the Text to put after the Field (e.g. "/100") when displaying
     */
    public void setSuffix(@NotNull Text newSuffix) {
        if (newSuffix == null) throw new IllegalArgumentException("newSuffix cannot be null");
        suffix = newSuffix;
    }

    /**
     * @return the prefix + field + suffix
     */
    @Override
    public String getText() {
        return prefix.getText() + field.getValue() + suffix.getText();
    }

    /**
     * @return the Field without the prefix or suffix
     */
    @NotNull
    public Field getField() {
        return field;
    }

    /**
     * @param field the Field (automatically updates when the value changes)
     */
    public FieldText(@NotNull Field field) {
        super(String.valueOf(field.getValue()));
        this.field = field;
    }

    public FieldText(Field field, Text prefix, Text suffix) {
        super(String.valueOf(field.getValue()));
        this.field = field;
        this.prefix = prefix;
        this.suffix = suffix;
    }
}
