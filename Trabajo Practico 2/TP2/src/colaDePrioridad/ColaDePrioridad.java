package colaDePrioridad;

public class ColaDePrioridad {
	private Monticulo monticulo;

	public ColaDePrioridad() {
		monticulo = new Monticulo();
	}

	public void insertar(Camino camino) {
		monticulo.agregarElemento(camino);
	}

	public Camino extraerMin() {
		Camino camino = monticulo.sacarElemento();
		return (camino != null) ? camino : null;
	}

	public Camino verPrimeroDeCola() {
		Camino camino = monticulo.verPrimerElemento();
		return (camino != null) ? camino : null;
	}

	public boolean estaVacio() {
		return monticulo.estaVacio();
	}

	public int tamaño() {
		return monticulo.getTamaño();
	}
}