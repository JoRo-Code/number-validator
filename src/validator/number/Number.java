package src.validator.number;

import src.validator.number.InvalidNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Number {
    private String century = "";
    private String year = "";
    private String month = "";
    private String day = "";
    private String delimiter = "";
    private String last4 = "";

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

    public boolean isValid() {
        return false; //TODO: implement
    }

    @Override
    public String toString() {
        return this.century + this.year + this.month + this.day + this.delimiter + this.last4;
    }
    
}
