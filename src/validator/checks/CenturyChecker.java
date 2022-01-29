package src.validator.checks;

import src.validator.number.Number;
import src.validator.number.Orgnumber;

public class CenturyChecker extends ValidityCheck {
    
    
    private boolean isValidCentury(Number n) {
        String c =  n.getCentury();

        if (c.equals("")) {
            return true;
        }

        if(n instanceof Orgnumber) {
            return c.equals("16");
        }

        int century = Integer.parseInt(c);
        boolean result = 17 < century && century < 21;
        return result;

    }

    
    @Override
    public boolean run(Number n) {
        return isValidCentury(n);
    }
}
