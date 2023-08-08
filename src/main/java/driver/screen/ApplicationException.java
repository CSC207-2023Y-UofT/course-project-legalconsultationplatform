package driver.screen;

/**
 * This class represents a custom runtime exception that can be thrown during application execution.
 */
public class ApplicationException extends RuntimeException {

    /**
     * Constructs an ApplicationException with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public ApplicationException(String msg) {
        super(msg);
    }
}