package com.peter.backend_technical.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.peter.backend_technical.dto.UsuariosDTO;
import com.peter.backend_technical.service.UsuariosService;

@RestController
@RequestMapping("${api.v1.publico.baseUrl}")
public class UsuariosControlador {
	
	@Autowired
	UsuariosService usuariosService;
	
	@GetMapping("/getUsuarios")
	public List<UsuariosDTO> ObtenerUsuarios() {
		return usuariosService.getAllUsuarios();
	}
	
	@GetMapping("/getUsuarios/{idUsuario}")
	public UsuariosDTO ObtenerActivoFijo(@PathVariable int idUsuario) {
		return usuariosService.getUsuario(idUsuario);
	}
	
	@PostMapping("/addUsuario")
	public String agregarUsuario(@RequestBody @Valid UsuariosDTO usuarioDTO) {
		return usuariosService.addUsuario(usuarioDTO);
	}
	
	@PutMapping("/updateUsuario/{idUsuario}")
	public UsuariosDTO actualizarActivoFijo(
			@PathVariable int idUsuario,
			@RequestBody @Valid UsuariosDTO usuarioDTO) {
		return usuariosService.updateUsuario(idUsuario, usuarioDTO);
	}
	
	@DeleteMapping("/deleteUsuario/{idUsuario}")
	public String eliminarActivoFijo(@PathVariable int idUsuario) {
		return usuariosService.deleteUsuario(idUsuario);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}
