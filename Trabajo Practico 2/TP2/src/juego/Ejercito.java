package juego;

import java.util.ArrayList;
import java.util.List;
import raza.*;

public class Ejercito implements Combatible {
	private List<Raza> ejercito;
	private int tamaño;

	public Ejercito() {
		this.ejercito = new ArrayList<Raza>();
	}

	@Override
	public int atacar() {
		Raza aliado = this.ejercito.getFirst();
		int daño = aliado.atacar();
		return daño;
	}

	@Override
	public void recibirAtaque(int daño) {
		Raza guerrero = this.ejercito.getFirst();
		guerrero.recibirAtaque(daño);

		if (guerrero.getSalud() <= 0) {
			this.ejercito.remove(guerrero);
			this.tamaño--;
		}
	}

	@Override
	public void descansar() {
		for (Raza raza : ejercito) {
			raza.descansar();
		}
	}

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

	public boolean haySoldados() {
		return !this.ejercito.isEmpty();
	}

	public Raza getPrimero() {
		return this.ejercito.getFirst();
	}

	public int getTamaño() {
		return tamaño;
	}

	public List<Raza> getEjercito() {
		return ejercito;
	}
}
