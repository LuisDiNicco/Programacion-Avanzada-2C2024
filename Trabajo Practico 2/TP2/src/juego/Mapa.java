package juego;

import java.util.HashSet;
import java.util.Set;

import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import raza.NombreRaza;

public class Mapa {
	private static Mapa instancia = null;
	private static int[][] grafo;
	private Pueblo[] pueblos;

	private Mapa(int numeroDePueblos) {
		grafo = new int[numeroDePueblos][numeroDePueblos];
		for (int i = 0; i < grafo.length; i++) {
			for (int j = 0; j < grafo[i].length; j++) {
				grafo[i][j] = Integer.MAX_VALUE;

				if (i == j) {
					grafo[i][j] = 0;
				}
			}
		}
	}

	public static void inicializarMapa(int numeroDePueblos) {
		if (instancia != null) {
			throw new RuntimeException("La instancia de Mapa ya ha sido inicializada.");
		}
		instancia = new Mapa(numeroDePueblos);
	}

	public static Mapa getInstancia() {
		if (instancia == null) {
			throw new RuntimeException(
					"La instancia de Mapa no ha sido inicializada. Llama a inicializarMapa() primero.");
		}
		return instancia;
	}

	public int[][] getGrafo() {
		return grafo;
	}

	public void setDistancia(int inicio, int fin, int distancia) {
		grafo[inicio - 1][fin - 1] = distancia;
		grafo[fin - 1][inicio - 1] = distancia;
	}

	public static void reiniciarMapa() {
		instancia = null;
	}

	public int getDistancia(int nodoOrigen, int nodoFin) {
		return grafo[nodoOrigen][nodoFin];
	}

	public int getCantidadDePueblos() {
		return grafo.length;
	}

	public void setPueblos(Pueblo[] pueblos) {
		this.pueblos = pueblos;
	}

	public Pueblo getPueblo(int numeroPueblo) {
		int cantidadHabitantes = pueblos[numeroPueblo].getCantidadHabitantes();
		NombreRaza nombreRaza = pueblos[numeroPueblo].getNombreRaza();
		TipoDePueblo tipo = pueblos[numeroPueblo].getTipoDePueblo();

		Pueblo Pueblo = new Pueblo(numeroPueblo, cantidadHabitantes, nombreRaza, tipo);
		Pueblo.generarEjercito(nombreRaza.crearRaza());
		return Pueblo;
	}

	public Pueblo[] getPueblos() {
		return pueblos;
	}
}
