package com.juntasalud.citasmedicas.service.impl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juntasalud.citasmedicas.entity.Cita;
import com.juntasalud.citasmedicas.enums.EstadoCita;
import com.juntasalud.citasmedicas.repository.CitaRepository;
import com.juntasalud.citasmedicas.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService{

	private final Clock horaZona;
	private final CitaRepository citaRepo;
	
	public CitaServiceImpl(CitaRepository citaRepo, Clock horaZona) {
		this.citaRepo=citaRepo;
		this.horaZona=horaZona;
	}
	
	@Override
	public List<Cita> listarCitas() {
		return citaRepo.findAll();
	}
	
	@Override
	public List<Cita> buscarPorEspecialidad(String nombreEspecialidad) {
		return citaRepo.findByEspecialidad_NomEspecialidadContainingIgnoreCase(nombreEspecialidad);
	}

	@Override
	public List<Cita> listarProximasCitas() {
		LocalDateTime fechaHoraActual=LocalDateTime.now(horaZona);
	
		LocalDate fechaActual=fechaHoraActual.toLocalDate();
		LocalTime horaActual=fechaHoraActual.toLocalTime();
		
		List<EstadoCita> estadosProximos = List.of(EstadoCita.PENDIENTE, EstadoCita.REPROGRAMADA);
		return citaRepo.listarProximasCitasQuery(fechaActual, horaActual, estadosProximos);
	}

	@Override
	public List<Cita> buscarPorEstablecimiento(String nombreEstablecimiento) {
		return citaRepo.findByEstablecimiento_NombreEstablecimientoContainingIgnoreCase(nombreEstablecimiento);
	}

}
