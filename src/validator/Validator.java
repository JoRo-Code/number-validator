package src.validator;

import java.util.Scanner;

import src.validator.number.InvalidNumberException;
import src.validator.number.Number;
import src.validator.number.*;

public class Validator {
    private static String PROMPT = "? ";

    private static String readInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print(PROMPT);

        String input = sc.nextLine();
        sc.close();

        return input;

    }

    /**
   * Performs checks for each number type and logs result
   * @param str     string to be validated. 
   */
    public static void validate(String str) {
        Boolean verbose = true;
        Number n = null;

        try {
            n = new Number(str);
        }
        catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        }

        if (n !=null) {

            Number[] numberTypes = {
                new Persnumber(str)
            ,   new Samnumber(str)
            ,   new Orgnumber(str)
            };

            for (Number type: numberTypes) {
                type.isValid(verbose);
            }
        }
    }

    public static void main(String[] args) {

        String input = readInput();
        validate(input);
    }
}
