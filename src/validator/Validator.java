package src.validator;

import java.util.Scanner;

import src.validator.number.InvalidNumberException;
import src.validator.number.Number;
import src.validator.number.Persnumber;

public class Validator {
    private static String PROMPT = "? ";


    private static String readInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print(PROMPT);

        String input = sc.nextLine();
        sc.close();

        return input;

    }

    public static void validate(String str) {
        System.out.println(str);
        Number n = null;
        try {
            n = new Number(str);
        }
        catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        }
        if (n !=null && n.isValid(true)) {
            System.out.println("Valid");
        }
        else {
            System.out.println("inValid");
        }

        // TODO: validate all types of numbers

    }

    public static void main(String[] args) {

        String input = readInput();
        validate(input);

    }
    
}
