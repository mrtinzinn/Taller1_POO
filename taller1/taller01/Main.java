//Martin Hofer Ochoa - 22.368.055-0 - mrtinzinn
//Vicente Jara Huerta - 22.108.526-4 -
//https://github.com/mrtinzinn/Taller1_POO/tree/main	

package taller01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		String[] nombres = new String[100];
		String[] apellidos = new String[100];
		String[] ruts = new String[100];
		String[] paralelos = new String[100];// CREO LAS LISTAS QUE ALMACENARAN LOS DATOS

		printMenu();
		int opcion = scanner.nextInt();
		opcion = controlError(opcion);
		//CREAR WHILE Y OPCIONES DEL MENU
		
		int cantAlumnos = leerAlumnos(nombres, apellidos, ruts, paralelos);
		String[] grupo = new String[cantAlumnos];
		String[] rechazados = new String[cantAlumnos];
		solicitudes(nombres, apellidos, cantAlumnos, grupo, ruts, paralelos, rechazados);
	}

	private static int controlError(int opcion) {
		Scanner scanner = new Scanner(System.in);
		while (opcion <= 0 && opcion > 7) { //En caso de que no se seleccione una opcion dentro del rango
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

	private static void solicitudes(String[] nombres, String[] apellidos, int cantAlumnos, String[] grupo,
			String[] ruts, String[] paralelos, String[] rechazados) throws FileNotFoundException {
		File arch = new File("txt's/solicitudes.txt");
		Scanner sFile = new Scanner(arch);
		while (sFile.hasNextLine()) {
			String linea = sFile.nextLine();
			String[] partes = linea.split("-");
			String nombre = partes[0];
			String apellido = partes[1];
			int cont = 0;
			int cont2 = 0;
			for (int i = 0; i < cantAlumnos; i++) {
				if (nombres[i].equals(nombre) && apellidos[i].equals(apellido)) {
					grupo[cont] = nombres[i] + " " + apellidos[i] + " " + ruts[i] + " " + paralelos[i];// ESTO AUN NO
																										// ESTA
																										// TERMINADO
				} else {
					rechazados[cont2] = nombre + " " + apellido;
				}
			}
		}

	}

	private static int leerAlumnos(String[] nombres, String[] apellidos, String[] ruts, String[] paralelos)
			throws FileNotFoundException {

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
