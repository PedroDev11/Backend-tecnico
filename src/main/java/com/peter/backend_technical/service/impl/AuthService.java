package com.peter.backend_technical.service.impl;

import com.peter.backend_technical.handler.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.peter.backend_technical.dto.RespuestaJwtDTO;
import com.peter.backend_technical.entity.UsuariosEntity;
import com.peter.backend_technical.repository.UsuariosRepository;
import com.peter.backend_technical.security.JwtIO;
import com.peter.backend_technical.util.DateUtils;

@Service
public class AuthService {
	
	@Autowired
	private JwtIO jwtIO;
	
	@Autowired
	private DateUtils dateUtils;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Value("${jms.jwt.token.expires-in:3600}")
	private Integer EXPIRES_IN;

	public RespuestaJwtDTO login(String nombre, String password) {
		
		UsuariosEntity usuarioDTO = usuariosRepository.findByNombre(nombre)
				.orElseThrow(() -> new AppException("Usuario no encontrado en la base de datos", HttpStatus.NOT_FOUND));
		
		RespuestaJwtDTO jwt = new RespuestaJwtDTO("bearer", jwtIO.generateToken(usuarioDTO), EXPIRES_IN, dateUtils.getDateMillis() + "");
		return jwt;
	}
}