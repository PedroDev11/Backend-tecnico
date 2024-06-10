package com.peter.backend_technical.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespuestaJwtDTO {

	@JsonProperty(value = "token_type")
	private String tokenType;
	
	@JsonProperty(value = "access_token")
	private String accessToken;
	
	@JsonProperty(value = "expires_in")
	private Integer expiresIn;
	
	@JsonProperty(value = "issued_at")
	private String issuedAt;

	public RespuestaJwtDTO(String tokenType, String accessToken, Integer expiresIn, String issuedAt) {
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.issuedAt = issuedAt;
	}
}
