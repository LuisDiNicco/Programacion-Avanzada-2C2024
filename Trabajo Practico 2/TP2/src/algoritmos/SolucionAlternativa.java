package algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import colaDePrioridad.*;

public class SolucionAlternativa {
	private int[][] grafo;

	public SolucionAlternativa(int[][] grafo) {
		this.grafo = grafo;
	}

	public List<Camino> encontrarCaminoNMinimo(int nodoInicio, int nodoFin, int N) {
		List<Camino> caminosMinimos = new ArrayList<>();
		ColaDePrioridad colaDePrioridad = new ColaDePrioridad();

		colaDePrioridad.insertar(new Camino(Arrays.asList(nodoInicio), 0));

		while (!colaDePrioridad.estaVacio() && caminosMinimos.size() < N) {
			Camino caminoActual = colaDePrioridad.extraerMin();
			int posicionUltimoNodo = caminoActual.getNodos().size() - 1;
			int nodoActual = caminoActual.getNodos().get(posicionUltimoNodo);

			if (nodoActual == nodoFin) {
				caminosMinimos.add(caminoActual);
				continue;
			}

			for (int w = 0; w < grafo.length; w++) {
				int costo = grafo[nodoActual][w];

				if (costo > 0 && costo < Integer.MAX_VALUE) {
					if (caminoActual.getNodos().contains(w)) {
						continue;
					}

					List<Integer> nuevoCamino = new ArrayList<>(caminoActual.getNodos());
					nuevoCamino.add(w);
					int costoNuevoCamino = caminoActual.getCosto() + costo;

					colaDePrioridad.insertar(new Camino(nuevoCamino, costoNuevoCamino));
				}
			}
		}

		return caminosMinimos;
	}
}