package com.juntasalud.citasmedicas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.juntasalud.citasmedicas.enums.MedioVirtual;
import com.juntasalud.citasmedicas.enums.ModalidadCita;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CitaRequestDTO {
	
	@NotNull(message="La fecha es obligatoria")
	private LocalDate fecha;
	@NotNull(message="La hora es obligatoria")
	private LocalTime hora;
	private String nroActoMedico;

	private Long establecimientoCod;
	private Long especialidadCod;
	@NotNull(message="El servicio es obligatorio")
	private Long servicioCod;
	
	private String consultorio;
	private String medico;
	@NotNull(message="La modalidad es obligatoria")
	private ModalidadCita modalidad;
	private String direccion;
	private MedioVirtual medioVirtual;

}
