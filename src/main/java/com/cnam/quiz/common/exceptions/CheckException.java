package com.cnam.quiz.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when some checking validation error is found.
 */

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Datas are not valids and can t be recorded")
public final class CheckException extends ApplicationException {

    public CheckException(final String message) {
        super(message);
    }
}
