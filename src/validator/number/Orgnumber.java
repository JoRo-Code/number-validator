package src.validator.number;

import src.validator.checks.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Orgnumber extends Number{
    
    private ArrayList<ValidityCheck> additionalChecks = new ArrayList<ValidityCheck>(Arrays.asList(
                new CenturyChecker()
            ,   new OrgnumberMonthChecker()
            // Add checks here
    ));

    public Orgnumber(String str) {
        super(str);
        this.checks.addAll(this.additionalChecks);
    }
    
}
