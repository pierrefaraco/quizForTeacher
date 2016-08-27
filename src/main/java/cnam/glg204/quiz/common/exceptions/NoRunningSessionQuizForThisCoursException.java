package cnam.glg204.quiz.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Unable to store result because no quiz session is running for this cours")
public class NoRunningSessionQuizForThisCoursException extends ApplicationException {

    public NoRunningSessionQuizForThisCoursException () {
        super();
    }

}
