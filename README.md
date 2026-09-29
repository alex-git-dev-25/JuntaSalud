# JuntaSalud API

API REST para gestionar citas médicas de un paciente: registro, consulta de historial, próximas citas y búsqueda por especialidad o establecimiento.

Proyecto personal desarrollado para practicar diseño de backend con Spring Boot + PostgreSQL, aplicando arquitectura por capas, reglas de negocio explícitas y buenas prácticas de API REST.

## Estado del proyecto

| Módulo | Estado |
|---|---|
| Modelo de datos (Cita, Establecimiento, Especialidad, Servicio) | ✅ completo |
| Reglas de negocio (servicio–modalidad, especialidad–médico, ubicación) | ✅ completo |
| CRUD de Cita — Crear / Consultar | ✅ completo y probado |

## Tecnologías

- Java 21
- Spring Boot 4.1.1 (Web MVC, Data JPA, Validation)
- PostgreSQL
- Lombok
- Maven

## Arquitectura

Arquitectura por capas (Controller → Service → Repository), con separación explícita de responsabilidades:

- **DTO + Mapper**: las entidades JPA nunca se exponen directo en la API.
- **`validation/`**: reglas de negocio aisladas de los controllers y del acceso a datos (ej. qué combinaciones de servicio/modalidad son válidas).
- **Catálogos vs. Enums**: `Establecimiento`, `Especialidad` y `Servicio` son entidades (catálogos administrables, pueden crecer sin recompilar); `EstadoCita`, `ModalidadCita` y `MedioVirtual` son enums (conjuntos cerrados y estables).
- **Manejo centralizado de errores** vía `@RestControllerAdvice`, con logging de errores no controlados y respuestas de error consistentes en JSON.

## Modelo de datos (resumen)

- **Cita**: entidad principal — fecha, hora, modalidad, estado, y relaciones opcionales/obligatorias según la modalidad (establecimiento para presencial, dirección para domicilio, medio virtual para teleconsulta).
- **Servicio**: catálogo de servicios médicos, cada uno con sus modalidades permitidas y si requiere especialidad/médico asociado.
- **Establecimiento** / **Especialidad**: catálogos simples de apoyo.

## Datos de prueba

El proyecto incluye un script de datos semilla en [`sql/v1-datos-prueba-citas.sql`](./sql/v1-datos-prueba-citas.sql) con establecimientos, especialidades, servicios y citas de ejemplo.

> **Todos los datos son ficticios**, generados únicamente con fines de demostración y prueba. No corresponden a pacientes, médicos ni establecimientos reales.

## Endpoints disponibles

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/api/citas` | Lista todas las citas |
| `GET` | `/api/citas?especialidad={nombre}` | Filtra citas por especialidad |
| `GET` | `/api/citas?establecimiento={nombre}` | Filtra citas por establecimiento |
| `GET` | `/api/citas/proximas` | Lista citas pendientes/reprogramadas con fecha futura |
| `GET` | `/api/citas/{id}` | Obtiene una cita por ID |
| `POST` | `/api/citas` | Registra una nueva cita |

## Probar la API con Postman

Se debe importar la colección manualmente desde [`postman/JuntaSalud.postman_collection.json`](./postman/JuntaSalud.postman_collection.json): Postman → *Import* → seleccionar el archivo.

La colección incluye 13 casos ya armados (consultas exitosas, filtros, y los 3 tipos de error que maneja la API: 400 por validación, 404 por recurso inexistente, 409 por reglas de negocio). Requiere haber cargado antes `sql/v1-datos-prueba-citas.sql`.

## Cómo levantarlo en local

**Requisitos:** JDK 21, Maven, PostgreSQL.

1. Clona el repositorio y ubícate en la carpeta raíz del proyecto (la que contiene `pom.xml`):
```bash
   git clone https://github.com/alex-git-dev-25/JuntaSalud.git
   cd JuntaSalud
```
Todos los comandos siguientes se ejecutan **desde esta carpeta**.

2. Crea la base de datos en PostgreSQL con el nombre `db_juntasalud` (los pasos siguientes asumen este nombre exacto):
```bash
   psql -U tu_usuario -c "CREATE DATABASE db_juntasalud;"
```

3. Define las variables de entorno (o un `.env` según tu configuración local):
```
   DB_URL=jdbc:postgresql://localhost:5432/db_juntasalud
   DB_USERNAME=tu_usuario
   DB_PASSWORD=tu_password
```
4. Desde la raíz del proyecto, levanta la aplicación:
```bash
   mvn spring-boot:run
```
   Al iniciar por primera vez, Hibernate crea el esquema automáticamente (`ddl-auto=update`). Déjalo corriendo un momento y luego deténlo (`Ctrl+C`) o déjalo activo en otra terminal — solo necesitas que haya creado las tablas antes del siguiente paso.

5. En otra terminal, **también desde la raíz del proyecto** (la ruta `sql/v1-datos-prueba-citas.sql` es relativa a esta carpeta), carga los datos de prueba:
```bash
   psql -U tu_usuario -d db_juntasalud -f sql/v1-datos-prueba-citas.sql
```
6. Vuelve a ejecutar `mvn spring-boot:run` si lo habías detenido. La API queda disponible en `http://localhost:8080`. Importa la colección de Postman para probarla de inmediato.

## Autor

Alexander — proyecto personal de práctica y portafolio.
