package algoritmos;

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
		boolean[] visitado = new boolean[cantNodos];

		visitado[nodoOrigen] = true;

		for (int i = 0; i < cantNodos; i++) {
			distancia[i] = grafo[nodoOrigen][i];
		}

		int w = minimo(visitado);
		int NodoActual = nodoOrigen;
		while (w != -1) {

			predecesores[w] = NodoActual;
			visitado[w] = true;

			for (int i = 0; i < cantNodos; i++) {
				if (visitado[i] == false) {
					if (grafo[w][i] != Integer.MAX_VALUE) {
						if (grafo[w][i] + distancia[w] < distancia[i]) {
							this.distancia[i] = grafo[w][i] + distancia[w];
						}
					}
				}
			}
			NodoActual = w;
			w = minimo(visitado);
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
}