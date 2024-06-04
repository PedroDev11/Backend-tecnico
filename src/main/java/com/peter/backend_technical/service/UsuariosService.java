package com.peter.backend_technical.service;

import java.util.List;

import com.peter.backend_technical.dto.UsuariosDTO;

public interface UsuariosService {
	
	public List<UsuariosDTO> getAllUsuarios();
	public UsuariosDTO getUsuario(int idUsuario);
	public String addUsuario(UsuariosDTO usuarioDTO);
	public UsuariosDTO updateUsuario(int idUsuario, UsuariosDTO usuarioDTO);
	public String deleteUsuario(int idUsuario);
}
