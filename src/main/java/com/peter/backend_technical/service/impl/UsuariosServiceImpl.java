package com.peter.backend_technical.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peter.backend_technical.dto.UsuariosDTO;
import com.peter.backend_technical.entity.UsuariosEntity;
import com.peter.backend_technical.handler.NotFoundException;
import com.peter.backend_technical.repository.UsuariosRepository;
import com.peter.backend_technical.service.UsuariosService;
import com.peter.backend_technical.util.*;

@Service
public class UsuariosServiceImpl implements UsuariosService {
	
	@Autowired
	UsuariosRepository usuariosRepository;
	Validaciones validar = new Validaciones();

	@Override
	public List<UsuariosDTO> getAllUsuarios() {		
		List<UsuariosEntity> usuariosEntity = usuariosRepository.findAll();
		
		List<UsuariosDTO> usuariosDTO = usuariosEntity.stream()
				.map(usuario -> new UsuariosDTO(usuario.getNombre(), usuario.getPrimerApellido(), usuario.getSegundoApellido(),
						usuario.getEdad(), usuario.getCurp(), usuario.getCp(), usuario.getRfc(), usuario.getTelefono(), usuario.getFecha(), usuario.getPassword()))
				.collect(Collectors.toList());
		
		return usuariosDTO;
	}

	@Override
	public UsuariosDTO getUsuario(int idUsuario) {
		Optional<UsuariosEntity> result = usuariosRepository.findById(idUsuario);
		UsuariosEntity usuario;
		
		if (result.isPresent()) {
			usuario = result.get();			
		} else {
            throw new NotFoundException("Usuario con el id: " + idUsuario + ", no encontrado");
        }
		
		UsuariosDTO usuarioDTO = new UsuariosDTO(usuario.getNombre(), usuario.getPrimerApellido(), usuario.getSegundoApellido(),
				usuario.getEdad(), usuario.getCurp(), usuario.getCp(), usuario.getRfc(), usuario.getTelefono(), usuario.getFecha(), usuario.getPassword());
		
		return usuarioDTO;
	}

	@Override
	public String addUsuario(UsuariosDTO usuarioDTO) {
		UsuariosEntity usuarioEntity = new UsuariosEntity();
		List<String> curps = usuariosRepository.findCurp();
		List<Integer> cps = usuariosRepository.findCp();
		List<String> rfcs = usuariosRepository.findRfc();
		List<String> telefonos = usuariosRepository.findTelefono();
		
		validar.validarCurp(usuarioDTO, curps);
		validar.validarCp(usuarioDTO, cps);
		validar.validarRfc(usuarioDTO, rfcs);
		validar.validarTelefono(usuarioDTO, telefonos);
		validar.validarFecha(usuarioDTO.getFecha());
		
		
		usuarioEntity.setNombre(usuarioDTO.getNombre());
		usuarioEntity.setPrimerApellido(usuarioDTO.getPrimerApellido());
		usuarioEntity.setSegundoApellido(usuarioDTO.getSegundoApellido());
		usuarioEntity.setEdad(usuarioDTO.getEdad());
		usuarioEntity.setCurp(usuarioDTO.getCurp());
		usuarioEntity.setCp(usuarioDTO.getCp());
		usuarioEntity.setRfc(usuarioDTO.getRfc());
		usuarioEntity.setTelefono(usuarioDTO.getTelefono());
		usuarioEntity.setFecha(usuarioDTO.getFecha());
		usuarioEntity.setPassword(usuarioDTO.getPassword());
		
		usuariosRepository.save(usuarioEntity);
		
		return "Usuario guardado correctamente";
	}

	@Override
	public UsuariosDTO updateUsuario(int idUsuario, UsuariosDTO usuarioDTO) {
		Optional<UsuariosEntity> result = usuariosRepository.findById(idUsuario);
		UsuariosEntity usuario;
		
		if (result.isPresent()) {
			usuario = result.get();
			
			usuario.setNombre(usuarioDTO.getNombre());
			usuario.setPrimerApellido(usuarioDTO.getPrimerApellido());
			usuario.setSegundoApellido(usuarioDTO.getSegundoApellido());
			usuario.setEdad(usuarioDTO.getEdad());
			usuario.setCurp(usuarioDTO.getCurp());
			usuario.setCp(usuarioDTO.getCp());
			usuario.setRfc(usuarioDTO.getRfc());
			usuario.setTelefono(usuarioDTO.getTelefono());
			usuario.setFecha(usuarioDTO.getFecha());
			usuario.setPassword(usuarioDTO.getPassword());
			
			usuariosRepository.save(usuario);			
		} else {
            throw new NotFoundException("Usuario con el id: " + idUsuario + ", no encontrado");
        }
		
		UsuariosDTO usuarioDTO2 = new UsuariosDTO(usuario.getNombre(), usuario.getPrimerApellido(), usuario.getSegundoApellido(),
				usuario.getEdad(), usuario.getCurp(), usuario.getCp(), usuario.getRfc(), usuario.getTelefono(), usuario.getFecha(), usuario.getPassword());
		
		return usuarioDTO2;
	}

	@Override
	public String deleteUsuario(int idUsuario) {
		Optional<UsuariosEntity> result = usuariosRepository.findById(idUsuario);
		UsuariosEntity usuario;
		
		if(result.isPresent()) {
			usuario = result.get();
			usuariosRepository.delete(usuario);
		} else {
            throw new NotFoundException("Usuario con el id: " + idUsuario + ", no encontrado");
        }
		
		return "Usuario eliminado correctamente";
	}
}
