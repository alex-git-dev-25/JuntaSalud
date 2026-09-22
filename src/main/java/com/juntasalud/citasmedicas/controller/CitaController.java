package com.juntasalud.citasmedicas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juntasalud.citasmedicas.dto.CitaRequestDTO;
import com.juntasalud.citasmedicas.dto.CitaResponseDTO;
import com.juntasalud.citasmedicas.service.CitaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

	private final CitaService citaService;

	public CitaController(CitaService citaService) {
		this.citaService = citaService;
	}

	@GetMapping
	public List<CitaResponseDTO> listarCitas(@RequestParam(required = false) String especialidad,
			@RequestParam(required = false) String establecimiento) {
		if (especialidad != null && !especialidad.isBlank()) {
			return citaService.buscarPorEspecialidad(especialidad);
		}
		if (establecimiento != null && !establecimiento.isBlank()) {
			return citaService.buscarPorEstablecimiento(establecimiento);
		}
		return citaService.listarCitas();
	}

	@GetMapping("/proximas")
	public List<CitaResponseDTO> proximasCitas() {
		return citaService.listarProximasCitas();
	}
	
	@GetMapping("/{id}")
	public CitaResponseDTO buscarPorId(@PathVariable Long id) {
		return citaService.buscarPorId(id);
	}

	@PostMapping
	public ResponseEntity<CitaResponseDTO> registrarCita(@Valid @RequestBody CitaRequestDTO request) {
		CitaResponseDTO nuevaCita = citaService.registrarCita(request);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(nuevaCita.getIdCita()).toUri();
		return ResponseEntity.created(ubicacion).body(nuevaCita);
	}
}
