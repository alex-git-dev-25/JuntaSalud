package com.juntasalud.citasmedicas.service.impl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juntasalud.citasmedicas.dto.CitaResponseDTO;
import com.juntasalud.citasmedicas.enums.EstadoCita;
import com.juntasalud.citasmedicas.mapper.CitaMapper;
import com.juntasalud.citasmedicas.repository.CitaRepository;
import com.juntasalud.citasmedicas.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService{

	private final Clock horaZona;
	private final CitaRepository citaRepo;
	private final CitaMapper citaMapper;
	
	public CitaServiceImpl(CitaRepository citaRepo, Clock horaZona,
							CitaMapper citaMapper) {
		this.citaRepo=citaRepo;
		this.horaZona=horaZona;
		this.citaMapper=citaMapper;
	}
	
	@Override
	public List<CitaResponseDTO> listarCitas() {
		return citaRepo.findAll()
				.stream()
				.map(citaMapper::toResponseDTO)
				.toList();
	}
	
	@Override
	public List<CitaResponseDTO> buscarPorEspecialidad(String nombreEspecialidad) {
		return citaRepo
				.findByEspecialidad_NomEspecialidadContainingIgnoreCase(nombreEspecialidad)
				.stream()
				.map(citaMapper::toResponseDTO)
				.toList();
	}

	@Override
	public List<CitaResponseDTO> listarProximasCitas() {
		LocalDateTime fechaHoraActual=LocalDateTime.now(horaZona);
	
		LocalDate fechaActual=fechaHoraActual.toLocalDate();
		LocalTime horaActual=fechaHoraActual.toLocalTime();
		
		List<EstadoCita> estadosProximos = List.of(EstadoCita.PENDIENTE, EstadoCita.REPROGRAMADA);
		return citaRepo
				.listarProximasCitasQuery(fechaActual, horaActual, estadosProximos)
				.stream()
				.map(citaMapper::toResponseDTO)
				.toList();
	}

	@Override
	public List<CitaResponseDTO> buscarPorEstablecimiento(String nombreEstablecimiento) {
		return citaRepo
				.findByEstablecimiento_NombreEstablecimientoContainingIgnoreCase(nombreEstablecimiento)
				.stream()
				.map(citaMapper::toResponseDTO)
				.toList();
	}

}
