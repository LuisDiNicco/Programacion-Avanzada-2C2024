package juego;

import java.util.ArrayList;
import java.util.List;
import raza.*;

public class Ejercito {
	private List<Raza> ejercito;
	private int tamaño;

	public Ejercito(int cantidadHabitantes) {
		this.ejercito = new ArrayList<Raza>();
		this.tamaño = cantidadHabitantes;
	}

	// ---------------Composite-------------------//

	public void atacar(Ejercito ejercitoEnemigo) {
		Raza aliado = this.ejercito.getFirst();
		int daño = aliado.atacar();
		ejercitoEnemigo.recibirAtaque(daño);

	}

	public void recibirAtaque(int daño) {
		Raza guerrero = this.ejercito.getFirst();
		guerrero.recibirAtaque(daño);

		if (guerrero.getSalud() < 0) {
			this.ejercito.remove(guerrero);
			this.tamaño--;
		}
	}

	public void descansar() {
		for (Raza raza : ejercito) {
			raza.descansar();
		}
	}

	// ---------------Metodos-------------------//

	public void agregar(Raza raza) {
		this.ejercito.addLast(raza);
		this.tamaño++;
	}

	public void agregarAliados(Ejercito ejercito2, int cantidadHabitantes) {
		List<Raza> aliados = ejercito2.getEjercito();
		for (int i = 0; i < cantidadHabitantes / 2; i++) {
			this.ejercito.addFirst(aliados.get(i));
			this.tamaño++;
		}
	}

	public void reordenarse() {
		Raza guerrero = this.getPrimero();

		if (guerrero.getSalud() != guerrero.getSaludMaxima()) {
			this.ejercito.removeFirst();
			this.ejercito.addLast(guerrero);
		}
	}

	// ---------------Metodos de Apoyo-------------------//

	public boolean haySoldados() {
		return !this.ejercito.isEmpty();
	}

	public Raza getPrimero() {
		return this.ejercito.getFirst();
	}

	// ---------------Getters-------------------//

	public int getTamaño() {
		return tamaño;
	}

	public List<Raza> getEjercito() {
		return ejercito;
	}
}
