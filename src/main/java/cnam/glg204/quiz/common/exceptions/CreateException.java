package cnam.glg204.quiz.common.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when an object cannot be created.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="This email is already used for an another account")
public class CreateException extends ApplicationException {

    public CreateException() {
    }

    public CreateException(final String message) {
        super(message);
    }
}
