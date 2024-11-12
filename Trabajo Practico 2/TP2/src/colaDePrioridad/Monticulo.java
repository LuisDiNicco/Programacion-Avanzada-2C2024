package colaDePrioridad;

import java.util.ArrayList;

public class Monticulo {

	private ArrayList<Camino> array;
	private int tope;

	public Monticulo() {
		this.array = new ArrayList<>();
		this.array.add(null);
		this.tope = 0;
	}

	public void agregarElemento(Camino camino) {
		this.array.add(camino);
		this.tope++;
		reordenarInsercion(tope);
	}

	private void reordenarInsercion(int tope) {
		while (tope / 2 > 0) {
			int indicePadre = tope / 2;
			if (array.get(indicePadre).compareTo(array.get(tope)) > 0) {
				intercambiar(indicePadre, tope);
			}
			tope = indicePadre;
		}
	}

	public Camino sacarElemento() {
		if (tope == 0)
			return null;
		Camino minElemento = array.get(1);
		array.set(1, array.get(tope));
		array.remove(tope);
		tope--;
		reordenarAlSacar(1);
		return minElemento;
	}

	private void reordenarAlSacar(int indice) {
		while (indice * 2 <= tope) {
			int hijoIzq = indice * 2;
			int hijoDer = hijoIzq + 1;
			int minHijo = hijoIzq;

			if (hijoDer <= tope && array.get(hijoDer).compareTo(array.get(hijoIzq)) < 0) {
				minHijo = hijoDer;
			}

			if (array.get(indice).compareTo(array.get(minHijo)) > 0) {
				intercambiar(indice, minHijo);
			} else {
				break;
			}

			indice = minHijo;
		}
	}

	private void intercambiar(int i, int j) {
		Camino temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	public Camino verPrimerElemento() {
		if (tope == 0)
			return null;
		return array.get(1);
	}

	public int getTamaño() {
		return tope;
	}

	public boolean estaVacio() {
		return tope == 0;
	}
}