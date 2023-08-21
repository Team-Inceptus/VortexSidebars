package me.dragin.vortexsidebars.text;

public class FieldText extends Text {
    private final Field field;
    private String prefix = "";
    private String suffix = "";

    /**
     * @return the String ("" if not set) before the Field
     */
    public String getPrefix() { return prefix; }

    /**
     * @param newPrefix the String to put before the Field (e.g. "Players: ") when displaying
     */
    public void setPrefix(String newPrefix) { prefix = newPrefix; }

    /**
     * @return the String ("" if not set) after the Field
     */
    public String getSuffix() { return suffix; }

    /**
     * @param newSuffix the String to put after the Field (e.g. "/100") when displaying
     */
    public void setSuffix(String newSuffix) { suffix = newSuffix; }

    /**
     * @return the prefix + field + suffix
     */
    @Override
    public String getText() {
        return prefix + String.valueOf(field.getValue()) + suffix;
    }

    /**
     * @return the Field without the prefix or suffix
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field the Field (automatically updates when the value changes)
     */
    public FieldText(Field field) {
        super(String.valueOf(field.getValue()));
        this.field = field;
    }
}
