package nl.hu.bep2.casino.blackjack.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	//##############  OMDAT HET NIET LUKT DEZE BOOLEAN IN APPLICATION.PRPERTIES TE ZETTEN NU EVEN HIER DE FOUTMELDINGEN AAN EN UIT ZETTEN
	@Value("${CustomExceptionHandlerStaatAan:false}")
	private Boolean CustomExceptionHandlerStaatAan;
	
	
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
    	
    	if (CustomExceptionHandlerStaatAan) {
    		return new ResponseEntity<>(ex.getReason(), ex.getStatus());
    	}
    	else {
    		throw ex;
    	}
       
    }
}

