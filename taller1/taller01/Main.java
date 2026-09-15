//Martin Hofer Ochoa - 22.368.055-0 - mrtinzinn
//Vicente Jara Huerta - 22.108.526-4 -
//https://github.com/mrtinzinn/Taller1_POO/tree/main	

package taller01;

import java.io.File;
import java.io.FileNotFoundException;
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

	public static void main(String[] args) throws FileNotFoundException {

		printMenu();
		int opcion = scanner.nextInt();
		opcion = controlError(opcion);
		// CREAR WHILE Y OPCIONES DEL MENU

		while (opcion != 7) {

			if (opcion == 1) { // CARGAR ARCHIVOS
				System.out.println("Cargando archivos...");
				cantAlumnos = leerAlumnos();
				solicitudes();
				System.out.println("Los archivos han sido cargados con exito.");

			}
			if (opcion == 2) {

				opcion2();
			}

			System.out.println();
			printMenu();
			opcion = scanner.nextInt();
			opcion = controlError(opcion);
		}
		System.out.println("Saliendo... Nos vemos!");
	}

	private static void opcion2() {
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
					for(int j = 0;j < cantAlumnos;j++) {
						
						if(nombres[j].equalsIgnoreCase(nombre) && apellidos[j].equalsIgnoreCase(apellido)) {
							encontrado = true;
							break;
						}
					}
					if (encontrado) {
						grupo[contGrupo] = nombre + " " + apellido;
						System.out.println(nombre + " " + apellido + " ->Añadido");
						contGrupo++;
					}else {
						rechazados[contRechazo] = nombre + " " + apellido;
						System.out.println(nombre + " " + apellido + " -> Rechazado");
						contRechazo++;
					}
				}
			}
		}

	}

	private static Boolean busqueda(String nombre, String apellido) {

		for (int i = 0; i < grupo.length; i++) {

			if(grupo[i]!= null) {
				if(grupo[i].equalsIgnoreCase(nombre + " " + apellido)) {
					return true;
				}
			}
			if(rechazados[i] != null) {
				if(rechazados[i].equalsIgnoreCase(nombre + " " + apellido)){
					return true;
				}
			}
		}
		
		return false;
	}

	private static int controlError(int opcion) {

		while (opcion < 1 || opcion > 7) { // En caso de que no se seleccione una opcion dentro del rango
			System.out.print("Porfavor , seleccione una opcion valida:");
			opcion = scanner.nextInt();
		}
		return opcion;
	}

	private static void printMenu() {
		System.out.println("===== Sistema de Control del Grupo POO =====");
		System.out.println("Creado por Martin Hofer y Vicente Jara!!");
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
