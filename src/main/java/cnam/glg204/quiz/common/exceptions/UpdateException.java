package cnam.glg204.quiz.common.exceptions;

/**
 * This exception is thrown when an object cannot be updated.
 */
public final class UpdateException extends ApplicationException {

    public UpdateException(final String message) {
        super(message);
    }
}
