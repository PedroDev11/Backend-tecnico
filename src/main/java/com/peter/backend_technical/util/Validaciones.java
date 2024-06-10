package com.peter.backend_technical.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.peter.backend_technical.handler.AppException;
import org.springframework.http.HttpStatus;

public class Validaciones {
	
	public boolean validarFecha(String date) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    dateFormat.setLenient(false);
	    try {
	        dateFormat.parse(date);
	        return true;
	    } catch (ParseException e) {
	    	throw new AppException("Formato de fecha incorrecto", HttpStatus.BAD_REQUEST);
	    }
	}
}
