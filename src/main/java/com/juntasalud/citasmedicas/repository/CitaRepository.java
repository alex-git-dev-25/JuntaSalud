package com.juntasalud.citasmedicas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juntasalud.citasmedicas.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long>{
	List<Cita> findByEspecialidad_NomEspecialidadContainingIgnoreCase(
			String nombreEspecialidad);
	
	List<Cita> findByEstablecimiento_NombreEstablecimientoContainingIgnoreCase(
			String nombreEstablecimiento);
	
}
