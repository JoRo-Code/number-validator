package src.validator.number;

public class InvalidNumberException extends RuntimeException {

    public InvalidNumberException() {
        super();
    }
  
    public InvalidNumberException(String errorMessage) {
        super(errorMessage);
    } 
}
