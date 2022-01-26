package src.utils;

public class Color {

    // Colors
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";


    public static final String Green(String str) {
        return ANSI_GREEN + str + ANSI_RESET;
    }
    
    public static final String Red(String str) {
        return ANSI_RED + str + ANSI_RESET;
    }
    
}
