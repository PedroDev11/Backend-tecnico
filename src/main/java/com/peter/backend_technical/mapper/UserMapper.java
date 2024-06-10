package com.peter.backend_technical.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.peter.backend_technical.dto.UsuariosDTO;
import com.peter.backend_technical.entity.UsuariosEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UsuariosDTO toUsuariosDTO(UsuariosEntity usuariosEntity);
	List<UsuariosDTO> toListUsuariosDTO(List<UsuariosEntity> listUsuariosEntity);
	UsuariosEntity toUsuariosEntity(UsuariosDTO usuariosDTO);
}
