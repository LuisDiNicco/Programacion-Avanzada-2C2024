package colaDePrioridad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Camino implements Comparable<Camino> {

	private List<Integer> nodos;
	private int costo;

	public Camino(List<Integer> nodos, int costo) {
		this.nodos = new ArrayList<>(nodos);
		this.costo = costo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, nodos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camino other = (Camino) obj;
		return costo == other.costo && Objects.equals(nodos, other.nodos);
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

	@Override
	public String toString() {
		String camino = "[";
		for (int i = 0; i < nodos.size() - 1; i++) {
			camino += ((nodos.get(i) + 1) + ", ");
		}
		camino += (nodos.get(nodos.size() - 1) + 1);
		camino += "]";

		return camino + " costo " + this.costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
}