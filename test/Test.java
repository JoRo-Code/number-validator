import src.validator.Validator;
import src.validator.number.Persnumber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {

    private static int passedTests;
    private static int numberOfTests;
    private static Validator validator = new Validator();

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";



    private static void printStatistics() {
        int failedTests = numberOfTests - passedTests;
        if(failedTests == 0) {
            System.out.println(ANSI_GREEN + "All tests passed!" + ANSI_RESET);
        } else {
            System.out.println("Passed " + passedTests + "/" + numberOfTests + " tests");
            System.out.println(ANSI_RED + "Failed " + failedTests + " test" + (failedTests == 1? "":"s") + " (see above)" + ANSI_RESET);
        }
    }

    private static void test(boolean actual, boolean expected) {
        numberOfTests++;

        if (actual == expected) {
            passedTests++;
            System.out.println(ANSI_GREEN + "- Passed" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + "- Failed" + ANSI_RESET);
        }
    }

    private static List<String> readFromFile(String path){
        //https://stackoverflow.com/questions/5343689/java-reading-a-file-into-an-arraylist
        
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
            return lines;
        }
        catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage() + " doesn't exist");

        }
        // Should not reach this point
        return null; //Cheat
    }

    private static void persnumberTests() {
        
        // valid
        System.out.println("Valid persnumber");
        List<String> strings = readFromFile("test/input/validPersnumber.txt");
        for (String str: strings) {
            Persnumber p = new Persnumber(str);
            boolean result = p.isValid();
            System.out.println(str);
            test(result, true);
        }
        
        // invalid
        System.out.println("Invalid persnumber");
        strings = readFromFile("test/input/invalidPersnumber.txt");
        for (String str: strings) {
            Persnumber p = new Persnumber(str);
            boolean result = p.isValid();
            System.out.println(str);
            test(result, false);
        }
    }


    private static void runTests() {
        persnumberTests();
        // TODO: add more tests

    } 


    public static void main(String[] args) {
        runTests();
        printStatistics();

    
    }
    
}