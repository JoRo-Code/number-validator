package src.validator.checks;

import src.validator.number.Number;

public class ValidDate extends ValidityCheck {
    
    public ValidDate() {
        this.name = "Valid date";

    }
    
    @Override
    public boolean run(Number n) {
        return false; // TODO: implement

    }

}
