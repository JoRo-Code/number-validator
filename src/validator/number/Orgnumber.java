package src.validator.number;

import src.validator.checks.OrgnumberFormatCheck;

public class Orgnumber extends Number {
    public Orgnumber(String str) {
        super(str);
        this.checks.add(
            // Add checks here
            new OrgnumberFormatCheck()
        );
    }
}
