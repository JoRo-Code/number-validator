package src.validator.checks;

import src.validator.number.Number;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidDate extends ValidityCheck {
    
    public ValidDate() {
        this.name = "Valid date";

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

    private String getBirthdate(Number n) {
        String century = n.getCentury();

        if (century.equals("") ) {

            int now = LocalDate.now().getYear();
            int year = Integer.parseInt(n.getYear());
            int birthYear = now-year; // birthyear < 100 years ago

            if (n.getDelimiter().equals("+")) {
                birthYear -= 100; // birthyear > 100 years ago
                // TODO: how to decide if its 100 or 200 years?
            }

            century = Integer.toString(birthYear / 100);
        }

        String date = century
                    + n.getYear() 
                    + n.getMonth() 
                    + n.getDay();

        return date;

    }

    @Override
    public boolean run(Number n) {

        String date = getBirthdate(n);
        return isValid(date);
    }

}
