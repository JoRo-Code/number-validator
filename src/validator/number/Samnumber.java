package src.validator.number;
import src.validator.checks.ValidDate;

public class Samnumber extends Number{
    public Samnumber(String str) {
        super(str);
        this.checks.add(
            new ValidDate(60)
            // Add checks here
        );
    }
    
}



