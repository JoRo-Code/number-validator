package src.validator.checks;

import src.validator.number.Number;

public class Luhn extends ValidityCheck {
    
    public Luhn() {
        this.name = "Luhn";
    }
    
    private static int toInt(char c) {
        return c - '0'; 
    }
   
    // Converts Number to YYDDMMXXX format
    private String luhn_format(Number n) {
        String s = n.getYear() + n.getMonth() + n.getDay() + n.getLast4();
        return s.substring(0, s.length()-1);
    }

    // YYDDMMXXX -> checksum
    private int luhn(String number) {
        // https://www.geeksforgeeks.org/luhn-algorithm/

        int sum = 0;

        for (int i = 0; i < number.length(); i++) {
            int d = toInt(number.charAt(i));

            if (i % 2 == 0) {
                d = d * 2;
            }
            sum += d / 10; // tens
            sum += d % 10; // singles
        }

        int luhns = (10 - (sum % 10)) % 10;

        return luhns;

    }

    @Override
    public boolean run(Number n) {
        
        int luhn = luhn(luhn_format(n));
        int last = toInt(n.getLast().charAt(0));

        return luhn == last;

    }
}
