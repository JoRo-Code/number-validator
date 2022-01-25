package src.validator.checks;

import src.validator.number.Number;

public abstract class ValidityCheck {
    
    protected String name = "ValidityCheck";

    public boolean run(Number n) {
        return false;

    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
