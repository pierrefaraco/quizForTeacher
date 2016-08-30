package cnam.glg204.quiz.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="This email is already used for an another account")
public final class UpdateException extends ApplicationException {

    public UpdateException(final String message) {
        super(message);
    }
}
