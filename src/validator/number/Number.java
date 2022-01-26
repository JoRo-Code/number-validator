package src.validator.number;

import src.validator.number.InvalidNumberException;
import src.validator.checks.*;

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

    protected ArrayList<ValidityCheck> checks = new ArrayList<ValidityCheck>(Arrays.asList(
        new Luhn(),
        new NumberFormatCheck()
    ));

    private void parse(String str) 
                throws InvalidNumberException {

        String delimiters = "+-";
        String pattern = "^(\\d{2})?(\\d{2})(\\d{2})(\\d{2})([" + delimiters + "]?)(\\d{4})$";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);

        if (m.find()) {
            if (m.group(1) != null && !m.group(1).isEmpty()) {
                this.century = m.group(1);
            }
            this.year = m.group(2);
            this.month = m.group(3);
            this.day = m.group(4);
            this.delimiter = m.group(5);
            this.last4 = m.group(6);
        }
        else {
            throw new InvalidNumberException("Invalid input: " + "'" + str + "'");
        }


    }


    public Number(String number) {
        parse(number);
    }

    public boolean isValid(boolean verbose) {
        boolean result = true;
        for (ValidityCheck check: checks)
        {
            result = check.run(this) && result;
            if (verbose) {
                System.out.println((result == true? "Passed":"Failed") + ": " + check);
            }
        }
        return result;
    }
    
    public boolean isValid() {
        return isValid(false);
    }

    // Getters
    
    public final String getLast() {
        return this.last4.substring(3);
    }
    
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


    @Override
    public String toString() {
        return this.century + this.year + this.month + this.day + this.delimiter + this.last4;
    }
    
}
