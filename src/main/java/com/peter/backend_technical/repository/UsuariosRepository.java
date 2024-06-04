package com.peter.backend_technical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.peter.backend_technical.entity.UsuariosEntity;


@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity , Integer>, JpaSpecificationExecutor<UsuariosEntity>{

	@Query("SELECT curp FROM UsuariosEntity")
	List<String> findCurp();
	
	@Query("SELECT cp FROM UsuariosEntity")
	List<Integer> findCp();
	
	@Query("SELECT rfc FROM UsuariosEntity")
	List<String> findRfc();
	
	@Query("SELECT telefono FROM UsuariosEntity")
	List<String> findTelefono();
}
