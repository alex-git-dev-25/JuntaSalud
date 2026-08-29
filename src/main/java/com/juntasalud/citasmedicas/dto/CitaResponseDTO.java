package com.juntasalud.citasmedicas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.juntasalud.citasmedicas.enums.EstadoCita;
import com.juntasalud.citasmedicas.enums.MedioVirtual;
import com.juntasalud.citasmedicas.enums.ModalidadCita;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CitaResponseDTO {
	
	private Long idCita;
	
	private LocalDate fecha;
	private LocalTime hora;

	private String nroActoMedico;
	
	private String lugar;
	private String consultorio;
	
	private String direccion;
	
	private String especialidad;
	private String medico;
	private String servicio; 
	
	private ModalidadCita modalidad;
	private MedioVirtual medioVirtual;
	private EstadoCita estado;
	
}
