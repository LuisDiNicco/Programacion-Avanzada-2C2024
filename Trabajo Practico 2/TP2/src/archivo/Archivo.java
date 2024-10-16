package archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import juego.Simulacion;
import pueblo.*;
import raza.*;

public class Archivo {
	public static void leerArchivo(String ruta, Simulacion simulacion) {
		File archivo = new File(ruta);
		Scanner sc = null;
		try {
			sc = new Scanner(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int numeroDePueblos = sc.nextInt();

		Pueblo[] pueblos = new Pueblo[numeroDePueblos];
		sc.nextLine();
		for (int i = 0; i < numeroDePueblos; i++) {
			String[] razas = sc.nextLine().split(" ");
			int totalHabitantes = Integer.valueOf(razas[1]);
			NombreRaza nombreRaza = NombreRaza.valueOf(razas[2].toUpperCase());
			TipoDePueblo tipo = TipoDePueblo.valueOf(razas[3].toUpperCase());

			pueblos[i] = new Pueblo(i + 1, totalHabitantes, nombreRaza, tipo);
			pueblos[i].generarEjercito(nombreRaza.crearRaza());
		}

		simulacion.setPueblos(pueblos);

		String[] partes  = sc.nextLine().split("->");
		String[] camino = new String[partes.length];

		for (int i = 0; i < partes.length; i++) {
		    camino[i] = partes[i].trim(); // Elimina los espacios en blanco
		}
		
		int puebloInicio = Integer.valueOf(camino[0]);
		int puebloFin = Integer.valueOf(camino[1]);

		simulacion.setPuebloFin(puebloFin);
		simulacion.setPuebloInicio(puebloInicio);

		int[][] grafo = new int[numeroDePueblos][numeroDePueblos];

		for (int i = 0; i < grafo.length; i++) {
			for (int j = 0; j < grafo[i].length; j++) {
				grafo[i][j] = Integer.MAX_VALUE;
			}
		}

		while (sc.hasNext()) {
			int inicio = sc.nextInt();
			int fin = sc.nextInt();
			int distancia = sc.nextInt();

			grafo[inicio - 1][fin - 1] = distancia;
			grafo[fin - 1][inicio - 1] = distancia;
		}

		simulacion.setGrafo(grafo);
	}
}
