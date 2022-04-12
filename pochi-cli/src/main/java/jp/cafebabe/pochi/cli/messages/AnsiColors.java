package jp.cafebabe.pochi.cli.messages;

/**
 * @see <a href="https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println">https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println</a>
 * @author Haruaki Tamada
 */
public enum AnsiColors {
    // Reset
    RESET("\033[0m"),  // Text Reset
    // Regular Colors
    BLACK("\033[0;30m"),   // BLACK
    RED("\033[0;31m"),     // RED
    GREEN("\033[0;32m"),   // GREEN
    YELLOW("\033[0;33m"),  // YELLOW
    BLUE("\033[0;34m"),    // BLUE
    PURPLE("\033[0;35m"),  // PURPLE
    CYAN("\033[0;36m"),    // CYAN
    WHITE("\033[0;37m"),   // WHITE
    // Bold
    BLACK_BOLD("\033[1;30m"),  // BLACK
    RED_BOLD("\033[1;31m"),    // RED
    GREEN_BOLD("\033[1;32m"),  // GREEN
    YELLOW_BOLD("\033[1;33m"), // YELLOW
    BLUE_BOLD("\033[1;34m"),   // BLUE
    PURPLE_BOLD("\033[1;35m"), // PURPLE
    CYAN_BOLD("\033[1;36m"),   // CYAN
    WHITE_BOLD("\033[1;37m"),  // WHITE
    // Background
    BLACK_BACKGROUND("\033[40m"),  // BLACK
    RED_BACKGROUND("\033[41m"),    // RED
    GREEN_BACKGROUND("\033[42m"),  // GREEN
    YELLOW_BACKGROUND("\033[43m"), // YELLOW
    BLUE_BACKGROUND("\033[44m"),   // BLUE
    PURPLE_BACKGROUND("\033[45m"), // PURPLE
    CYAN_BACKGROUND("\033[46m"),   // CYAN
    WHITE_BACKGROUND("\033[47m");  // WHITE

    private String code;

    AnsiColors(String code) {
        this.code = code;
    }

    public String decorate(String message) {
        return new StringBuilder().append(code)
                .append(message).append(RESET.code)
                .toString();
    }

    public String decoratef(String formatter, Object... labels) {
        return decorate(String.format(formatter, labels));
    }

    public static String decorate(AnsiColors color, String message) {
        return color.decorate(message);
    }

    public static String decoratef(AnsiColors color, String formatter, Object... labels) {
        return color.decoratef(formatter, labels);
    }
}
