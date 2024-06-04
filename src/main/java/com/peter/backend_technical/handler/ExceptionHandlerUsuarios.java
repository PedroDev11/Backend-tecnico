package com.peter.backend_technical.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.peter.backend_technical.dto.RespuestaDTO;

@ControllerAdvice
public class ExceptionHandlerUsuarios {

	@ExceptionHandler
	public ResponseEntity<RespuestaDTO> handleExceptions(NotFoundException exc) {
		RespuestaDTO error = new RespuestaDTO();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
