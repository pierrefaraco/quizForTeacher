package com.cnam.quiz.common.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="You should select a cours before start a Quiz session")
public class NoCoursSelectedException extends ApplicationException{
    
    public NoCoursSelectedException() {
    }

    public NoCoursSelectedException(final String message) {
        super(message);
    }
}
    

