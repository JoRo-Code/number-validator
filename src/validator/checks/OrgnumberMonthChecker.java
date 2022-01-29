package src.validator.checks;

import src.validator.number.Number;

public class OrgnumberMonthChecker extends ValidityCheck {

    private boolean isValidMonth(Number n) {
        String m = n.getMonth();
        int month = Integer.parseInt(m);
        return month >= 20;

    }
    
    @Override
    public boolean run(Number n) {

        return isValidMonth(n);
    }
    
}
