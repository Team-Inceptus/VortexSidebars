package us.teaminceptus.vortexsidebars.text;

/**
 * An alternative to ChatColor that allows custom hex colors
 */
public interface TextColor {
    String DARK_RED = "§4";
    String RED = "§c";
    String GOLD = "§6";
    String YELLOW = "§e";
    String DARK_GREEN = "§2";
    String GREEN = "§a";
    String AQUA = "§b";
    String CYAN = "§3";
    String DARK_BLUE = "§1";
    String BLUE = "§9";
    String PINK = "§d";
    String PURPLE = "§5";
    String WHITE = "§f";
    String GRAY = "§7";
    String DARK_GRAY = "§8";
    String BLACK = "§0";
    String BOLD = "§l";
    String ITALIC = "§o";
    String UNDERLINE = "§n";
    String STRIKETHROUGH = "§m";
    String OBFUSCATE = "§k";

    /**
     * Converts a Hex Code to a Color Code.
     * @param hex the hex code to use, should be 6 characters long with no #, not case-sensitive
     * @return the color code for the hex code, returns black if the hex code provided was in an invalid format
     */
    static String fromHex(String hex) {
        hex = hex.toLowerCase();
        if (hex.length() == 6) {
            StringBuilder output = new StringBuilder("§x");
            for (Character character : hex.toCharArray()) {
                output.append("§").append(character);
            }
            return output.toString();
        } else {
            return BLACK;
        }
    }
}
