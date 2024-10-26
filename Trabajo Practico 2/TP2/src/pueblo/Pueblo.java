package pueblo;

import raza.*;
import juego.*;

public class Pueblo {
	private int numeroPueblo;
	private int cantidadHabitantes;
	private NombreRaza nombreRaza;
	private TipoDePueblo tipoDePueblo;
	private Ejercito ejercito;

	public Pueblo(int numeroPueblo, int cantidadHabitantes, NombreRaza nombreRaza, TipoDePueblo tipoDePueblo) {
		this.numeroPueblo = numeroPueblo;
		this.cantidadHabitantes = cantidadHabitantes;
		this.nombreRaza = nombreRaza;
		this.tipoDePueblo = tipoDePueblo;
		this.ejercito = new Ejercito(cantidadHabitantes);
	}

	// ---------------Metodos-------------------//

	public void generarEjercito(Raza raza) {
		for (int i = 0; i < cantidadHabitantes; i++) {
			ejercito.agregar(nombreRaza.crearRaza());
		}
	}

	public boolean hayEjercito() {
		return this.ejercito.haySoldados();
	}

	public void agregarAliados(Pueblo pueblo2) {
		this.ejercito.agregarAliados(pueblo2.getEjercito(), pueblo2.getCantidadHabitantes());
	}

	// ---------------Getters-------------------//

	public int getNumeroPueblo() {
		return numeroPueblo;
	}

	public int getCantidadHabitantes() {
		return this.cantidadHabitantes;
	}

	public NombreRaza getNombreRaza() {
		return nombreRaza;
	}

	public TipoDePueblo getTipoDePueblo() {
		return tipoDePueblo;
	}

	public Ejercito getEjercito() {
		return ejercito;
	}
}