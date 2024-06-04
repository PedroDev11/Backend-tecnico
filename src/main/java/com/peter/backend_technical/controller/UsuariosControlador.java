package com.peter.backend_technical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String agregarUsuario(@RequestBody UsuariosDTO usuarioDTO) {
		return usuariosService.addUsuario(usuarioDTO);
	}
	
	@PutMapping("/updateUsuario/{idUsuario}")
	public UsuariosDTO actualizarActivoFijo(
			@PathVariable int idUsuario,
			@RequestBody UsuariosDTO usuarioDTO) {
		return usuariosService.updateUsuario(idUsuario, usuarioDTO);
	}
	
	@DeleteMapping("/deleteUsuario/{idUsuario}")
	public String eliminarActivoFijo(@PathVariable int idUsuario) {
		return usuariosService.deleteUsuario(idUsuario);
	}
}
