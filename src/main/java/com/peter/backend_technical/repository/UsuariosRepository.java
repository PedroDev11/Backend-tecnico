package com.peter.backend_technical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.peter.backend_technical.entity.UsuariosEntity;


@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity , Integer>, JpaSpecificationExecutor<UsuariosEntity>{
	
	Optional<UsuariosEntity> findByNombre(String nombre);
}
