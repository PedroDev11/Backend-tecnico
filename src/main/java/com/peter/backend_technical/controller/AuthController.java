package com.peter.backend_technical.controller;

import com.peter.backend_technical.handler.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peter.backend_technical.dto.AuthRequest;
import com.peter.backend_technical.service.impl.AuthService;

@RestController
@RequestMapping(path = "v1.0")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping(path = "oauth/client_credential/accessToken")
	public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest) throws AppException {

		return ResponseEntity.ok(authService.login(authRequest.getUsername(), authRequest.getPassword()));
	}

}
