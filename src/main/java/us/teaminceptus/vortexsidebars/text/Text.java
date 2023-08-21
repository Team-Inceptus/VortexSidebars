package us.teaminceptus.vortexsidebars.text;

/**
 * Simple object that holds a String, used in order to allow for AnimatedText and FieldText
 */
public class Text {
    private String text;

    /**
     * @return the stored String
     */
    public String getText() { return text; }

    /**
     * Replaces the stored String
     * @param newText the new String
     */
    public void setText(String newText) { text = newText; }

    /**
     * @param text the String to store
     */
    public Text(String text) { this.text = text; }

    public boolean equals(Object compareObject) {
        if (compareObject instanceof Text) {
            if (((Text) compareObject).getText().equals(this.getText()))
                return compareObject.getClass() == this.getClass();
        }
        return false;
    }
}
