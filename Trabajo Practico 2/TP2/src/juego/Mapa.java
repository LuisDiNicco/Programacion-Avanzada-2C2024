package juego;

import java.util.HashSet;
import java.util.Set;

public class Mapa {
	private static Mapa instancia = null;
	private static int[][] grafo;

	private Mapa(int numeroDePueblos) {
		// Inicializar el grafo como una matriz de adyacencia con valores infinitos (sin
		// caminos)
		grafo = new int[numeroDePueblos][numeroDePueblos];
		for (int i = 0; i < grafo.length; i++) {
			for (int j = 0; j < grafo[i].length; j++) {
				grafo[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	// Método estático para inicializar la instancia única de Mapa
	public static void inicializarMapa(int numeroDePueblos) {
		if (instancia != null) {
			throw new RuntimeException("La instancia de Mapa ya ha sido inicializada.");
		}
		instancia = new Mapa(numeroDePueblos);
	}

	// Método estático para obtener la instancia única de Mapa
	public static Mapa getInstancia() {
		if (instancia == null) {
			throw new RuntimeException(
					"La instancia de Mapa no ha sido inicializada. Llama a inicializarMapa() primero.");
		}
		return instancia;
	}

	// Método para obtener el grafo
	public int[][] getGrafo() {
		return grafo;
	}

	// Método para configurar una distancia entre pueblos
	public void setDistancia(int inicio, int fin, int distancia) {
		grafo[inicio - 1][fin - 1] = distancia;
		grafo[fin - 1][inicio - 1] = distancia; // Asegura la simetría en ambos sentidos
	}

	// Método para borrar la instancia actual de Mapa
	public static void reiniciarMapa() {
		instancia = null;
	}
	
	// Método para retornar los nodos adyacentes a un nodo dado
    public Set<Integer> getPueblosAdyacentes(int nodo) {
        Set<Integer> adyacentes = new HashSet<>();
        for (int i = 0; i < grafo[nodo].length; i++) {
            if (grafo[nodo][i] != Integer.MAX_VALUE) {
                adyacentes.add(i);
            }
        }
        return adyacentes;
    }
}
