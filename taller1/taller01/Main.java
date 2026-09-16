//Martin Hofer Ochoa - 22.368.055-0 - mrtinzinn
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
	public static boolean lectura = false; // Creo un booleando que verifica si se leyeron ambos archivos para que no se
											// caiga el codigo.

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
				} else {
					System.out.println(
							"No se han cargado los archivos , por favor seleccione la opcion 1 para poder filtrar...");
				}
			}
			if (opcion == 3) {
				System.out.println("¿Como desea inscribir a la persona?");
				System.out.println("1) Por nombre completo.");
				System.out.println("2) Por RUT.");
				System.out.print("Ingrese una opcion: ");
				int respuesta = scanner.nextInt();
				respuesta = controlError(respuesta, 1, 2);
				scanner.nextLine();
				if(lectura) {
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
						for (int i = 0; i < ruts.length; i++) {
							if (rut.equalsIgnoreCase(ruts[i])) {
								pos = i;
								res = true;
								break;
							}
						}
						if (res) { // SI EL RUT PERTENECE AL GRUPO
							System.out.println("Persona: " + nombres[pos] + " " + apellidos[pos]);

							for (int j = 0; j < grupo.length; j++) {

								if (nombres[pos].equalsIgnoreCase(nombres[j])
										&& apellidos[pos].equalsIgnoreCase(nombres[j])) {

									System.out.println("Se encontro a la persona en el grupo...");
									break;

								}
								if (grupo[j] == null) {
									grupo[j] = nombres[pos] + " " + apellidos[pos];
								}
								if (j == grupo.length - 1) {

									if (grupo[j] != null) {

										System.out.println("Lo sentimos, el grupo esta lleno...");
									}
								}
							}
						} else {
							System.out.println("El rut no pertenece a ningun paralelo");
							System.out.println("No tenemos su nombre, por lo que se agregara el RUT en los rechazados.");
							for (int i = 0; i < rechazados.length; i++) {
								if (rechazados[i] == null) {
									rechazados[i] = rut;
								}
								if (i == rechazados.length - 1) {
									if (rechazados[i] != null) {
										System.out.println("El grupo de rechazados esta lleno...");
									}
								}
							}
						}
					}
				}else {
					System.out.println("Lo lamentamos , no se han podido cargar los archivos");
				}
			}

			System.out.println();
			printMenu();
			opcion = scanner.nextInt();
			opcion = controlError(opcion, 1, 7);
		}
		System.out.println("Saliendo... Nos vemos!");
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
					for (int j = 0; j < cantAlumnos; j++) {

						if (nombres[j].equalsIgnoreCase(nombre) && apellidos[j].equalsIgnoreCase(apellido)) {
							encontrado = true;
							break;
						}
					}
					if (encontrado) {
						grupo[contGrupo] = nombre + " " + apellido;
						System.out.println("[OK] " + nombre + " " + apellido + " ->Añadido a ");
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
