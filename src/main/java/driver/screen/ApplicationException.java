package driver.screen;

/**
 * This class represents a custom runtime exception that can be thrown during application execution.
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException(String msg){
        super(msg);
    }
}
