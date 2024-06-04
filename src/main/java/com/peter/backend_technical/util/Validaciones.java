package com.peter.backend_technical.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.peter.backend_technical.dto.UsuariosDTO;
import com.peter.backend_technical.handler.NotFoundException;

public class Validaciones {

	public void validarCurp(UsuariosDTO usuarioDTO, List<String> curps) {
		for(String curp: curps) {
			if(curp.equals(usuarioDTO.getCurp())) {
				throw new NotFoundException("El CURP ya ha sido registrado previamente");
			}
		}
	}
	
	public void validarCp(UsuariosDTO usuarioDTO, List<Integer> cps) {
		for(Integer cp: cps) {
			if(cp.equals(usuarioDTO.getCp())) {
				throw new NotFoundException("El CP ya ha sido registrado previamente");
			}
		}
	}
	
	public void validarRfc(UsuariosDTO usuarioDTO, List<String> rfcs) {
		for(String rfc: rfcs) {
			if(rfc.equals(usuarioDTO.getRfc())) {
				throw new NotFoundException("El RFC ya ha sido registrado previamente");
			}
		}
	}

	public void validarTelefono(UsuariosDTO usuarioDTO, List<String> telefonos) {
		for(String telefono: telefonos) {
			if(telefono.equals(usuarioDTO.getTelefono())) {
				throw new NotFoundException("El numero telefonico ya ha sido registrado previamente");
			}
		}
	}
	
	public boolean validarFecha(String date) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    dateFormat.setLenient(false);
	    try {
	        dateFormat.parse(date);
	        return true;
	    } catch (ParseException e) {
	    	throw new NotFoundException("Formato de fecha incorrecto");
	    }
	}
}
