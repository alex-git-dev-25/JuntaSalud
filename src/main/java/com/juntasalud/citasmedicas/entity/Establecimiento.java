package com.juntasalud.citasmedicas.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_establecimiento")
@Getter
@Setter
@NoArgsConstructor
public class Establecimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstablecimiento;
	
	@Column(length=80,nullable=false,unique=true)
	private String nombreEstablecimiento;
	
}
