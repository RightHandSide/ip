package yuno.exception;

/**
 * Represents a command that Yuno does not recognize.
 */
public class UnknownCommandException extends YunoException {
    /** Serialization version identifier. */
    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the specified error message.
     *
     * @param message Error message to display.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
