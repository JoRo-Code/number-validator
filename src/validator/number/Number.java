package src.validator.number;

import src.validator.checks.*;

import static src.utils.Color.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.Arrays;

public class Number {
    private String century = "";
    private String year = "";
    private String month = "";
    private String day = "";
    private String delimiter = "";
    private String last4 = "";
    
    // Getters
    
    public final String getCentury() {
        return this.century;
    }
    
    public final String getYear() {
        return this.year;
    }
    
    public final String getMonth() {
        return this.month;
    }
    
    public final String getDay() {
        return this.day;
    }
    
    public final String getDelimiter() {
        return this.delimiter;
    }
    
    public final String getLast4() {
        return this.last4;
    }

    public final String getLast() {
        return this.last4.substring(3);
    }
    
    /**
   * General validity checks, defines the general number format
   * 
   */
    protected ArrayList<ValidityCheck> checks = new ArrayList<ValidityCheck>(Arrays.asList(
        new LuhnChecker(),
        new NumberFormatChecker()
    ));
    
    /**
   * Parsing a string into a Number. Format: (CC)? YYMMDD(-+)? XXXX
   * Examples:
   * 12123456+1234
   * 1234561234
   * 
   * @param str                         string to parse
   * @throws InvalidNumberException     if not in correct format
   */
    private void parse(String str) 
                throws InvalidNumberException {

        String delimiters = "+-";
        String pattern = "^(\\d{2})?(\\d{2})(\\d{2})(\\d{2})([" + delimiters + "]?)(\\d{4})$";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);

        if (m.find()) {
            boolean hasCentury = m.group(1) != null && !m.group(1).isEmpty();
            if (hasCentury) {
                this.century = m.group(1);
            }

            this.year = m.group(2);
            this.month = m.group(3);
            this.day = m.group(4);
            this.delimiter = m.group(5);
            this.last4 = m.group(6);
        }
        else {
            throw new InvalidNumberException("Invalid input: '" + str + "' cannot be parsed as a number");
        }
    }


    public Number(String number) 
                throws InvalidNumberException {
        parse(number);
    }

    /**
   * Runs ValidityChecks for a number 
   * 
   * @param verbose determines if checks should be logged
   * @return true if all checks pass, else false
   */
    public boolean isValid(boolean verbose) {
        if (verbose) { System.out.println(this.getClass().getSimpleName());}
        boolean result = true;
        for (ValidityCheck check: checks)
        {
            boolean partialResult = check.run(this);
            if (verbose) {
                System.out.println((partialResult == true? Green("Passed") : Red("Failed")) + ": " + check);
            }
            result = partialResult && result;
        }
        return result;
    }
    
    public boolean isValid() {
        return isValid(false);
    }


    @Override
    public String toString() {
        return this.century + this.year + this.month + this.day + this.delimiter + this.last4;
    }
    
}
