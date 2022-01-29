package src.validator.checks;

import src.validator.number.Number;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidDate extends ValidityCheck {

    private int dayOffset = 0;
    
    public ValidDate() {
        this(0);

    }
    
    public ValidDate(int dayOffset) {
        this.dayOffset = dayOffset;
    }


    private boolean isValid(String dateStr) {
        // https://www.baeldung.com/java-string-valid-date
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        try {
            LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private String getDayWithOffset(Number n) {
        String day = n.getDay();
        int dayInt = Integer.parseInt(n.getDay()) - this.dayOffset;
        
        day = Integer.toString(dayInt);
        if (day.length() != 2) {
            day = "0" + day;
        }

        return day;
    }

    /**
   * Calculates century of a given number 
   * with unknown century, depending on delimiter
   * 
   * @param n the Number to get century from
   * @return a String with format XX
   */
    private String calculateCentury(Number n) {

        String century = n.getCentury();

        if (century.equals("") ) {

            int now = LocalDate.now().getYear();
            int year = Integer.parseInt(n.getYear());
            int birthYear = now-year; // birthyear < 100 years ago

            if (n.getDelimiter().equals("+")) {
                birthYear -= 100; // birthyear > 100 years ago
            }

            century = Integer.toString(birthYear / 100);
        }

        return century;
    }

    private String getBirthdate(Number n) {

        String date = calculateCentury(n)
                    + n.getYear() 
                    + n.getMonth() 
                    + getDayWithOffset(n);

        return date;

    }

    @Override
    public boolean run(Number n) {

        String date = getBirthdate(n);
        return isValid(date);
    }

}
