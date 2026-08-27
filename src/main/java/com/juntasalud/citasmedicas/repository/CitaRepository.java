package com.juntasalud.citasmedicas.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.juntasalud.citasmedicas.entity.Cita;
import com.juntasalud.citasmedicas.enums.EstadoCita;


public interface CitaRepository extends JpaRepository<Cita, Long>{
	List<Cita> findByEspecialidad_NomEspecialidadContainingIgnoreCase(
			String nombreEspecialidad);
	
	List<Cita> findByEstablecimiento_NombreEstablecimientoContainingIgnoreCase(
			String nombreEstablecimiento);
	
	@Query("SELECT c FROM Cita c WHERE c.estado IN :estados AND (c.fechaCita > :fecha OR (c.fechaCita = :fecha AND c.horaCita >= :hora))")
		List<Cita> listarProximasCitasQuery(@Param("fecha") LocalDate fecha, @Param("hora") LocalTime hora, @Param("estados") List<EstadoCita> estados);
	
}
