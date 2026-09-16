package com.juntasalud.citasmedicas.service.impl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juntasalud.citasmedicas.dto.CitaRequestDTO;
import com.juntasalud.citasmedicas.dto.CitaResponseDTO;
import com.juntasalud.citasmedicas.entity.Cita;
import com.juntasalud.citasmedicas.entity.Especialidad;
import com.juntasalud.citasmedicas.entity.Establecimiento;
import com.juntasalud.citasmedicas.entity.Servicio;
import com.juntasalud.citasmedicas.enums.EstadoCita;
import com.juntasalud.citasmedicas.exception.ResourceNotFoundException;
import com.juntasalud.citasmedicas.mapper.CitaMapper;
import com.juntasalud.citasmedicas.repository.CitaRepository;
import com.juntasalud.citasmedicas.repository.EspecialidadRepository;
import com.juntasalud.citasmedicas.repository.EstablecimientoRepository;
import com.juntasalud.citasmedicas.repository.ServicioRepository;
import com.juntasalud.citasmedicas.service.CitaService;
import com.juntasalud.citasmedicas.validation.CitaValidator;

import jakarta.transaction.Transactional;

@Service
public class CitaServiceImpl implements CitaService {

	private final Clock horaZona;
	private final CitaRepository citaRepo;
	private final EstablecimientoRepository establecimientoRepo;
	private final EspecialidadRepository especialidadRepo;
	private final ServicioRepository servicioRepo;
	private final CitaValidator citaValidator;

	private final CitaMapper citaMapper;

	public CitaServiceImpl(Clock horaZona, CitaMapper citaMapper, CitaValidator citaValidator, CitaRepository citaRepo,
			EstablecimientoRepository establecimientoRepo, EspecialidadRepository especialidadRepo,
			ServicioRepository servicioRepo) {
		this.citaRepo = citaRepo;
		this.horaZona = horaZona;
		this.citaValidator = citaValidator;
		this.citaMapper = citaMapper;
		this.establecimientoRepo = establecimientoRepo;
		this.especialidadRepo = especialidadRepo;
		this.servicioRepo = servicioRepo;
	}

	@Override
	public List<CitaResponseDTO> listarCitas() {
		return citaRepo.findAll().stream().map(citaMapper::toResponseDTO).toList();
	}

	@Override
	public List<CitaResponseDTO> buscarPorEspecialidad(String nombreEspecialidad) {
		return citaRepo.findByEspecialidad_NomEspecialidadContainingIgnoreCase(nombreEspecialidad).stream()
				.map(citaMapper::toResponseDTO).toList();
	}

	@Override
	public List<CitaResponseDTO> listarProximasCitas() {
		LocalDateTime fechaHoraActual = LocalDateTime.now(horaZona);

		LocalDate fechaActual = fechaHoraActual.toLocalDate();
		LocalTime horaActual = fechaHoraActual.toLocalTime();

		List<EstadoCita> estadosProximos = List.of(EstadoCita.PENDIENTE, EstadoCita.REPROGRAMADA);
		return citaRepo.listarProximasCitasQuery(fechaActual, horaActual, estadosProximos).stream()
				.map(citaMapper::toResponseDTO).toList();
	}

	@Override
	public List<CitaResponseDTO> buscarPorEstablecimiento(String nombreEstablecimiento) {
		return citaRepo.findByEstablecimiento_NombreEstablecimientoContainingIgnoreCase(nombreEstablecimiento).stream()
				.map(citaMapper::toResponseDTO).toList();
	}

	@Transactional
	@Override
	public CitaResponseDTO registrarCita(CitaRequestDTO request) {

		// 1. VALIDAR QUE EL SERVICIO EXISTA
		Servicio servicio = servicioRepo.findById(request.getServicioCod())
				.orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));

		// 2. VERIFICAR QUE EL ESTABLECIMIENTO EXISTA
		// Se busca solo cuando la solicitud brinda un código.
		Establecimiento establecimiento = request.getEstablecimientoCod() != null
				? establecimientoRepo.findById(request.getEstablecimientoCod()).orElseThrow(
						() -> new ResourceNotFoundException("Establecimiento no encontrado"))
				: null;

		// 3. VERIFICAR QUE LA ESPECIALIDAD EXISTA
		// Se busca solo cuando la solicitud brinda un código.
		Especialidad especialidad = request.getEspecialidadCod() != null
				? especialidadRepo.findById(request.getEspecialidadCod()).orElseThrow(
						() -> new ResourceNotFoundException("Especialidad no encontrada"))
				: null;

		// 4. VALIDAR QUE EL SERVICIO PERMITA LA MODALIDAD SOLICITADA
		citaValidator.validarServicioModalidad(servicio, request.getModalidad());

		// 5. VALIDAR ESPECIALIDAD Y MÉDICO SEGÚN EL SERVICIO
		citaValidator.validarEspecialidadYMedico(servicio, especialidad, request.getMedico());

		// 6. VALIDAR LOS DATOS DE UBICACIÓN SEGÚN LA MODALIDAD
		citaValidator.validarUbicacionSegunModalidad(request.getModalidad(), establecimiento, request.getDireccion(),
				request.getMedioVirtual());

		// 7. CREAR LA ENTIDAD CITA
		Cita cita = citaMapper.toEntity(request, servicio, establecimiento, especialidad);

		// 8. ESTABLECER EL ESTADO INICIAL DE LA NUEVA CITA
		cita.setEstado(EstadoCita.PENDIENTE);
		
		// 9. GUARDAR LA NUEVA CITA
		Cita citaGuardada = citaRepo.save(cita);
		
		// 10. RETORNAR LA CITA COMO DTO
		return citaMapper.toResponseDTO(citaGuardada);
	}
}
