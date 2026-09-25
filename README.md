# Sistema de Control del Grupo POO

## Integrantes

* Martin Hofer Ochoa - RUT 22.368.055-0 - ICCI

## Descripción

Este proyecto corresponde a un sistema desarrollado en Java para administrar la inscripción y organización de estudiantes en los paralelos C1 y C2 de la asignatura de Programación Orientada a Objetos (POO).

El programa permite cargar información desde archivos de texto, procesar solicitudes de inscripción, administrar estudiantes y generar distintos reportes.

## Funcionalidades

El sistema cuenta con las siguientes opciones:

### 1. Cargar archivos

Permite cargar la información de los estudiantes y las solicitudes desde los archivos:

* `alumnos.txt`
* `solicitudes.txt`

Los datos de los alumnos contienen:

* Nombre
* Apellido
* RUT
* Paralelo

### 2. Procesar solicitudes

Procesa automáticamente las solicitudes ingresadas y determina si el estudiante puede ser agregado al grupo.

Los estudiantes que pertenecen al curso son admitidos y los que no pertenecen son registrados como rechazados.

### 3. Inscripción manual

Permite agregar manualmente un estudiante al grupo utilizando:

* Nombre y apellido
* RUT

El sistema verifica que el estudiante exista y que no se encuentre previamente inscrito.

### 4. Administración

Permite realizar diferentes modificaciones sobre los estudiantes:

* Cambiar el paralelo de un alumno entre C1 y C2.
* Eliminar un alumno del curso.
* Inscribir un alumno nuevo.

Los cambios realizados también se actualizan en el archivo `alumnos.txt`.

### 5. Generar reportes

Permite generar reportes de:

* Miembros del paralelo C1.
* Miembros del paralelo C2.
* Solicitudes rechazadas.

Los reportes se guardan en la carpeta `Reportes` y utilizan versiones para no sobrescribir reportes anteriores.

Ejemplo:

```text
ReporteC1-V1.txt
ReporteC1-V2.txt
ReporteC2-V1.txt
Rechazados-V1.txt
```

### 6. Análisis estadístico

Permite obtener información estadística sobre las solicitudes procesadas, incluyendo:

* Cantidad de estudiantes admitidos.
* Cantidad de estudiantes rechazados.
* Cantidad de estudiantes por paralelo.
* Porcentajes de admisión y rechazo.

## Estructura del proyecto

```text
Taller1_POO
│
├── src
│   └── taller01
│       └── Main.java
│
├── txt's
│   ├── alumnos.txt
│   └── solicitudes.txt
│
├── Reportes
│   ├── ReporteC1-VX.txt
│   ├── ReporteC2-VX.txt
│   └── Rechazados-VX.txt
│
└── README.md
```

## Tecnologías utilizadas

* Java
* Eclipse
* GitHub
* Archivos de texto `.txt`

## Ejecución

Para ejecutar el programa:

1. Abrir el proyecto en Eclipse.
2. Verificar que los archivos `alumnos.txt` y `solicitudes.txt` estén dentro de la carpeta `txt's`.
3. Ejecutar la clase `Main.java`.
4. Utilizar el menú principal para seleccionar las distintas opciones.

## Consideraciones

El sistema trabaja con una capacidad máxima de 100 estudiantes y utiliza arreglos para almacenar la información.

Los archivos generados por el programa se guardan dentro de la carpeta `Reportes`.

## Repositorio

[GitHub - Taller1_POO](https://github.com/mrtinzinn/Taller1_POO)

