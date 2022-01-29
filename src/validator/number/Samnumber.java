package src.validator.number;

import src.validator.checks.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Samnumber extends Number{
    
    private ArrayList<ValidityCheck> additionalChecks = new ArrayList<ValidityCheck>(Arrays.asList(
                new ValidDate(60)
            ,   new CenturyChecker()
            // Add checks here
    ));

    public Samnumber(String str) {
        super(str);
        this.checks.addAll(this.additionalChecks);
    }
    
}



