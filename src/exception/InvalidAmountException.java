package exception;

public class InvalidAmountException extends Exception {
    /** The current {@link #message} Holds the message of this exception. */
    private String message;

    /**
     * @param message Set the message for the exception
     */
    public InvalidAmountException(final String message) {
        this.message = message;
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the message string of {@code InvalidAmountException} instance
     */
    @Override
    public String getMessage() {
        return message;
    }

}
