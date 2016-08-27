package cnam.glg204.quiz.common.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Unable to suscribe for receive or push questions, pool is probably full")
public class NoWebSocketMethodToSubscribeException  extends ApplicationException {

    public  NoWebSocketMethodToSubscribeException() {
        super();
    }    
    
    public  NoWebSocketMethodToSubscribeException(String message) {
        super(message);
    }

}
