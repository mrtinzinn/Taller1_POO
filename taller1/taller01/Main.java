//Martin Hofer Ochoa - 22.368.055-0 - mrtinzinn
//https://github.com/mrtinzinn/Taller1_POO/tree/main	

package taller01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static Scanner scanner = new Scanner(System.in);
	static int cantAlumnos = 0;
	static String[] nombres = new String[100];
	static String[] apellidos = new String[100];
	static String[] ruts = new String[100];
	static String[] paralelos = new String[100];
	static String[] solicitud = new String[100];
	static String[] grupo = new String[100];
	static String[] rechazados = new String[100];
	public static boolean lectura = false; // Creo un booleando que verifica si se leyeron ambos archivos para que no se
	public static boolean procesados = false; // caiga el codigo.

	public static void main(String[] args) throws FileNotFoundException {
		
		printMenu();
		int opcion = scanner.nextInt();
		opcion = controlError(opcion, 1, 7);
		// CREAR WHILE Y OPCIONES DEL MENU

		while (opcion != 7) {

			if (opcion == 1) { // CARGAR ARCHIVOS
				if (!lectura) {
					System.out.println("Cargando archivos...");
					cantAlumnos = leerAlumnos();
					solicitudes();
					System.out.println("Los archivos han sido cargados con exito.");
					lectura = true;
				}

			}
			if (opcion == 2) { // FILTRAR ALUMNOS

				if (lectura) {
					opcion2();
					procesados = true;
				} else {
					System.out.println(
							"No se han cargado los archivos , por favor seleccione la opcion 1 para poder filtrar...");
				}
			}
			if (opcion == 3) {
				opcion3();
			}
			if(opcion == 4) {
				opcion4();
			}
			if (opcion ==5) {
				opcion5();
			}
			System.out.println();
			printMenu();
			opcion = scanner.nextInt();
			opcion = controlError(opcion, 1, 7);
		}
		System.out.println("Saliendo... Nos vemos!");
	}

	private static void opcion5() {
		if (lectura && procesados) {
			System.out.println("-- Generar reportes --");
			System.out.println("1) Nuevo paralelo C1.");
			System.out.println("2) Nuevo paralelo C2.");
			System.out.println("3) Reporte rechazados.");
			System.out.println("4) Volver");
			System.out.print("Ingrese una respuesta: ");
			int respuesta = scanner.nextInt();
			respuesta = controlError(respuesta, 1, 4);
			if(respuesta == 1) {
				generarReporteC1();
			}
			if(respuesta == 2) {
				generarReporteC2();
			}
			if(respuesta == 3) {
				generarReporteRechazados();
			}
			
			
		}else {
			System.out.println("Primero debe cargar los archivos.");
		}
		
		
	}

	private static void generarReporteRechazados() {

		int version = 1;

		String nombreArchivo = "Reportes/Rechazados-V" + version + ".txt";

		while (new File(nombreArchivo).exists()) {
		    version++;
		    nombreArchivo = "Reportes/Rechazados-V" + version + ".txt";
		}
		try {
			File carpeta = new File("Reportes");
			if (!carpeta.exists()) {
				carpeta.mkdir();
			}

			FileWriter archivo = new FileWriter(nombreArchivo);
			BufferedWriter escritor = new BufferedWriter(archivo);
			escritor.write("=== Solicitudes rechazadas ===");
			escritor.newLine();

			for (int i = 0; i < rechazados.length; i++) {
				if (rechazados[i] != null) {
					if (rechazados[i].contains(" ")) {
						
						escritor.write(rechazados[i] + " - No pertenece a ningun paralelo del curso");
					} else {
						escritor.write("Sin nombre registrado, RUT: " + rechazados[i]);
					}
					escritor.newLine();
				}
			}
			escritor.close();

			System.out.println("Reporte de rechazados generado correctamente.");
		} catch (IOException e) {
			System.out.println("No se pudo generar el reporte.");
		}

	}

	private static void generarReporteC2() {

		int version = 1;

		String nombreArchivo = "Reportes/ReporteC2-V" + version + ".txt";

		while (new File(nombreArchivo).exists()) {
		    version++;
		    nombreArchivo = "Reportes/ReporteC2-V" + version + ".txt";
		}
		try {
			File carpeta = new File("Reportes");
			if (!carpeta.exists()) {
				carpeta.mkdir();
			}

			FileWriter archivo = new FileWriter(nombreArchivo);
			BufferedWriter escritor = new BufferedWriter(archivo);
			escritor.write("=== Miembros del grupo - Paralelo C2 ===");
			escritor.newLine();
			for (int i = 0; i < grupo.length; i++) {
				if (grupo[i] != null) {
					for (int j = 0; j < cantAlumnos; j++) {
						String persona = nombres[j] + " " + apellidos[j];
						if (grupo[i].equalsIgnoreCase(persona) && paralelos[j].equalsIgnoreCase("C2")) {
							escritor.write(persona + " - " + ruts[j]);
							escritor.newLine();
						}
					}
				}
			}
			escritor.close();
			System.out.println("Reporte C2 generado correctamente.");
		} catch (IOException e) {
			System.out.println("No se pudo generar el reporte.");
		}
	}

	private static void generarReporteC1() {

		int version = 1;

		String nombreArchivo = "Reportes/ReporteC1-V" + version + ".txt";

		while (new File(nombreArchivo).exists()) {
		    version++;
		    nombreArchivo = "Reportes/ReporteC1-V" + version + ".txt";
		}
		try {
			File carpeta = new File("Reportes");
			if (!carpeta.exists()) {
				carpeta.mkdir();
			}

			FileWriter archivo = new FileWriter(nombreArchivo);
			BufferedWriter escritor = new BufferedWriter(archivo);
			escritor.write("=== Miembros del grupo - Paralelo C1 ===");
			escritor.newLine();

			for (int i = 0; i < grupo.length; i++) {
				if (grupo[i] != null) {
					for (int j = 0; j < cantAlumnos; j++) {
						String persona = nombres[j] + " " + apellidos[j];
						if (grupo[i].equalsIgnoreCase(persona) && paralelos[j].equalsIgnoreCase("C1")) {
							escritor.write(persona + " - " + ruts[j]);
							escritor.newLine();
						}
					}
				}
			}
			escritor.close();
			System.out.println("Reporte C1 generado correctamente.");
		} catch (IOException e) {
			System.out.println("No se pudo generar el reporte.");
		}
	}

	private static void opcion4() {

		System.out.println("-- Administracion del curso --");

		System.out.println("1) Cambiar paralelo de un alumno.");
		System.out.println("2) Eliminar alumno del curso.");
		System.out.println("3) Inscribir alumno nuevo");
		System.out.println("4) Salir.");

		System.out.print("Seleccione una opcion: ");

		int respuesta = scanner.nextInt();
		respuesta = controlError(respuesta, 1, 4);
		scanner.nextLine();

		if (lectura) {

			if (respuesta == 1) {
				System.out.print("Seleccione el RUT del alumno: ");
				String rut = scanner.nextLine();
				int pos = 0;
				boolean esta = false;

				// BUSCAR EL ALUMNO POR RUT
				for (int i = 0; i < cantAlumnos; i++) {
					if (ruts[i].equalsIgnoreCase(rut)) {
						esta = true;
						pos = i;
						break;
					}
				}

				if (esta) {

					System.out.println("Alumno: " + nombres[pos] + " " + apellidos[pos] + " actualmente pertenece a " + paralelos[pos]);
					System.out.print("Nuevo paralelo (C1/C2): ");
					String paraleloNuevo = scanner.nextLine();
					// VALIDAR PARALELO
					while (!paraleloNuevo.equalsIgnoreCase("C1")
							&& !paraleloNuevo.equalsIgnoreCase("C2")) {
						System.out.println("Ingrese una opcion valida (C1/C2): ");
						paraleloNuevo = scanner.nextLine();
					}

					// VERIFICAR QUE NO SEA EL MISMO PARALELO
					while (paraleloNuevo.equalsIgnoreCase(paralelos[pos])) {
						System.out.print("El alumno ya esta en ese paralelo, intente de nuevo: ");
						paraleloNuevo = scanner.nextLine();
						while (!paraleloNuevo.equalsIgnoreCase("C1")
								&& !paraleloNuevo.equalsIgnoreCase("C2")) {
							System.out.println("Ingrese una opcion valida (C1/C2): ");
							paraleloNuevo = scanner.nextLine();
						}
					}

					// CAMBIAR PARALELO
					paralelos[pos] = paraleloNuevo;
					System.out.println("El alumno fue cambiado al paralelo " + paralelos[pos]);
					try {
						guardaralumnos();
					}catch (Exception e) {
						System.out.println("No se pudo actualizar el archivo...");
					}
					
				} else {
					System.out.println("El RUT no pertenece a ningun alumno del curso.");
				}
			}

			if (respuesta == 2) {
				System.out.print("Seleccione el nombre y apellido del alumno: ");
				String alumno = scanner.nextLine();

				int pos = 0;
				boolean esta = false;

				// BUSCAR AL ALUMNO EN LOS VECTORES DE ALUMNOS
				for (int i = 0; i < cantAlumnos; i++) {
					String persona = nombres[i] + " " + apellidos[i];
					if (alumno.equalsIgnoreCase(persona)) {
						esta = true;
						pos = i;
						break;
					}
				}

				if (esta) {

					// ELIMINAR ALUMNO DE LOS VECTORES
					for (int i = pos; i < cantAlumnos - 1; i++) {
						nombres[i] = nombres[i + 1];
						apellidos[i] = apellidos[i + 1];
						ruts[i] = ruts[i + 1];
						paralelos[i] = paralelos[i + 1];
					}

					// DEJAR LIBRE LA ULTIMA POSICION
					nombres[cantAlumnos - 1] = null;
					apellidos[cantAlumnos - 1] = null;
					ruts[cantAlumnos - 1] = null;
					paralelos[cantAlumnos - 1] = null;

					cantAlumnos--;
					try {
						guardaralumnos();
					}catch (Exception e) {
						System.out.println("No se pudo actualizar el archivo...");
					}
					
					// BUSCAR SI ESTABA EN EL GRUPO
					for (int i = 0; i < grupo.length; i++) {
						if (grupo[i] != null) {
							if (grupo[i].equalsIgnoreCase(alumno)) {

								// DESPLAZAR LOS ELEMENTOS DEL GRUPO
								for (int j = i; j < grupo.length - 1; j++) {
									grupo[j] = grupo[j + 1];
								}
								grupo[grupo.length - 1] = null;
								break;
							}
						}
					}
					System.out.println("El alumno fue eliminado del curso.");

					

				} else {
					System.out.println("El alumno no esta inscrito en el curso.");
				}
			}

			if (respuesta == 3) {
				System.out.println("Ingrese su nombre y su apellido: ");
				String alumno = scanner.nextLine();
				String[] partes = alumno.split(" ");
				String nombre = partes[0];
				String apellido = partes[1];
				
				System.out.println("Ingrese su rut: ");
				String rut = scanner.nextLine();

				// VERIFICAR SI EL RUT YA EXISTE
				boolean existe = false;
				for (int i = 0; i < cantAlumnos; i++) {
					if (ruts[i].equalsIgnoreCase(rut)) {
						existe = true;
						break;
					}
				}

				if (existe) {
					System.out.println("El RUT ya pertenece a un alumno del curso.");
				} else if (cantAlumnos >= 100) {
					System.out.println("El curso se encuentra lleno.");

				} else {

					System.out.println("Ingrese su paralelo: ");
					String paralelo = scanner.nextLine();
					
					// VALIDAR PARALELO
					while (!paralelo.equalsIgnoreCase("C1") && !paralelo.equalsIgnoreCase("C2")) {
						System.out.println("Ingrese un paralelo correcto (C1/C2): ");
						paralelo = scanner.nextLine();
					}

					// AGREGAR ALUMNO A LOS VECTORES
					nombres[cantAlumnos] = nombre;
					apellidos[cantAlumnos] = apellido;
					ruts[cantAlumnos] = rut;
					paralelos[cantAlumnos] = paralelo;

					cantAlumnos++;
					try {
						guardaralumnos();
					} catch (IOException e) {
						System.out.println("No se pudo actualizar Alumnos.txt");
					}
					
					System.out.println("El alumno fue inscrito al curso.");
					System.out.println("Recuerde que debe inscribirse al grupo por separado.");

				}
			}
		} else {
			System.out.println("Por favor, primero cargue los archivos...");
		}
	}
	private static void guardaralumnos() throws IOException{
		FileWriter archivo = new FileWriter("txt's/alumnos.txt");
		BufferedWriter escritor = new BufferedWriter(archivo);

		for (int i = 0; i < cantAlumnos; i++) {

			escritor.write(nombres[i] + ";" + apellidos[i] + ";" + ruts[i] + ";" + paralelos[i]);
			escritor.newLine();
		}

		escritor.close();
	}
	
	private static void opcion3() {

		System.out.println("¿Como desea inscribir a la persona?");
		System.out.println("1) Por nombre completo.");
		System.out.println("2) Por RUT.");
		System.out.print("Ingrese una opcion: ");

		int respuesta = scanner.nextInt();
		respuesta = controlError(respuesta, 1, 2);
		scanner.nextLine();

		if (lectura && procesados) {

			if (respuesta == 1) {

				System.out.print("Ingrese el nombre y apellido de la persona: ");
				String persona = scanner.nextLine();

				String[] partes = persona.split(" ");
				String nombre = partes[0];
				String apellido = partes[1];

				boolean estaEnGrupo = false;
				boolean estaEnAlumnos = false;

				// BUSCAR SI YA ESTA EN EL GRUPO
				for (int i = 0; i < grupo.length; i++) {

					if (grupo[i] != null) {

						if (grupo[i].equalsIgnoreCase(nombre + " " + apellido)) {
							estaEnGrupo = true;
							break;
						}
					}
				}

				if (estaEnGrupo) {

					System.out.println("La persona ya esta en el grupo.");

				} else {

					// BUSCAR SI LA PERSONA EXISTE EN ALUMNOS
					for (int i = 0; i < cantAlumnos; i++) {

						if (nombres[i].equalsIgnoreCase(nombre)
								&& apellidos[i].equalsIgnoreCase(apellido)) {

							estaEnAlumnos = true;
							break;
						}
					}

					if (estaEnAlumnos) {

						// BUSCAR ESPACIO EN EL GRUPO
						boolean agregado = false;

						for (int i = 0; i < grupo.length; i++) {

							if (grupo[i] == null) {

								grupo[i] = nombre + " " + apellido;

								System.out.println("La persona fue añadida al grupo.");

								agregado = true;
								break;
							}
						}

						if (!agregado) {
							System.out.println("El grupo esta lleno.");
						}

					} else {

						System.out.println("La persona no forma parte de ningun paralelo");

						// AGREGAR A RECHAZADOS
						boolean agregado = false;

						for (int i = 0; i < rechazados.length; i++) {

							if (rechazados[i] == null) {

								rechazados[i] = nombre + " " + apellido;

								System.out.println("La persona fue añadida a rechazados...");

								agregado = true;
								break;
							}
						}

						if (!agregado) {
							System.out.println("El grupo de rechazados esta lleno...");
						}
					}
				}
			}

			if (respuesta == 2) {

				System.out.print("Ingrese el rut de la persona: ");
				String rut = scanner.nextLine();

				boolean res = false;
				int pos = 0;

				// BUSCAR EL RUT
				for (int i = 0; i < cantAlumnos; i++) {

					if (rut.equalsIgnoreCase(ruts[i])) {

						pos = i;
						res = true;
						break;
					}
				}

				if (res) {

					// EL RUT SI PERTENECE AL CURSO

					String persona = nombres[pos] + " " + apellidos[pos];

					System.out.println("Persona: " + persona);

					boolean estaEnGrupo = false;

					// BUSCAR SI YA ESTA EN EL GRUPO
					for (int j = 0; j < grupo.length; j++) {

						if (grupo[j] != null) {

							if (grupo[j].equalsIgnoreCase(persona)) {

								estaEnGrupo = true;
								break;
							}
						}
					}

					if (estaEnGrupo) {

						System.out.println("La persona ya esta en el grupo.");

					} else {

						// BUSCAR ESPACIO EN EL GRUPO
						boolean agregado = false;

						for (int i = 0; i < grupo.length; i++) {

							if (grupo[i] == null) {

								grupo[i] = persona;

								System.out.println("La persona fue añadida al grupo.");

								agregado = true;
								break;
							}
						}

						if (!agregado) {
							System.out.println("El grupo esta lleno");
						}
					}

				} else {

					// EL RUT NO PERTENECE AL CURSO

					System.out.println("El rut no pertenece a ningun paralelo.");
					System.out.println("No tenemos su nombre, por lo que se agregara el RUT en los rechazados");

					boolean agregado = false;

					for (int i = 0; i < rechazados.length; i++) {

						if (rechazados[i] == null) {

							rechazados[i] = rut;

							System.out.println("El rut fue añadido a rechazados");

							agregado = true;
							break;
						}
					}

					if (!agregado) {
						System.out.println("El grupo de rechazados esta lleno.");
					}
				}
			}

		} else {

			System.out.println("Lo lamentamos, no se han podido cargar los archivos");
		}
	}


	private static void opcion2() {
		System.out.println("Procesando solicitudes...");
		System.out.println();

		int contGrupo = 0;
		int contRechazo = 0;
		for (int i = 0; i < solicitud.length; i++) {

			if (solicitud[i] != null) {

				String linea = solicitud[i];
				String[] partes = linea.split(" ");

				String nombre = partes[0];
				String apellido = partes[1];

				boolean pertenece = busqueda(nombre, apellido);

				if (!pertenece) {
					boolean encontrado = false;
					int pos = 0;
					for (int j = 0; j < cantAlumnos; j++) {

						if (nombres[j].equalsIgnoreCase(nombre) && apellidos[j].equalsIgnoreCase(apellido)) {
							encontrado = true;
							pos = j;
							break;
						}
					}
					if (encontrado) {
						grupo[contGrupo] = nombre + " " + apellido;
						System.out.println("[OK] " + nombre + " " + apellido + " -> Añadido a paralelo " + paralelos[pos] );
						contGrupo++;
					} else {
						rechazados[contRechazo] = nombre + " " + apellido;
						System.out.println("[RECHAZO] " + nombre + " " + apellido + " -> Rechazado");
						contRechazo++;
					}
				}
			}
		}
		System.out.println("Resumen: " + contGrupo + " admitidos/ " + contRechazo + " rechazados...");

	}

	private static Boolean busqueda(String nombre, String apellido) {

		for (int i = 0; i < grupo.length; i++) {

			if (grupo[i] != null) {
				if (grupo[i].equalsIgnoreCase(nombre + " " + apellido)) {
					return true;
				}
			}
			if (rechazados[i] != null) {
				if (rechazados[i].equalsIgnoreCase(nombre + " " + apellido)) {
					return true;
				}
			}
		}

		return false;
	}

	private static int controlError(int opcion, int parametro1, int parametro2) {

		while (opcion < parametro1 || opcion > parametro2) { // En caso de que no se seleccione una opcion dentro del
																// rango
			System.out.print("Porfavor , seleccione una opcion valida:");
			opcion = scanner.nextInt();
		}
		return opcion;
	}

	private static void printMenu() {
		System.out.println("===== Sistema de Control del Grupo POO =====");
		System.out.println("Creado por Martin Hofer!!");
		System.out.println("1) Cargar archivos (Alumnos y Solicitudes).");
		System.out.println("2) Procesar solicitudes (filtrado automatico).");
		System.out.println("3) Inscripcion manual al grupo.");
		System.out.println("4) Administracion del curso.");
		System.out.println("5) Generar reportes.");
		System.out.println("6) Analisis estadistico.");
		System.out.println("7) Salir.");
		System.out.print("Seleccione una opcion:");

	}

	private static void solicitudes() throws FileNotFoundException {
		File arch = new File("txt's/solicitudes.txt");
		Scanner sFile = new Scanner(arch);
		int cont = 0;
		while (sFile.hasNextLine()) {
			String linea = sFile.nextLine();
			String[] partes = linea.split("-");
			String nombre = partes[0];
			String apellido = partes[1];

			solicitud[cont] = nombre + " " + apellido;
			cont++;
		}
	}

	private static int leerAlumnos() throws FileNotFoundException {

		File arch = new File("txt's/alumnos.txt");
		Scanner sFile = new Scanner(arch);
		int contador = 0;
		while (sFile.hasNextLine()) {

			String linea = sFile.nextLine();
			String[] partes = linea.split(";");
			String nombre = partes[0];
			String apellido = partes[1];
			String rut = partes[2];
			String paralelo = partes[3];

			nombres[contador] = nombre;
			apellidos[contador] = apellido;
			ruts[contador] = rut;
			paralelos[contador] = paralelo;
			contador++;

		}
		sFile.close();
		return contador;

		/*
		 * Aqui esta la funcion para leer el archivo alumnos.txt e ir asignando a cada
		 * lista su dato respectivo retornando la cantidad de alumnos totales en ambos
		 * paralelos y considerando que el maximo es de 100 alumnos
		 */

	}

}
