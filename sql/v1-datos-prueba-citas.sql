
BEGIN;

-- 1. Establecimientos
INSERT INTO tb_establecimiento (nombre_establecimiento) VALUES
('Hospital Norte'),
('Hospital Centro'),
('Hospital Sur');

-- 2. Especialidades
INSERT INTO tb_especialidad (nom_especialidad) VALUES
('Medicina General'), ('Neurología'), ('Cardiología'),
('Traumatología'), ('Oftalmología'), ('Psicología');

-- 3. Servicios
INSERT INTO tb_servicio (nombre_servicio, requiere_especialidadymedico) VALUES
('Consulta general', true),
('Especialidad médica', true),
('Análisis clínico', false),
('Imagenología', false);

-- 4. Modalidades permitidas por servicio
INSERT INTO tb_servicio_modalidad (id_servicio, modalidad) VALUES
(1,'PRESENCIAL'), (1,'VIRTUAL'), (1,'DOMICILIO'),
(2,'PRESENCIAL'), (2,'VIRTUAL'), (2,'DOMICILIO'),
(3,'PRESENCIAL'), (3,'DOMICILIO'),
(4,'PRESENCIAL');

-- 5. Citas (10, PENDIENTE, coherentes con tus reglas de negocio)
INSERT INTO tb_cita
(fecha_cita, hora_cita, consultorio_cita, nro_acto_medico, id_establecimiento, id_especialidad, medico, id_servicio, modalidad, direccion, medio_virtual, estado)
VALUES
('2026-09-25','09:00','101A','AM-001', 2, 1, 'Carlos Ramírez Soto', 1, 'PRESENCIAL', NULL, NULL, 'PENDIENTE'),
('2026-09-26','10:30', NULL,'AM-002', NULL, 3, 'Lucía Fernández Vega', 2, 'VIRTUAL', NULL, 'VIDEOLLAMADA', 'PENDIENTE'),
('2026-09-27','08:00','Lab-3','AM-003', 3, NULL, NULL, 3, 'PRESENCIAL', NULL, NULL, 'PENDIENTE'),
('2026-09-28','11:15','Rayos X-1','AM-004', 1, NULL, NULL, 4, 'PRESENCIAL', NULL, NULL, 'PENDIENTE'),
('2026-09-29','14:00', NULL,'AM-005', NULL, 1, 'José Torres Medina', 1, 'DOMICILIO', 'Av. 123', NULL, 'PENDIENTE'),
('2026-09-30','09:45','205B','AM-006', 2, 2, 'Mariana Ríos Castillo', 2, 'PRESENCIAL', NULL, NULL, 'PENDIENTE'),
('2026-10-01','07:30', NULL,'AM-007', NULL, NULL, NULL, 3, 'DOMICILIO', 'Av. 123', NULL, 'PENDIENTE'),
('2026-10-02','16:00', NULL,'AM-008', NULL, 6, 'Andrés Quispe Huamán', 2, 'VIRTUAL', NULL, 'TELEFONO', 'PENDIENTE'),
('2026-10-03','10:00','102A','AM-009', 3, 1, 'Carlos Ramírez Soto', 1, 'PRESENCIAL', NULL, NULL, 'PENDIENTE'),
('2026-10-04','13:30','301C','AM-010', 1, 4, 'Lucía Fernández Vega', 2, 'PRESENCIAL', NULL, NULL, 'PENDIENTE');

COMMIT;