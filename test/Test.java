import src.validator.Validator;
import src.validator.number.Number;
import src.validator.number.Persnumber;
import src.validator.number.Samnumber;
import src.validator.number.Orgnumber;
import src.validator.number.InvalidNumberException;
import src.validator.checks.*;

// readFromFile()
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

        String[][] numberContextArray = {
                {"201701102384",    "20",   "17",   "01",   "10",   "",     "2384"} // 12 digits
            ,   {"20080903-2386",   "20",   "08",   "09",   "03",   "-",    "2386"} // 12 digits w/ delimiter -
            ,   {"9001189811",      "",     "90",   "01",   "18",   "",     "9811"} // 10 digits w/ delimiter
            ,   {"141206-2380",     "",     "14",   "12",   "06",   "-",    "2380"} // 10 digits w/ delimiter -
            ,   {"900118+9811",     "",     "90",   "01",   "18",   "+",    "9811"} // 10 digits w/ delimiter +
        };

        for (String[] numberContext: numberContextArray) {
            String number = numberContext[0];
            Number n = new Number(number);
            System.out.println(n);
            test(number.equals(n.toString()), true);
            test(n.getCentury().equals(numberContext[1]), true);
            test(n.getYear().equals(numberContext[2]), true);
            test(n.getMonth().equals(numberContext[3]), true);
            test(n.getDay().equals(numberContext[4]), true);
            test(n.getDelimiter().equals(numberContext[5]), true);
            test(n.getLast4().equals(numberContext[6]), true);
        }

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


    private static void luhnTests() {
        ValidityCheck luhn = new Luhn();
        System.out.println(luhn);
        
        List<String> numbers = readFromFile("test/input/validLuhn.txt");
        for (String str: numbers) {
            System.out.println(str);
            Number n = new Number(str);
            boolean result = luhn.run(n);
            test(result, true);

        }
        
        numbers = readFromFile("test/input/invalidLuhn.txt");
        for (String str: numbers) {
            System.out.println(str);
            Number n = new Number(str);
            boolean result = luhn.run(n);
            test(result, false);

        }
    }

    private static void validDateTests() {
        System.out.println("DateValidator tests");
        ValidityCheck dateChecker = new ValidDate();

        String valid = "valid";
        String invalid = "invalid";

        String[][] numberArrayContext = {
                // date             expected
                {"19980301-0000",  valid        } // 12 digit
            ,   {"980301-0000"  ,  valid        } // 10 digit
            ,   {"19980230-0000",  invalid      } // february 30
            ,   {"19981301-0000",  invalid      } // 13 month
            ,   {"30301301-0000",  invalid      } // future
            ,   {"901201+0000"  ,  valid      } // 100 >
        };

        for (String[] numberContext: numberArrayContext) {
            String str = numberContext[0];
            boolean expected = numberContext[1].equals(valid); 

            System.out.println(str);
            Number n = new Number(str);
            boolean result = dateChecker.run(n);
            test(result, expected);

        }
    }
    
    private static void persnumberTests() {
        
        // valid
        System.out.println("Valid persnumber");
        List<String> strings = readFromFile("test/input/validPersnumber.txt");
        for (String str: strings) {
            System.out.println(str);
            Persnumber p = new Persnumber(str);
            boolean result = p.isValid();
            test(result, true);
        }
        
        // invalid
        System.out.println("Invalid persnumber");
        strings = readFromFile("test/input/invalidPersnumber.txt");
        for (String str: strings) {
            System.out.println(str);
            Persnumber p = new Persnumber(str);
            boolean result = p.isValid();
            test(result, false);
        }
    }


    private static void samnumberTests() {
        
        // valid

        System.out.println("Valid samnumber");
        List<String> strings = readFromFile("test/input/validSamnumber.txt");
        for (String str: strings) {
            System.out.println(str);
            Number n = new Samnumber(str);
            boolean result = n.isValid();
            test(result, true);
        }
        
        // invalid
        System.out.println("Invalid samnumber");
        strings = readFromFile("test/input/invalidSamnumber.txt");
        for (String str: strings) {
            System.out.println(str);
            Number n = new Samnumber(str);
            boolean result = n.isValid();
            test(result, false);
        }
    }
    
    private static void orgnumberTests() {
        
        // valid

        System.out.println("Valid orgnumber");
        List<String> strings = readFromFile("test/input/validOrgnumber.txt");
        for (String str: strings) {
            System.out.println(str);
            Number n = new Orgnumber(str);
            boolean result = n.isValid();
            test(result, true);
        }
        
        // invalid
        System.out.println("Invalid orgnumber");
        strings = readFromFile("test/input/invalidOrgnumber.txt");
        for (String str: strings) {
            System.out.println(str);
            Number n = new Orgnumber(str);
            boolean result = n.isValid();
            test(result, false);
        }
    }


    private static void runTests() {
        parserTests();
        luhnTests();
        validDateTests();
        /*
        persnumberTests();
        samnumberTests();
        orgnumberTests();
        */
        // TODO: add more tests

    } 


    public static void main(String[] args) {
        runTests();
        printStatistics();

    
    }
    
}