package cnam.glg204.quiz.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN , reason="The selected cours should be active !")
public class CoursNotActiveException extends ApplicationException {

    public CoursNotActiveException (final String message) {
        super(message);
    }

}
