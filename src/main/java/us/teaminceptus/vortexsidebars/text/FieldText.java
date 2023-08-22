package us.teaminceptus.vortexsidebars.text;

public class FieldText extends Text {
    private final Field field;
    private Text prefix = new Text("");
    private Text suffix = new Text("");

    /**
     * @return the String ("" if not set) before the Field
     */
    public Text getPrefix() { return prefix; }

    /**
     * @param newPrefix the String to put before the Field (e.g. "Players: ") when displaying
     */
    public void setPrefix(Text newPrefix) { prefix = newPrefix; }

    /**
     * @return the String ("" if not set) after the Field
     */
    public Text getSuffix() { return suffix; }

    /**
     * @param newSuffix the String to put after the Field (e.g. "/100") when displaying
     */
    public void setSuffix(Text newSuffix) { suffix = newSuffix; }

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

    public FieldText(Field field, Text prefix, Text suffix) {
        super(String.valueOf(field.getValue()));
        this.field = field;
        this.prefix = prefix;
        this.suffix = suffix;
    }
}
