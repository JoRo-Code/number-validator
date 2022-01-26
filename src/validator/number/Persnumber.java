package src.validator.number;

import src.validator.checks.ValidDate;
public class Persnumber extends Number {

    public Persnumber(String str) {
        super(str);
        this.checks.add(
            new ValidDate()
            // Add checks here
        );
    }
}
