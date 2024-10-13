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

	public void generarEjercito(Raza raza) {
		for (int i = 0; i < cantidadHabitantes; i++) {
			ejercito.agregar(nombreRaza.crearRaza());
		}
	}

	public Ejercito getEjercito() {
		return ejercito;
	}

	public TipoDePueblo getTipoDePueblo() {
		return tipoDePueblo;
	}

	public boolean hayEjercito() {
		return this.ejercito.haySoldados();
	}

	public void agregarAliados(Pueblo pueblo2) {
		this.ejercito.agregarAliados(pueblo2.getEjercito(), pueblo2.getCantidadHabitantes());
	}

	public int getCantidadHabitantes() {
		return this.cantidadHabitantes;
	}

	public NombreRaza getNombreRaza() {
		return nombreRaza;
	}

	public int getNumeroPueblo() {
		return numeroPueblo;
	}

}