package src.validator.checks;

import src.validator.number.Number;

public class OrgnumberFormatCheck extends ValidityCheck {

    private boolean isValidCentury(Number n) {
        String c =  n.getCentury();

        return c.equals("") || c.equals("16");

    }

    private boolean isValidMonth(Number n) {
        String m = n.getMonth();
        int month = Integer.parseInt(m);
        return month >= 20;

    }
    
    @Override
    public boolean run(Number n) {

        return isValidCentury(n) && isValidMonth(n);
    }
    
}
