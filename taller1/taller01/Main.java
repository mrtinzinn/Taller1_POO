//Martin Hofer Ochoa - 22.368.055-0 - mrtinzinn
//Vicente Jara
//https://github.com/mrtinzinn/Taller1_POO/tree/main	

package taller01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String[] nombres = new String[100];
		String[] apellidos = new String[100];
		String[] ruts = new String[100];
		String[] paralelos = new String[100];
		for (int i = 0; i < nombres.length;i++) {
			nombres[i] = "a";
			apellidos[i] = "a";
			ruts[i] = "a";
			paralelos[i] = "a";
		}
		int cantAlumnos = leerAlumnos(nombres, apellidos, ruts, paralelos);

	}

	private static int leerAlumnos(String[] nombres, String[] apellidos, String[] ruts, String[] paralelos) throws FileNotFoundException {

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
	}

}
