package yuno.exception;

/**
 * Represents an error caused by an invalid Yuno command or operation.
 */
public class YunoException extends Exception {
    /** Serialization version identifier. */
    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception with the specified error message.
     *
     * @param message Error message to display.
     */
    public YunoException(String message) { super(message); }
}
