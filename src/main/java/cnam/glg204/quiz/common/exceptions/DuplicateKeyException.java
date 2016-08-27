package cnam.glg204.quiz.common.exceptions;

/**
 * This exception is throw when an object already exists in the persistent layer
 * and we want to add another one with the same identifier.
 */
public final class DuplicateKeyException extends CreateException {
}
