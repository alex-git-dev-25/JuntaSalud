package com.juntasalud.citasmedicas.validation;

import org.springframework.stereotype.Component;

import com.juntasalud.citasmedicas.entity.Especialidad;
import com.juntasalud.citasmedicas.entity.Establecimiento;
import com.juntasalud.citasmedicas.entity.Servicio;
import com.juntasalud.citasmedicas.enums.MedioVirtual;
import com.juntasalud.citasmedicas.enums.ModalidadCita;
import com.juntasalud.citasmedicas.exception.ReglaNegocioException;

@Component
public class CitaValidator {
	public void validarServicioModalidad(Servicio servicio, ModalidadCita modalidad) {
		if (!servicio.getModalidadesPermitidas().contains(modalidad)) {
			throw new ReglaNegocioException(
					"El servicio '" + servicio.getNombreServicio() + "' no permite la modalidad " + modalidad+".");
		}
	}

	public void validarEspecialidadYMedico(Servicio servicio, Especialidad especialidad, String medico) {
		if (servicio.isRequiereEspecialidadYMedico()) {
			boolean datosCompletos = especialidad != null && medico != null && !medico.isBlank();
			if (!datosCompletos) {
				throw new ReglaNegocioException(
						"El servicio '" + servicio.getNombreServicio() + "' requiere especialidad y medico");
			}
		} else {
			boolean datosVacios = especialidad == null && (medico == null || medico.isBlank());
			if (!datosVacios) {
				throw new ReglaNegocioException(
						"El servicio '" + servicio.getNombreServicio() + "' no necesita especialidad ni medico.");
			}
		}
	}

	public void validarUbicacionSegunModalidad(ModalidadCita modalidad, Establecimiento establecimiento,
			String direccion, MedioVirtual medioVirtual) {
		switch (modalidad) {
			case PRESENCIAL -> {
				boolean ubicacionValida = establecimiento != null && direccion == null && medioVirtual == null;
				if(!ubicacionValida) {
					throw new ReglaNegocioException("La modalidad PRESENCIAL debe tener establecimiento, sin direccion ni medio virtual.");
				}
			}
			case VIRTUAL -> {
				boolean ubicacionValida = establecimiento == null && direccion == null && medioVirtual != null;
				if(!ubicacionValida) {
					throw new ReglaNegocioException("La modalidad VIRTUAL debe tener medio de comunicacion, sin establecimiento ni direccion.");
				}
			}
			case DOMICILIO -> {
				boolean ubicacionValida = establecimiento == null && direccion != null && !direccion.isBlank() && medioVirtual == null;
				if(!ubicacionValida) {
					throw new ReglaNegocioException("La modalidad A DOMICILIO debe tener direccion, sin establecimiento ni medio virtual.");
				}
			}
		}
	}
}
