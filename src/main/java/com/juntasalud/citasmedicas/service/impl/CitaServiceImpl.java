package com.juntasalud.citasmedicas.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juntasalud.citasmedicas.entity.Cita;
import com.juntasalud.citasmedicas.enums.EstadoCita;
import com.juntasalud.citasmedicas.repository.CitaRepository;
import com.juntasalud.citasmedicas.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService{

	private final CitaRepository repo;
	
	public CitaServiceImpl(CitaRepository repo) {
		this.repo=repo;
	}
	
	@Override
	public List<Cita> listarCitas() {
		return repo.findAll();
	}

	@Override
	public List<Cita> buscarPorEspecialidad(String nombreEspecialidad) {
		return repo.findByEspecialidad_NomEspecialidadContainingIgnoreCase(nombreEspecialidad);
	}

	@Override
	public List<Cita> listarProximasCitas() {
		LocalDate hoy=LocalDate.now();
		LocalTime ahora=LocalTime.now();
		List<EstadoCita> estadosProximos = List.of(EstadoCita.PENDIENTE, EstadoCita.REPROGRAMADA);
		return repo.listarProximasCitasQuery(hoy, ahora, estadosProximos);
	}

	@Override
	public List<Cita> buscarPorEstablecimiento(String nombreEstablecimiento) {
		return repo.findByEstablecimiento_NombreEstablecimientoContainingIgnoreCase(nombreEstablecimiento);
	}

}
