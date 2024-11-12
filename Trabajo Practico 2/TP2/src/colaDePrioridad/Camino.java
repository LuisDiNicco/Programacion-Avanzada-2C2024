package colaDePrioridad;

import java.util.ArrayList;
import java.util.List;

public class Camino implements Comparable<Camino> {

	private List<Integer> nodos;
	private int costo;

	public Camino(List<Integer> nodos, int costo) {
		this.nodos = new ArrayList<>(nodos);
		this.costo = costo;
	}

	public int compareTo(Camino camino2) {
		return Integer.compare(this.costo, camino2.costo);
	}

	public List<Integer> getNodos() {
		return nodos;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
}