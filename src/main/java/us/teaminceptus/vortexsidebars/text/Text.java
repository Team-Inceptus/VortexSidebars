package us.teaminceptus.vortexsidebars.text;

/**
 * Simple object that holds a String, used in order to allow for AnimatedText and FieldText
 */
public class Text {

    private String text;

    /**
     * Fetches the stored Text.
     * @return the stored String
     */
    public String getText() { return text; }

    /**
     * Replaces the stored Text.
     * @param newText the new String
     */
    public void setText(String newText) { text = newText; }

    /**
     * Constructs a new Text Object.
     * @param text the String to store
     */
    public Text(String text) { this.text = text; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Text) {
            if (((Text) obj).getText().equals(this.getText()))
                return obj.getClass() == this.getClass();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
