package com.juntasalud.citasmedicas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.juntasalud.citasmedicas.enums.MedioVirtual;
import com.juntasalud.citasmedicas.enums.ModalidadCita;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CitaRequestDTO {

	private LocalDate fecha;
	private LocalTime hora;
	private String nroActoMedico;

	private Long lugarCod;
	private Long especialidadCod;
	private Long servicioCod;
	
	private String consultorio;
	private String medico;
	private ModalidadCita modalidad;
	private String direccion;
	private MedioVirtual medioVirtual;

}
