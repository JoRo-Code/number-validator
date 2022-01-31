package src.validator.checks;

import src.validator.number.Number;

public class OrgnumberDelimiterChecker extends ValidityCheck {
    
    @Override
    public boolean run(Number n) {
        String d = n.getDelimiter();
        return !(d.equals("+"));
    }
}
