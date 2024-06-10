package com.peter.backend_technical.service.impl;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import com.peter.backend_technical.handler.AppException;
import com.peter.backend_technical.handler.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.peter.backend_technical.dto.UsuariosDTO;
import com.peter.backend_technical.entity.UsuariosEntity;
import com.peter.backend_technical.mapper.UserMapper;
import com.peter.backend_technical.repository.UsuariosRepository;
import com.peter.backend_technical.service.UsuariosService;

@Service
public class UsuariosServiceImpl implements UsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private UserMapper userMapper;
		
	private UsuariosEntity buscarUsuario(int idUsuario) {
		Optional<UsuariosEntity> result = usuariosRepository.findById(idUsuario);
		UsuariosEntity usuario;
		
		if (result.isPresent()) {
			usuario = result.get();			
		} else {
			throw new AppException("Usuario con el id: " + idUsuario + ", no encontrado", HttpStatus.NOT_FOUND);
        }
		return usuario;
	}

	private void saveUsers(UsuariosEntity usuarioEntity) {
		try {
			usuariosRepository.save(usuarioEntity);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("No pueden existir datos repetidor");
		}
	}

	@Override
	public List<UsuariosDTO> getAllUsuarios() {		
		List<UsuariosEntity> usuariosEntity = usuariosRepository.findAll();
		
		List<UsuariosDTO> usuariosDTO = userMapper.toListUsuariosDTO(usuariosEntity);
		
		return usuariosDTO;
	}

	@Override
	public UsuariosDTO getUsuario(int idUsuario) {
		UsuariosEntity usuarioEntity = buscarUsuario(idUsuario);
		
		return userMapper.toUsuariosDTO(usuarioEntity);
	}
	
	@Override
	public String addUsuario(UsuariosDTO usuarioDTO) {
		
		UsuariosEntity usuarioEntity = userMapper.toUsuariosEntity(usuarioDTO);

		saveUsers(usuarioEntity);

		return "Usuario guardado correctamente";
	}

	@Override
	public UsuariosDTO updateUsuario(int idUsuario, UsuariosDTO usuarioDTO) {
		UsuariosEntity usuarioEntity = buscarUsuario(idUsuario);

		usuarioEntity = userMapper.toUsuariosEntity(usuarioDTO);

		saveUsers(usuarioEntity);

		return userMapper.toUsuariosDTO(usuarioEntity);
	}

	@Override
	public String deleteUsuario(int idUsuario) {
		UsuariosEntity usuarioEntity = buscarUsuario(idUsuario);
		usuariosRepository.delete(usuarioEntity);
		
		return "Usuario eliminado correctamente";
	}
}
