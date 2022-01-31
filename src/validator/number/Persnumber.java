package src.validator.number;

import src.validator.checks.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Persnumber extends Number {

    private ArrayList<ValidityCheck> additionalChecks = new ArrayList<ValidityCheck>(Arrays.asList(
                new ValidDateChecker()
            ,   new CenturyChecker()
            // Add checks here
    ));

    public Persnumber(String str) {
        super(str);
        this.checks.addAll(this.additionalChecks);
    }
}
