import src.validator.Validator;
import src.validator.number.Persnumber;
import src.validator.number.InvalidNumberException;
import src.validator.number.Number;

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
            System.out.println("Passed " + passedTests + "/" + numberOfTests + " test" + (failedTests == 1? "":"s"));
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

    private static void parserTests() {
    System.out.println("Parser tests");
    // Valid
        System.out.println("Valid input tests");

        String number;
        // 12
        number = "201701102384";
        Number n0 = new Number(number);
        System.out.println(n0);
        test(number.equals(n0.toString()), true);
        test(n0.getCentury().equals("20"), true);
        test(n0.getYear().equals("17"), true);
        test(n0.getMonth().equals("01"), true);
        test(n0.getDay().equals("10"), true);
        test(n0.getDelimiter().equals(""), true);
        test(n0.getLast4().equals("2384"), true);

        // 12 w/ -
        number = "20080903-2386";
        Number n1 = new Number(number);
        System.out.println(n1);
        test(number.equals(n1.toString()), true);
        test(n1.getCentury().equals("20"), true);
        test(n1.getYear().equals("08"), true);
        test(n1.getMonth().equals("09"), true);
        test(n1.getDay().equals("03"), true);
        test(n1.getDelimiter().equals("-"), true);
        test(n1.getLast4().equals("2386"), true);
        
        // 10 w/ - 
        number = "141206-2380";
        Number n2 = new Number(number);
        System.out.println(n2);
        test(number.equals(n2.toString()), true);
        test(n2.getCentury().equals(""), true);
        test(n2.getYear().equals("14"), true);
        test(n2.getMonth().equals("12"), true);
        test(n2.getDay().equals("06"), true);
        test(n2.getDelimiter().equals("-"), true);
        test(n2.getLast4().equals("2380"), true);

        // 10 w/ + 
        number = "900118+9811";
        Number n3 = new Number(number);
        System.out.println(n3);
        test(number.equals(n3.toString()), true);
        test(n3.getCentury().equals(""), true);
        test(n3.getYear().equals("90"), true);
        test(n3.getMonth().equals("01"), true);
        test(n3.getDay().equals("18"), true);
        test(n3.getDelimiter().equals("+"), true);
        test(n3.getLast4().equals("9811"), true);

        // 10 
        number = "9001189811";
        Number n4 = new Number(number);
        System.out.println(n4);
        test(number.equals(n4.toString()), true);
        test(n4.getCentury().equals(""), true);
        test(n4.getYear().equals("90"), true);
        test(n4.getMonth().equals("01"), true);
        test(n4.getDay().equals("18"), true);
        test(n4.getDelimiter().equals(""), true);
        test(n4.getLast4().equals("9811"), true);

    //Invalid input

        System.out.println("Invalid input tests");
        String[] invalidInput = {
            ""                  // Empty
            , "hello"           // no numbers
            , "900118?9811"     // invalid delimiter
        };
        for (String input: invalidInput) {

            boolean result = false;
            try {
                Number n5 = new Number(input);
            }
            catch (InvalidNumberException e) {
                result = true;
            }
            test(result, true);

        }
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
        parserTests();
        //persnumberTests();
        // TODO: add more tests

    } 


    public static void main(String[] args) {
        runTests();
        printStatistics();

    
    }
    
}