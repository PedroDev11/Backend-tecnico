package com.peter.backend_technical.dto;

import jakarta.validation.constraints.Size;

public class UsuariosDTO {

	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private Integer edad;
	
	@Size(min = 18, max = 18, message = "CURP con formato incorrecto")
	private String curp;
	
	@Size(min = 5, max = 5, message = "Codigo postal con formato incorrecto")
	private Integer cp;
	
	@Size(min = 13, max = 13, message = "RFC con formato incorrecto")
	private String rfc;
	
	@Size(min = 10, max = 10, message = "Numero telefonico incorrecto, deberia tener al menos 10 digitos")
	private String telefono;
	
	private String fecha;
	private String password;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
