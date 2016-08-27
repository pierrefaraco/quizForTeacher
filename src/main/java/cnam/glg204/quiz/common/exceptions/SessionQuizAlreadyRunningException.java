package cnam.glg204.quiz.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="A session quiz already running for this cours")
public class SessionQuizAlreadyRunningException extends ApplicationException {

    public SessionQuizAlreadyRunningException(final String message) {
        super(message);
    }
}
