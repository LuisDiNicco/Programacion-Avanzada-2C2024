package algoritmos;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra {

	private int[][] grafo;
	private int[] predecesores;
	private int[] distancia;
	private int nodoOrigen;
	private int cantNodos;

	public Dijkstra(int[][] grafo, int nodoOrigen) {
		this.cantNodos = grafo.length;
		this.grafo = grafo;
		this.distancia = new int[cantNodos];
		this.predecesores = new int[cantNodos];
	}

	// ---------------Metodos-------------------//

	private int minimo(boolean[] visitado) {
		int min = Integer.MAX_VALUE;
		int pos = -1;
		for (int i = 0; i < distancia.length; i++) {
			if (distancia[i] < min && visitado[i] == false) {
				min = distancia[i];
				pos = i;
			}
		}
		return pos;
	}

	public void calcularDistanciaDijkstra() {

		for (int i = 0; i < grafo.length; i++) {
			distancia[i] = grafo[nodoOrigen][i];
		}

		boolean[] visitado = new boolean[cantNodos];
		distancia[nodoOrigen] = 0;

		for (int step = 0; step < cantNodos; step++) {
			int w = minimo(visitado);
			if (w == -1)
				break;
			visitado[w] = true;

			for (int j = 0; j < cantNodos; j++) {
				if (!visitado[j] && grafo[w][j] != Integer.MAX_VALUE) {
					int nuevaDistancia = grafo[w][j] + distancia[w];
					if (nuevaDistancia < distancia[j]) {
						distancia[j] = nuevaDistancia;
						predecesores[j] = w;
					}
				}
			}
		}
	}

	// ---------------Getters-------------------//

	public int[] getVectorDistancia() {
		return distancia;
	}

	public int[] getVectorPredecesores() {
		return predecesores;
	}

	public int getNodoOrigen() {
		return nodoOrigen;
	}

	public int getDistancia(int otroNodo) {
		return distancia[otroNodo - 1];
	}

	public List<Integer> getRuta(int nodoFin) {
		List<Integer> ruta = new LinkedList<Integer>();
		int nodoActual = nodoFin;

		while (nodoActual != nodoOrigen) {
			ruta.add(nodoActual);
			nodoActual = predecesores[nodoActual];
		}
		ruta.add(nodoOrigen);

		Collections.reverse(ruta);

		return ruta;
	}
}