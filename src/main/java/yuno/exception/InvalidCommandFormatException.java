package yuno.exception;

/**
 * Represents a command that does not follow its required format.
 */
public class InvalidCommandFormatException extends YunoException {
    /** Serialization version identifier. */
    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the specified error message.
     *
     * @param message Error message to display.
     */
    public InvalidCommandFormatException(String message) {
        super(message);
    }
}
