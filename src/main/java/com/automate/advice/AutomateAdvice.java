package com.automate.advice;



import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.automate.exception.GlobalSettingsServiceException;
import com.automate.model.globalsettings.ErrorDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AutomateAdvice {

	@ExceptionHandler(GlobalSettingsServiceException.class)
	public ResponseEntity<?> globalSettingsExceptionHandler(GlobalSettingsServiceException ex,WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(false),ex.getStatusCode().toString());
		log.debug("globalSettingsExceptionHandler "+errorDetails);
		return new ResponseEntity<>(errorDetails,ex.getStatusCode());
	}
	
}
